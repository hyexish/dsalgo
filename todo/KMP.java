public class KMP {
    // 生成部分匹配表（前缀表）
    public static int[] buildPrefixTable(String pattern) {
        int m = pattern.length();
        int[] prefixTable = new int[m];
        int j = 0; // j 指向前缀的末尾位置

        // 前缀表第一个位置永远是0（单字符没有匹配失败的前缀）
        for (int i = 1; i < m; i++) {
            // 回溯：当前字符不匹配前缀，移动 j 位置
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixTable[j - 1];
            }
            // 匹配上则增加前缀长度
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefixTable[i] = j;
        }
        return prefixTable;
    }

    // KMP 搜索算法
    public static int KMPsearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0; // 空字符串直接返回0

        int[] prefixTable = buildPrefixTable(pattern);
        int j = 0; // j 是模式串的匹配长度

        for (int i = 0; i < n; i++) {
            // 当 text[i] 不匹配 pattern[j] 时，进行部分匹配表回退
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixTable[j - 1];
            }
            // 匹配上，则继续下一个字符
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // 如果完整匹配了 pattern
            if (j == m) {
                return i - m + 1; // 返回匹配的起始位置
            }
        }
        return -1; // 未找到匹配
    }


    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        int position = KMPsearch(text, pattern);

        if (position != -1) {
            System.out.println("Pattern found at position: " + position);
        } else {
            System.out.println("Pattern not found.");
        }
    }

}
