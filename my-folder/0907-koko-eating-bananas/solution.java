class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000000;
        while (left < right) {
            int mid = (left + right) / 2;
            int counter = 0;
            for (int i = 0; i < piles.length; i++) {
                counter += Math.ceil(1.0 * piles[i] / mid);
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
