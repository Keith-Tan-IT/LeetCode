class SmallestInfiniteSet {
    PriorityQueue<Integer> heap;
    int current;
    public SmallestInfiniteSet() {
        heap  = new PriorityQueue<>();
        current = 1;
    }
    
    public int popSmallest() {
        int smallest = current;
        if(!heap.isEmpty() && heap.peek() < current) {
            smallest = heap.poll();
        }
        else {
            current++;
        }
        while (!heap.isEmpty() && heap.peek() == smallest) {
            heap.poll();
        }
        return smallest;
    }
    
    public void addBack(int num) {
        heap.offer(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
