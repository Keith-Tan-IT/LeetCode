class Solution {
    public int[] countBits(int n) {
        int[] output = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int curr = i;
            while(curr > 0) {
                count += curr % 2;
                curr = curr / 2;
            }
            output[i] = count;
        }
        return output;
    }
}
