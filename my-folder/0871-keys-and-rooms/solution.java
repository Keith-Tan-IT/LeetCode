class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.add(0);
        seen.add(0);
        while (!stack.isEmpty()) {
            int i = stack.pop();
            for (int j : rooms.get(i)) {
                if (!seen.contains(j)) {
                    stack.add(j);
                    seen.add(j);
                }
            }
        }
        return rooms.size() == seen.size();
    }
}
