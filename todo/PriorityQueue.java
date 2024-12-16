
public class PriorityQueue {

    int[] heap = new int[1000];
    int size = 0;

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void sort(int[] heap, int k) {
        for (int i = (k - 1) >> 1; i >= 0; i--) {
            siftDown(heap, i, k);
        }
        for (int i = k; i > 0; i--) {
            swap(heap, 0, i);
            siftDown(heap, 0, i);
        }
    }
    public void siftDown(int[] heap, int i, int k) {
        int left = (i << 1)+ 1;
        int right = (i << 1) + 2;
        int j = i;
        if (left <= k && heap[left] < heap[j]) j = left;
        if (right <= k && heap[right] < heap[j]) j = right;
        if (j != i) {
            swap(heap, i, j);
            siftDown(heap, j, k);
        }
    }

    public void siftUp(int[] heap, int i) {
        int parent = (i - 1) >> 1;
        int j = i;
        if (parent >= 0 && heap[parent] > heap[j]) j = parent;
        if (j != i) {
            swap(heap, i, j);
            siftUp(heap, j);
        }
    }


    public int add(int val) {
        heap[size++] = val;
        siftUp(heap, size - 1);
        return heap[0];
    }

    public void pop() {
        heap[0] = heap[--size];
        siftDown(heap, 0, size - 1);
    }

    public static void main(String[] args) {
        PriorityQueue que = new PriorityQueue();
        System.out.println(que.add(4));
        System.out.println(que.add(5));
        System.out.println(que.add(8));
        System.out.println(que.add(2));
        que.pop();
    }
}
