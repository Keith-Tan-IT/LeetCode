class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            }
            else {
                dire.add(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r = radiant.poll();
            int d = dire.poll();
            if (r > d) {
                dire.add(senate.length() + r);
            }
            else {
                radiant.add(senate.length() + d);
            }
        }
        return !dire.isEmpty() ? "Dire" : "Radiant";
    }
}
