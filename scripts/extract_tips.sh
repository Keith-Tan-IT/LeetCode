#!/usr/bin/env bash
set -euo pipefail

# Usage: ./extract_tips.sh [base_dir]
BASE_DIR="${1:-my-folder}"   # change if your sync destination differs
TIPS_DIR="tips"

mkdir -p "$TIPS_DIR"

# Find java files and extract TIP blocks
find "$BASE_DIR" -type f -name '*.java' | while IFS= read -r file; do
  # look for start marker "/** TIP"
  if grep -q '/\*\* *TIP' "$file"; then
    # create a slug (path-based) e.g. my-folder/0121-best.../Solution -> 0121-best...-Solution
    rel="${file#"$BASE_DIR"/}"
    slug=$(echo "$rel" | sed 's|/|-|g' | sed 's/\.java$//')
    mdfile="$TIPS_DIR/$slug.md"

    # extract block between '/** TIP' and '*/', strip the leading '*' and spaces
    tip=$(sed -n '/\/\*\* *TIP/,/\*\//p' "$file" | sed '1d;$d' | sed 's/^[[:space:]]*\* *//')

    # If tip is empty (defensive), skip
    if [ -z "$(echo "$tip" | tr -d '[:space:]')" ]; then
      echo "No tip content for $file, skipping."
      continue
    fi

    # write to markdown
    cat > "$mdfile" <<EOF
# Tip — $slug

**Source:** \`$file\`

$tip

---
_Extracted: $(date -u +"%Y-%m-%dT%H:%M:%SZ")_
EOF

    git add "$mdfile" || true
    echo "Wrote tip -> $mdfile"
  fi
done

# If there are any changes, commit & push (GitHub Actions runner already has GITHUB_TOKEN)
if [ -n "$(git status --porcelain)" ]; then
  git config user.name "github-actions[bot]"
  git config user.email "41898282+github-actions[bot]@users.noreply.github.com"
  git commit -m "chore(tips): extract tips from Java files" || true
  git push origin HEAD || true
else
  echo "No tip changes to commit."
fi
