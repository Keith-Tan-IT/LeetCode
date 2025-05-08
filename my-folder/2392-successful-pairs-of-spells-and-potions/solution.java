class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] result = new int[spells.length];  
        for (int i = 0; i < spells.length; i++) {
            int left = 0;
            int right = potions.length - 1;
            while (right >= left) {
                int mid = (right + left )/ 2;
                if ((long) potions[mid] * spells[i] < success) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            result[i] = potions.length - left;
        }
        return result;
    }
}
