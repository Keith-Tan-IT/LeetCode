/**
 * TIP
 * Problem: 380. Insert Delete GetRandom O(1)
 *
 * Goal:
 * - insert(val): O(1)
 * - remove(val): O(1)
 * - getRandom(): O(1), uniform distribution
 *
 * -------------------------------------------------------------
 * Key Idea:
 * Use two data structures:
 * 1) ArrayList<Integer>     → O(1) random access
 * 2) HashMap<Integer,Index> → O(1) insert & delete tracking
 *
 * -------------------------------------------------------------
 * O(1) Delete Trick:
 * - To remove element at index i:
 *      swap(list[i], list[last])
 *      update map[lastValue] = i
 *      pop the last element
 * - This avoids O(n) shifting.
 *
 * -------------------------------------------------------------
 * Example:
 * Before: [10,4,7], remove 4
 * Swap 4 with 7 → [10,7,4]
 * Pop last → [10,7]
 * Update index of 7 → 1
 *
 * -------------------------------------------------------------
 * Complexity:
 * Time  = O(1) average for all operations
 * Space = O(n)
 *
 * -------------------------------------------------------------
 * Takeaways:
 * - HashMap + ArrayList = Classic design for O(1) operations.
 * - Always swap-with-last for O(1) delete in dynamic arrays.
 */

class RandomizedSet {
    List<Integer> list;
    Map<Integer, Integer> map;
    Random random;
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (list.contains(val)) {
            return false;
        }
        else {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int indexToRemove = map.get(val);
        int lastVal = list.get(list.size() - 1);

        list.set(indexToRemove, lastVal);
        map.put(lastVal, indexToRemove);

        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
