#!/bin/bash
set -e

BASE_DIR="$1"

if [ -z "$BASE_DIR" ]; then
    echo "Usage: $0 <path-to-synced-leetcode-folder>"
    exit 1
fi

echo "Starting tip extraction from: $BASE_DIR"

# Create tips folder if not exists
mkdir -p "$BASE_DIR/tips"

# Initialize aggregated file
AGG_FILE="$BASE_DIR/TIPS.md"
echo "# LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "Newest tips first" >> "$AGG_FILE"
echo "" >> "$AGG_FILE"

# Iterate over each problem folder
for folder in "$BASE_DIR"/*/; do
    SOLUTION_FILE="$folder/solution.java"
    if [ ! -f "$SOLUTION_FILE" ]; then
        continue
    fi

    # Extract TIP block (multi-line)
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
    }' "$SOLUTION_FILE")

    # Skip if no TIP found
    if [ -z "$TIP_BLOCK" ]; then
        continue
    fi

    # Get problem number and title from TIP
    PROBLEM_LINE=$(echo "$TIP_BLOCK" | grep -Eo "Problem: [0-9]+\. .*")
    if [ -z "$PROBLEM_LINE" ]; then
        PROBLEM_LINE="Problem unknown"
    fi
    SLUG=$(echo "$folder" | sed 's#.*/##; s#/$##')  # folder name

    # Write per-problem TIP.md
    TIP_FILE="$folder/TIP.md"
    echo "# $PROBLEM_LINE" > "$TIP_FILE"
    echo "$TIP_BLOCK" >> "$TIP_FILE"
    echo "Wrote per-problem TIP: $TIP_FILE"

    # Append to aggregated TIPS.md (newest first)
    echo "## $PROBLEM_LINE" >> "$AGG_FILE"
    echo "$TIP_BLOCK" >> "$AGG_FILE"
    echo "" >> "$AGG_FILE"
done

echo "Aggregated TIPS.md generated successfully at: $AGG_FILE"
