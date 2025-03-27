class RecentCounter {
    int[] time;

    public RecentCounter() {
        time = new int[3001];
    }
    
    public int ping(int t) {
        int req = 0;
        time[t % 3001] = t;
        for (int i = 0; i < 3001; i++) {
            if (time[i] != 0 && time[i] > t - 3001) {
                req += 1;
            }
        }
        return req;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
