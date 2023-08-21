/*
 *
 * Fenwick Tree
 * Binary Indexed Tree
 * 树状数组
 */
public class BIT {

  static int[] a;

  static int lowbit(int x) {
    return (x & (-x));
  }

  static void update(int i, int val) {
    a[i] = val;
  }

  static void query(int val) {

  }

  static int prefixSum(int k) {

    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += a[i];
    }
    return sum;
  }

  public static void main(String[] args) {
    System.out.println("BIT:");
    a = new int[101];
    for (int i = 0; i < 101; i++) {
      a[i] = i;
    }
    System.out.println(prefixSum(101));
  }

}
