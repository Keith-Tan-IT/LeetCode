#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"

mkdir -p "$TIPS_DIR"

# Reset aggregate file
cat > "$AGG_FILE" <<EOF
# 📘 LeetCode Tips Cheat Sheet

_All extracted automatically from solution files. Newest tips appear first._

---
EOF

# Extract TIP blocks from Java files into per-problem md files
find "$BASE_DIR" -type f -name '*.java' | while IFS= read -r file; do
  if grep -q '/\*\* *TIP' "$file"; then
    rel="${file#"$BASE_DIR"/}"
    slug=$(echo "$rel" | sed 's|/|-|g' | sed 's/\.java$//')
    mdfile="$TIPS_DIR/$slug.md"

    tip=$(sed -n '/\/\*\* *TIP/,/\*\//p' "$file" | sed '1d;$d' | sed 's/^[[:space:]]*\* *//')

    if [ -n "$(echo "$tip" | tr -d '[:space:]')" ]; then
      cat > "$mdfile" <<EOF
# Tip — $slug

**Source:** \`$file\`

$tip

---
_Extracted: $(date -u +"%Y-%m-%dT%H:%M:%SZ")_
EOF
    fi
  fi
done

# Build aggregated TIPS.md ordered by git commit date (newest first)
git ls-files "$TIPS_DIR"/*.md | while read f; do
  # Use last commit date for ordering
  date=$(git log -1 --format="%at" -- "$f")
  echo "$date|$f"
done | sort -nr | cut -d'|' -f2 | while read f; do
  echo "" >> "$AGG_FILE"
  cat "$f" >> "$AGG_FILE"
done

# Stage and commit if changes
if [ -n "$(git status --porcelain "$TIPS_DIR" "$AGG_FILE")" ]; then
  git add "$TIPS_DIR" "$AGG_FILE"
  git config user.name "github-actions[bot]"
  git config user.email "41898282+github-actions[bot]@users.noreply.github.com"
  git commit -m "chore(tips): update extracted tips and cheat sheet (newest first)"

  # Rebase onto remote without messing with unstaged files
  git fetch origin main
  git rebase origin/main

  git push origin HEAD:main
else
  echo "No tip changes to commit."
fi
