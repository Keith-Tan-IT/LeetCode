class Solution {
    public int largestAltitude(int[] gain) {
        int[] altitudes = new int[gain.length + 1];
        altitudes[0] = 0;
        int highest = altitudes[0];
        for (int i = 1; i < gain.length + 1; i++) {
            altitudes[i] = altitudes[i-1] + gain[i-1];
            highest = Math.max(highest,altitudes[i]);
        }
        return highest;
    }
}
