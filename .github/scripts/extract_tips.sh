#!/usr/bin/env bash
set -euo pipefail

# Usage: ./extract_tips.sh my-folder
BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"

mkdir -p "$TIPS_DIR"

echo "Starting tip extraction from: $BASE_DIR"

# Loop through all Java files under the LeetCode sync folder
find "$BASE_DIR" -type f -name "*.java" | while read -r file; do

    # Extract all TIP blocks using grep -Poz (multi-line, null-separated)
    grep -Poz '/\*\*.*?TIP.*?\*/' "$file" | while IFS= read -r -d '' tip_block; do

        # Extract Problem number and title
        problem_line=$(echo "$tip_block" | grep -i "Problem:" | head -1 | sed 's/^.*Problem: *//')

        if [[ -n "$problem_line" ]]; then
            # Get problem number and title
            problem_number=$(echo "$problem_line" | cut -d'.' -f1 | tr -d ' ')
            problem_title=$(echo "$problem_line" | cut -d'.' -f2- | sed 's/^ *//')
            slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
            
            # Prepare markdown file path
            mdfile="$TIPS_DIR/${problem_number}-${slug}.md"
            problem_url="https://leetcode.com/problems/$slug/"

            # Write per-problem markdown
            echo -e "# Tip — $problem_number. $problem_title\n\nProblem URL: [$problem_title]($problem_url)\n\n$tip_block" > "$mdfile"
            echo "Wrote tip: $mdfile"
        fi

    done

done

# Aggregate all tips into TIPS.md
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_Newest tips first_" >> "$AGG_FILE"
echo -e "\n---\n" >> "$AGG_FILE"

# Add all per-problem markdown into TIPS.md (newest first)
for f in $(ls -1t $TIPS_DIR/*.md 2>/dev/null); do
    cat "$f" >> "$AGG_FILE"
    echo -e "\n\n---\n\n" >> "$AGG_FILE"
done

echo "TIPS.md generated successfully."
