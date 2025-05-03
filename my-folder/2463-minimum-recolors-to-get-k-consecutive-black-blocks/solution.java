class Solution {
    public int minimumRecolors(String blocks, int k) {
        int whiteCount = 0, output = k;
        for (int i = 0; i < blocks.length(); i++) {
            if (blocks.charAt(i) != 'B') {
                whiteCount++;
            }
            System.out.println(whiteCount);
            if (i - k >= 0) {
                if (blocks.charAt(i - k) != 'B') {
                    whiteCount--;
                }
            }
            if (i >= k - 1) {
                output = Math.min(whiteCount,output);
            }
        }
        return output;
    }
}
