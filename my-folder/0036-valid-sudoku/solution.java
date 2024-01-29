class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if (number != '.')
                    if (seen.contains(number + "in Row" + i) ||
                        seen.contains(number + "in Column" + j) ||
                        seen.contains(number + "in Block" + i/3 + "-" + j/3))
                        return false; 
                    seen.add(number + "in Row" + i);
                    seen.add(number + "in Column" + j);
                    seen.add(number + "in Block" + i/3 + "-"+ j/3);
            }
        }
        return true;
    }
}
