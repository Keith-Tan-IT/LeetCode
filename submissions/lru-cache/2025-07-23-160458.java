class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node (int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
    Node head = new Node(0,0), tail = new Node(0,0);
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void addNode (Node newNode) {
        Node temp = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = temp;
        temp.prev = newNode;
    }

    public void deleteNode (Node delNode) {
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            deleteNode(node);
            addNode(node);
            return node.value;
        }
        else {
            return -1; 
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteNode(map.get(key));
        }
        Node newNode = new Node(key, value);
        addNode(newNode);
        map.put(key, newNode);

        if (map.size() > capacity) {
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */