import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    final Map<Integer, Node> map;
    final DoubleList list;
    final int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.list = new DoubleList();
        this.capacity = capacity;
    }

    public void removeLeastRecently() {
        Node node = list.removeFirst();
        map.remove(node.key);
    }

    public void makeRecently(int key) {
        Node node = map.get(key);
        list.remove(node);
        list.addLast(node);
    }

    public void makeRecently(int key, int value) {
        Node node = map.get(key);
        node.value = value;
        list.remove(node);
        list.addLast(node);
    }

    public void addRecently(int key, int value) {
        Node node = new Node(key, value);
        map.put(key, node);
        list.addLast(node);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            makeRecently(key, value);
            return;
        }
        if (capacity == list.size) {
            removeLeastRecently();
        }
        addRecently(key, value);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).value;
    }

    public void print() {
        Node p = list.head.next;
        while (p != list.tail) {
            System.out.printf("key: %d, value: %d\n", p.key, p.value);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.get(2);
        lru.put(4, 5);
        lru.get(3);
        lru.put(1, 1);
        lru.print();
    }
}

class DoubleList {
    Node head;
    Node tail;
    int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(Node node) {
        size++;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public void remove(Node node) {
        size--;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public Node removeFirst() {
        if (head.next == tail)
            return null;
        Node first = head.next;
        remove(first);
        return first;
    }
}

class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
