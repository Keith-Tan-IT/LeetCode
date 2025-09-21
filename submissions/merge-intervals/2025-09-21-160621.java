class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        int[][] output = new int[intervals.length][2];
        int index = 0; 
        output[index] = intervals[index];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= output[index][1]) {
                output[index][1] = Math.max(output[index][1], intervals[i][1]);
            }
            else {
                output[++index] = intervals[i];
            }
        }
        return Arrays.copyOf(output, index + 1);
    }
}