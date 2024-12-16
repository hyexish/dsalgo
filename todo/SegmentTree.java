
/**
 * SegmentTree
 */
public class SegmentTree {

    private int[] tree;

    public SegmentTree(int n) {
        tree = new int[4 * n];
    }
    public void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }
        int mid = start + (end - start) / 2;
        int left = 2 * node + 1;
        int right = 2 * node + 2;
        if (start <= index && index <= mid) {
            update(left, start, mid, index, value);
        } else {
            update(right, mid + 1, end, index, value);
        }
        tree[node] = tree[left] + tree[right];
    }
    public int query(int node, int start, int end, int L, int R) {
        if (L <= start && end <= R) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        int left = 2 * node + 1;
        int right = 2 * node + 2;
        int sum = 0;
        if (L <= mid) {
            sum += query(left, start, mid, L, R);
        }
        if (R > mid) {
            sum += query(right, mid + 1, end, L, R);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 7, 9, 11 };
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            st.update(0,0,n -1,i, nums[i]);
        }
        System.out.println(st.query(0, 0, n - 1, 1, n - 1));
    }
}
