public class FenwickTree {

    private int[] tree;

    public int lowbit(int i) {
        return i & -i;
    }

    public FenwickTree(int[] nums) {
        tree = new int[nums.length + 1];
        build(nums);
    }

    public void build(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            update(i + 1, nums[i]);
        }
    }

    public void update(int index, int value) {
        for (int i = index; i < tree.length; i += lowbit(i)) {
            tree[i] += value;
        } 
    }

    public int query(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }

    public int rangeQuery(int L, int R) {
        return query(R) - query(L - 1);
    }


    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 7, 9, 11 };
        FenwickTree ft = new FenwickTree(nums);
        for (int i = 1; i <= nums.length; i++) {
            System.err.printf("tree[%d] = %d\n", i, ft.tree[i]);
        }
        System.out.println(ft.rangeQuery(2,6));
    }
}
