public class SegmentTree2 {
    public static class Node {
        int value;
        Node left, right;
    }
    public Node root;
    public SegmentTree2() {
        this.root = new Node();
    }

    public void update(Node node, int start, int end, int index, int value) {
        if (start == end) {
            node.value = value;
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            if (node.left == null) {
                node.left = new Node();
            }
            update(node.left, start, mid, index, value);
        } else {
            if (node.right == null) {
                node.right = new Node();
            }
            update(node.right, mid + 1, end, index, value);
        }
        int leftSum = node.left == null ? 0 : node.left.value;
        int rightSum = node.right == null ? 0 : node.right.value;
        node.value = leftSum + rightSum;
    }

    public int query(Node node, int start, int end, int L, int R) {
        if (node == null || L > end || R < start) {
            return 0;
        }
        if (L <= start && end <= R) {
            return node.value;
        }
        int mid = start + (end - start) / 2;
        int leftSum = query(node.left, start, mid, L, R);
        int rightSum = query(node.right, mid + 1, end, L, R);
        return leftSum + rightSum;
    }

    public void traversal(Node node) {
        if (node==null) return;
        System.out.println(node.value); 
        traversal(node.left);
        traversal(node.right);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 7, 9, 11 };
        int n = nums.length;
        SegmentTree2 st = new SegmentTree2();
        for (int i = 0; i < n; i++) {
            st.update(st.root,0,n -1,i, nums[i]);
        }
        System.out.println(st.query(st.root, 0, n - 1, 5, n - 1));
    }
}