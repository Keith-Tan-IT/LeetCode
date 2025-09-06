#!/usr/bin/env bash
set -e

# Usage: ./extract_tips.sh <leetcode-folder>
LEETCODE_FOLDER=$1
if [ -z "$LEETCODE_FOLDER" ]; then
    echo "Usage: $0 <leetcode-folder>"
    exit 1
fi

echo "Starting tip extraction from: $LEETCODE_FOLDER"

# Aggregated cheat sheet
AGG_FILE="$LEETCODE_FOLDER/TIPS.md"
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_Newest tips first_" >> "$AGG_FILE"
echo "" >> "$AGG_FILE"

# Iterate over problem folders
for folder in "$LEETCODE_FOLDER"/*/; do
    SOLUTION_FILE="$folder/solution.java"
    if [ ! -f "$SOLUTION_FILE" ]; then
        continue
    fi

    # Extract TIP block (multi-line, detect TIP anywhere in /** ... */)
    TIP_BLOCK=$(awk '
    BEGIN {in_comment=0; tip_found=0; buf=""}
    /\/\*\*/ {in_comment=1; buf=""; tip_found=0}
    /\*\// {
        if(tip_found) print buf
        in_comment=0
        buf=""
    }
    in_comment {
        buf = buf $0 "\n"
        if($0 ~ /TIP/) tip_found=1
    }
    ' "$SOLUTION_FILE")

    # Skip if no TIP found
    if [ -z "$TIP_BLOCK" ]; then
        continue
    fi

    # Get problem number and title from TIP (trim leading '* ')
    PROBLEM_LINE=$(echo "$TIP_BLOCK" | sed -n 's/^[[:space:]]*\* *Problem: \(.*\)/\1/p')
    if [ -z "$PROBLEM_LINE" ]; then
        PROBLEM_LINE="Unknown. Unknown"
    fi

    # Slug = folder name (e.g., 0435-non-overlapping-intervals)
    SLUG=$(basename "$folder")

    # Write per-problem TIP.md
    TIP_FILE="$folder/TIP.md"
    echo "# $PROBLEM_LINE" > "$TIP_FILE"
    echo "$TIP_BLOCK" >> "$TIP_FILE"
    echo "Wrote per-problem TIP: $TIP_FILE"

    # Append to aggregated TIPS.md (newest first)
    echo "---" >> "$AGG_FILE"
    echo "## $PROBLEM_LINE" >> "$AGG_FILE"
    echo "$TIP_BLOCK" >> "$AGG_FILE"
    echo "" >> "$AGG_FILE"
done

echo "Aggregated TIPS.md generated successfully at: $AGG_FILE"
