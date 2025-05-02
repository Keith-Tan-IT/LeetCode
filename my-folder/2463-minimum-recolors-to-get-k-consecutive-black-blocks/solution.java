class Solution {
    public int minimumRecolors(String blocks, int k) {
        int output = 0, whiteCount = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) != 'B') {
                whiteCount += 1;
            }
        }
        output = whiteCount;
        for (int i = k; i < blocks.length(); i++) {
            if (blocks.charAt(i) != 'B') {
                if (blocks.charAt(i - k) == 'B') {
                    whiteCount += 1;
                }
            }
            else {
                if (blocks.charAt(i - k) != 'B') {
                    whiteCount --;
                }
            }
            output = Math.min(whiteCount,output);
        }
        return output;
    }
}
