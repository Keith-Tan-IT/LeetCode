class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        for (int i = 0; i < candies.length; i++) {
            if (maxCandies < candies[i]) {
                maxCandies = candies[i];
            }
        }

        List <Boolean> result = new ArrayList<>();

        for (int j = 0; j < candies.length; j++) {
            if (candies[j] + extraCandies >= maxCandies) {
                result.add(true);
            }
            else {
                result.add(false);
            }
        }
        return result;
    }
}
