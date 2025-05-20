class Solution {
    public int[] countBits(int n) {
        int[] output = new int[n + 1];
        output[0] = 0;
        int power = 1;
        for (int i = 1, reset = 0; i <= n; i++, reset++) {
            if(i == power) {
                power *= 2;
                reset = 0;
            }
            output[i] = output[reset] + 1;
        }
        return output;
    }
}
