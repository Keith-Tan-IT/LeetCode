class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int[][] pairs = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0, max = 0;
        for (int i = 0; i < nums1.length; i++) {
            int num1 = pairs[i][0];
            int num2 = pairs[i][1];
            minHeap.add(num1);
            sum += num1;

            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }
            if (minHeap.size() == k) {
                max = Math.max(max, sum * num2);
            }
        }
        return max;
    }
}
