/**
 * TIP
 * Problem: 2144. Minimum Cost of Buying Candies With Discount
 *
 * Summary:
 * - You can buy any 3 candies and get the cheapest one free.
 * - Minimize total cost to buy all candies.
 *
 * ----------------------------------------------------------------
 * Approach:
 * 1️⃣ Sort `cost` in ascending order.
 * 2️⃣ Start from the most expensive (end of array).
 * 3️⃣ Pay for 2 candies, skip the 3rd one (free).
 * 4️⃣ Repeat until all candies are processed.
 *
 * ----------------------------------------------------------------
 * Example:
 * cost = [6,5,7,9,2,2]
 * Sort → [2,2,5,6,7,9]
 * Groups → (9,7,6) skip 6 → pay 9+7
 *           (5,2,2) skip 2 → pay 5+2
 * Total = 23
 *
 * ----------------------------------------------------------------
 * Implementation:
 * class Solution {
 *     public int minimumCost(int[] cost) {
 *         Arrays.sort(cost);
 *         int total = 0, n = cost.length;
 *         for (int i = n - 1; i >= 0; i--) {
 *             if ((n - 1 - i) % 3 != 2) total += cost[i];
 *         }
 *         return total;
 *     }
 * }
 *
 * ----------------------------------------------------------------
 * Complexity:
 * Time  = O(n log n)
 * Space = O(1)
 *
 * ----------------------------------------------------------------
 * Key Takeaways:
 * - Always sort and process from highest price.
 * - Skip every 3rd candy to simulate “buy 2, get 1 free.”
 * - Clean O(n log n) greedy solution.
 */
class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int total = 0, n = cost.length;
        for (int i = n - 1; i >= 0; i--) {
            if ((n - i - 1) % 3 != 2) {
                total += cost[i];
            }
        }
        return total;
    }
}
