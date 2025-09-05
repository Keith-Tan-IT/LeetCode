class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        int left = 0, right = n - 1;
        long total = 0;
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>();
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();

        for (int i = 0; i < candidates && left <= right; i++) {
            if (left == right) {
                leftPQ.add(costs[left++]);
                //System.out.println(leftPQ);
                break;
            }
            leftPQ.add(costs[left++]);
            rightPQ.add(costs[right--]);
            /*
            System.out.println(leftPQ);
            System.out.println(rightPQ);
            */
        }
        while (k-- > 0) { 
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