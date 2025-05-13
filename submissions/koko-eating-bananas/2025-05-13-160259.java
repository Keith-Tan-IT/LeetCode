class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        int k = 0;
        int left = 1;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        int right = max;
        while (left < right) {
            int mid = (left + right) / 2;
            long counter = 0;
            for (int i = 0; i < piles.length; i++) {
                counter += piles[i] / mid;
                if (piles[i] % mid != 0) {
                    counter++;
                }
            }
            if (counter <= h) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}