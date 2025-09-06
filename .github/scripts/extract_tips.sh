#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"

mkdir -p "$TIPS_DIR"

echo "Starting tip extraction from: $BASE_DIR"

# Loop through all Java files
find "$BASE_DIR" -type f -name "*.java" | while read -r file; do
  # Extract TIP blocks (any line inside /** ... */ containing TIP)
  tips=$(awk '/\/\*/,/\*\// {print}' "$file" | awk '/TIP/ {flag=1} flag {print} /\*\// {flag=0}')

  if [ -n "$tips" ]; then
    # Extract problem number from TIP block
    problem_line=$(echo "$tips" | grep -i "Problem:" | head -1 | sed 's/^.*Problem: *//')
    if [ -n "$problem_line" ]; then
      # Separate number and title
      problem_number=$(echo "$problem_line" | cut -d'.' -f1 | tr -d ' ')
      problem_title=$(echo "$problem_line" | cut -d'.' -f2- | sed 's/^ *//')

      # Generate slug for filename
      slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')

      mdfile="$TIPS_DIR/${problem_number}-${slug}.md"

      # Fetch URL from LeetCode
      # Convert title to slug used in URL (replace spaces with - and lowercase)
      url_slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
      problem_url="https://leetcode.com/problems/$url_slug/"

      # Write markdown file
      echo -e "# Tip — $problem_number. $problem_title\n\nProblem URL: [$problem_title]($problem_url)\n\n$tips" > "$mdfile"
      echo "Wrote tip: $mdfile"
    fi
  fi
done

# Build aggregate TIPS.md (newest first based on file timestamp)
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_All extracted automatically. Newest tips first._" >> "$AGG_FILE"
echo -e "\n---\n" >> "$AGG_FILE"

# Sort tip files by modification time descending
for f in $(ls -1t $TIPS_DIR/*.md 2>/dev/null); do
  cat "$f" >> "$AGG_FILE"
  echo -e "\n\n---\n\n" >> "$AGG_FILE"
done

echo "TIPS.md generated with all tips."
