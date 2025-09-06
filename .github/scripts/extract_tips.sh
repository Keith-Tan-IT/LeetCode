#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="${1:-my-folder}"
AGG_FILE="$BASE_DIR/TIPS.md"

echo "Starting tip extraction from: $BASE_DIR"

# Loop through all Java files
find "$BASE_DIR" -type f -name "*.java" | while read -r file; do
    awk '
    BEGIN {in_comment=0; in_tip=0; tip_text=""}
    /\/\*\*/ {in_comment=1; buf=""}
    in_comment {buf=buf $0 "\n"}
    /TIP/ && in_comment {in_tip=1; tip_text=buf}
    /\*\// {
        if(in_tip){
            print tip_text
            in_tip=0
        }
        in_comment=0
    }
    ' "$file" | while IFS= read -r tip_block || [ -n "$tip_block" ]; do
        if [[ -z "$tip_block" ]]; then
            continue
        fi

        # Get problem number and title
        problem_line=$(echo "$tip_block" | grep -i "Problem:" || true | head -1)
        problem_number=$(echo "$problem_line" | cut -d'.' -f1 | grep -o '[0-9]*')
        problem_title=$(echo "$problem_line" | cut -d'.' -f2- | sed 's/^ *//')

        # Generate slug for file/folder name
        slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
        PROBLEM_FOLDER=$(dirname "$file")
        TIP_FILE="$PROBLEM_FOLDER/TIP.md"

        # Write per-problem TIP.md
        echo -e "# Tip — $problem_number. $problem_title\n\n$tip_block" > "$TIP_FILE"
        echo "Wrote per-problem TIP: $TIP_FILE"
    done
done

# Build aggregated TIPS.md, newest first
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_Newest tips first_" >> "$AGG_FILE"
echo -e "\n---\n" >> "$AGG_FILE"

find "$BASE_DIR" -type f -name "TIP.md" | sort -r | while read -r f; do
    cat "$f" >> "$AGG_FILE"
    echo -e "\n\n---\n\n" >> "$AGG_FILE"
done

echo "TIPS.md generated successfully at $AGG_FILE"
exit 0
