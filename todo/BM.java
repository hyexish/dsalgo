public class BM {

    private int[] badCharTable;  // 坏字符规则表
    private int[] goodSuffixTable;  // 好后缀规则表
    private String pattern;
    
    public BM(String pattern) {
        this.pattern = pattern;
        this.badCharTable = createBadCharTable(pattern);
        this.goodSuffixTable = createGoodSuffixTable(pattern);
    }

    private int[] createBadCharTable(String pattern) {
        int[] table = new int[256];
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;  // 记录字符的最后出现位置
        }
        return table;
    }

    private int[] createGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] table = new int[m];
        int[] suffixes = new int[m];
        
        // 初始化
        for (int i = 0; i < table.length; i++) {
            table[i] = m;
        }
        for (int i = m - 1, j = m; i >= 0; i--) {
            if (i == m - 1 || suffixes[i + 1] == 0) {
                j = i + 1;
            }
            suffixes[i] = j;
            while (j < m && pattern.charAt(i) != pattern.charAt(m - 1 - j)) {
                j++;
            }
        }
        for (int i = 0; i < m - 1; i++) {
            table[m - 1 - suffixes[i]] = m - 1 - i;
        }
        return table;
    }

    public int search(String text) {
        int n = text.length();
        int m = pattern.length();
        int s = 0;  // s 是模式相对于文本的滑动位置

        while (s <= n - m) {
            int j = m - 1;
            
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            if (j < 0) {
                return s;  // 找到匹配的位置
            } else {
                int badCharShift = j - badCharTable[text.charAt(s + j)];
                int goodSuffixShift = goodSuffixTable[j];
                s += Math.max(badCharShift, goodSuffixShift);  // 选择较大的移动
            }
        }
        return -1;  // 未找到匹配
    }

    public static void main(String[] args) {
        String text = "ABAAABCD";
        String pattern = "ABC";
        
        BM bm = new BM(pattern);
        int position = bm.search(text);
        
        if (position != -1) {
            System.out.println("Pattern found at position: " + position);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}
