class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List <int[]> result = new LinkedList<>();
        int[][] newArray = new int[intervals.length + 1][2];
        int a = 0, count = 0;
        boolean inserted = false;
        for (a = 0; a < intervals.length; a++) {                   
            if (inserted == false && newInterval[0] < intervals[a][0]) {
                newArray[a] = newInterval;
                inserted = true;
            }
            else { 
                newArray[a] = intervals[count++];
            }
        }
        if (!inserted) {
            newArray[a] = newInterval;
        }
        else {
            newArray[a] = intervals[count];
        }
        int[] prev = newArray[0];
        result.add(prev);
        for (int i = 1; i < newArray.length; i++) {
            if (newArray[i][0] <= result.get(result.size() - 1)[1]) {
                prev[1] = Math.max(prev[1], newArray[i][1]);
            }
            else {
                prev = newArray[i];
                result.add(prev);
            }
        }
        return result.toArray(new int[0][]);

    }
}