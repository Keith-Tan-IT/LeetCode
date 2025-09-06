#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="${1:-my-folder}"
TIPS_DIR="tips"
AGG_FILE="TIPS.md"

mkdir -p "$TIPS_DIR"

echo "Starting tip extraction from: $BASE_DIR"

find "$BASE_DIR" -type f -name "*.java" | while read -r file; do
    awk '
    BEGIN {in_tip=0; tip_text=""}
    /\/\*\*/ {in_comment=1; buf=""}
    in_comment {buf=buf $0 "\n"}
    /TIP/ && in_comment {in_tip=1; tip_text=buf}
    /\*\// {
        if(in_tip){print tip_text; in_tip=0}
        in_comment=0
    }
    ' "$file" | while read -r tip_block; do
        problem_line=$(echo "$tip_block" | grep -i "Problem:" | head -1 | sed 's/^.*Problem: *//')
        if [[ -n "$problem_line" ]]; then
            problem_number=$(echo "$problem_line" | cut -d'.' -f1 | tr -d ' ')
            problem_title=$(echo "$problem_line" | cut -d'.' -f2- | sed 's/^ *//')
            slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
            mdfile="$TIPS_DIR/${problem_number}-${slug}.md"
            problem_url="https://leetcode.com/problems/$slug/"

            echo -e "# Tip — $problem_number. $problem_title\n\nProblem URL: [$problem_title]($problem_url)\n\n$tip_block" > "$mdfile"
            echo "Wrote tip: $mdfile"
        fi
    done
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
