#!/usr/bin/env bash
set -euo pipefail

# Usage: .github/scripts/extract_tips.sh [base_dir]
BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"
BRANCH="tips/extract-$(date -u +"%Y%m%dT%H%M%SZ")"
COMMITTER_NAME="github-actions[bot]"
COMMITTER_EMAIL="41898282+github-actions[bot]@users.noreply.github.com"

mkdir -p "$TIPS_DIR"

echo "Starting tip extraction from: $BASE_DIR"
# 1) Extract per-problem TIP blocks into tips/*.md
found_any=0
while IFS= read -r file; do
  if grep -q '/\*\* *TIP' "$file"; then
    found_any=1
    rel="${file#"$BASE_DIR"/}"
    slug=$(echo "$rel" | sed 's|/|-|g' | sed 's/\.java$//')
    mdfile="$TIPS_DIR/$slug.md"

    tip=$(sed -n '/\/\*\* *TIP/,/\*\//p' "$file" | sed '1d;$d' | sed 's/^[[:space:]]*\* *//')
    if [ -n "$(echo "$tip" | tr -d '[:space:]')" ]; then
      printf '%s\n\n' "# Tip — $slug" > "$mdfile"
      printf '%s\n\n' "**Source:** \`$file\`" >> "$mdfile"
      printf '%s\n\n' "$tip" >> "$mdfile"
      printf '%s\n' "---" >> "$mdfile"
      printf '%s\n' "_Extracted: $(date -u +"%Y-%m-%dT%H:%M:%SZ")_" >> "$mdfile"
      echo "Wrote tip: $mdfile"
    fi
  fi
done < <(find "$BASE_DIR" -type f -name '*.java' 2>/dev/null)

if [ "$found_any" -eq 0 ]; then
  echo "No TIP blocks found under $BASE_DIR — nothing to do."
  exit 0
fi

# 2) Configure git committer
git config user.name "$COMMITTER_NAME"
git config user.email "$COMMITTER_EMAIL"

# 3) Start a new branch for tip commits (safe: unique timestamp)
git checkout -b "$BRANCH"

# 4) Stage and commit per-problem tip files (if there are changes)
git add "$TIPS_DIR" || true
if [ -n "$(git status --porcelain)" ]; then
  git commit -m "chore(tips): update per-problem tip files"
  echo "Committed per-problem tips on branch $BRANCH"
else
  echo "No changes in $TIPS_DIR to commit."
fi

# 5) Build aggregated TIPS.md — newest tips first (by commit timestamp)
cat > "$AGG_FILE" <<'EOF'
# 📘 LeetCode Tips Cheat Sheet

_All extracted automatically from solution files. Newest tips appear first._

---
EOF

# list committed tip files (use git ls-files so we get tracked files with commit dates)
files=$(git ls-files "$TIPS_DIR"/*.md 2>/dev/null || true)

if [ -n "$files" ]; then
  # produce a sorted list by latest commit timestamp
  printf "%s\n" $(
    for f in $files; do
      # If a file has no log (shouldn't happen because we committed), fallback to 0
      ts=$(git log -1 --format='%at' -- "$f" 2>/dev/null || echo 0)
      echo "${ts}|${f}"
    done | sort -nr | cut -d'|' -f2
  ) | while IFS= read -r f; do
    echo "" >> "$AGG_FILE"
    cat "$f" >> "$AGG_FILE"
  done
else
  # Fallback: include any tips files present on disk (untracked)
  for f in "$TIPS_DIR"/*.md; do
    [ -f "$f" ] || continue
    echo "" >> "$AGG_FILE"
    cat "$f" >> "$AGG_FILE"
  done
fi

# 6) Add and commit aggregate file
git add "$AGG_FILE"
if [ -n "$(git status --porcelain)" ]; then
  git commit -m "chore(tips): build TIPS.md (newest first)"
  echo "Committed $AGG_FILE on branch $BRANCH"
else
  echo "No changes to $AGG_FILE to commit."
fi

# 7) Rebase branch on top of latest origin/main (if origin/main exists)
git fetch origin main
if git show-ref --verify --quiet refs/remotes/origin/main; then
  echo "Rebasing $BRANCH onto origin/main"
  git rebase origin/main
else
  echo "No origin/main found; skipping rebase"
fi

# 8) Push the result to main
echo "Pushing changes to origin main"
git push origin HEAD:main

echo "Done — tips extracted and pushed from branch $BRANCH -> main"
