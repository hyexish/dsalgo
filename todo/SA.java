import java.util.Arrays;

/**
 * Suffix Array
 * 后缀数组
 */
public class SA {

    public static Integer[] sa; 
    public static int[] rank; 
    public static int[] tmp;
    public static int[] height;

    public static void init(int N) {
         sa = new Integer[N]; 
         sa[0] = 0;
         height = new int[N]; 
         rank = new int[N]; 
         tmp = new int[N];
    } 

    public static void build(char[] s) {
        int n = s.length - 1;
        for (int i = 1; i <= n; i++) {
            sa[i] = i;
            rank[i] = s[i];
        }
        for (int w = 1; w <= n; w <<= 1) {
            final int fw = w;
            Arrays.sort(sa, 1, n + 1, (x, y) -> rank[x] == rank[y] ? rank[x + fw] - rank[y + fw] : rank[x] - rank[y]);
            System.arraycopy(rank, 0, tmp, 0, rank.length);
            for (int i = 1, j = 0; i <= n; i++) {
                if (tmp[sa[i]] == tmp[sa[i - 1]] && tmp[sa[i] + fw] == tmp[sa[i - 1] + fw]) {
                    rank[sa[i]] = j;
                } else {
                    rank[sa[i]] = ++j;
                }
            }
        }
    }

    public static void lcp(char[] s) {
        int n = s.length - 1;
        for (int i = 1, k = 0; i <= n; i++) {
            if (k > 0) k--;
            int j = sa[rank[i] - 1];
            while (i + k <= n && j + k <= n && s[i + k] == s[j + k]) k++;
            height[rank[i]] = k;
        }
    }

    public static void main(String[] args) {
	    char s[] = "$aabaaaabbbbbb".toCharArray();
	    // char s[] = "$aabaaaab".toCharArray();
        init(s.length + 1);
        build(s);
        lcp(s);
        System.out.print("suffix: ");
        for (int i = 1; i < s.length; i++) {
            System.out.print(sa[i] + ", ");
        }
        System.out.println();
        System.out.print("rank:   ");
        for (int i = 1; i < s.length; i++) {
            System.out.print(rank[i] + ", ");
        }
        System.out.println();
        System.out.print("height: ");
        for (int i = 1; i < s.length; i++) {
            System.out.print(height[i] + ", ");
        }
    }
}
