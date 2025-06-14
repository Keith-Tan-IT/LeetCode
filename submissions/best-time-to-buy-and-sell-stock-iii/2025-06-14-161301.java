class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        int buy = prices[0];
        for (int i = 1; i < n; i++) {
            if (buy > prices[i]){
                buy = prices[i];
            }
            left[i] = Math.max(left[i - 1], prices[i] - buy);
        }

        int sell = prices[n - 1];
        for (int i = n - 2; i >= 0; i--){
            if (sell < prices[i]) {
                sell = prices[i];
            }
            right[i] = Math.max(right[i + 1], sell - prices[i]);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, left[i] + right[i]);
        }
        return max;
    }
}