#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"

mkdir -p "$TIPS_DIR"

echo "Starting tip extraction from: $BASE_DIR"

# Loop over all Java files
find "$BASE_DIR" -type f -name "*.java" | while read -r file; do
  inside_tip=0
  tip_content=""
  problem_line=""

  while IFS= read -r line; do
    if [[ "$line" =~ /\*\* ]]; then
      inside_tip=0
    fi
    if [[ "$line" =~ TIP ]]; then
      inside_tip=1
    fi
    if [[ $inside_tip -eq 1 ]]; then
      tip_content+="$line"$'\n'
      if [[ "$line" =~ Problem: ]]; then
        problem_line=$(echo "$line" | sed 's/^.*Problem: *//')
      fi
    fi
  done < "$file"

  if [[ -n "$tip_content" && -n "$problem_line" ]]; then
    problem_number=$(echo "$problem_line" | cut -d'.' -f1 | tr -d ' ')
    problem_title=$(echo "$problem_line" | cut -d'.' -f2- | sed 's/^ *//')
    slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
    mdfile="$TIPS_DIR/${problem_number}-${slug}.md"
    problem_url="https://leetcode.com/problems/$slug/"

    echo -e "# Tip — $problem_number. $problem_title\n\nProblem URL: [$problem_title]($problem_url)\n\n$tip_content" > "$mdfile"
    echo "Wrote tip: $mdfile"
  fi
done

# Aggregate all tips
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_Newest tips first_" >> "$AGG_FILE"
echo -e "\n---\n" >> "$AGG_FILE"

for f in $(ls -1t $TIPS_DIR/*.md 2>/dev/null); do
  cat "$f" >> "$AGG_FILE"
  echo -e "\n\n---\n\n" >> "$AGG_FILE"
done

echo "TIPS.md generated successfully."
