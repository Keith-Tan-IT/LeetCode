class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        int left = 0, right = n - 1;
        long total = 0;
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>();
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();

        while (k-- > 0) { 
            while (leftPQ.size() < candidates && left <= right) {
                leftPQ.offer(costs[left++]);
            }
            while (rightPQ.size() < candidates && left <= right) {
                rightPQ.offer(costs[right--]);
            }
            if (rightPQ.isEmpty() || !leftPQ.isEmpty() && leftPQ.peek() <= rightPQ.peek()) {

                total += leftPQ.poll();
                if (left <= right) {
                    leftPQ.add(costs[left++]);
                }
                //System.out.println(leftPQ);

            }
            else {
                //System.out.println(rightPQ);

                total += rightPQ.poll();
                if (left <= right) {
                    rightPQ.add(costs[right--]);
                }
            }
        }
        return total;
    }
}