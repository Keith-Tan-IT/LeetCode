class SmallestInfiniteSet {
    private PriorityQueue<Integer> heap;
    int current_min;

    public SmallestInfiniteSet() {
        heap = new PriorityQueue<>();
        current_min = 1;
    }
    
    public int popSmallest() {
        if(!heap.isEmpty()) {
            return heap.poll();
        }
        else {
            current_min++;
            return current_min - 1;
        }
    }
    
    public void addBack(int num) {
        if(num < current_min && !heap.contains(num)) {
            heap.offer(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
