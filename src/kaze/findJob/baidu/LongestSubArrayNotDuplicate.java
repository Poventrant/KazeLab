package kaze.findJob.baidu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kaze on 2016/9/25.
 * 字符串最长无重复子串
 */
public class LongestSubArrayNotDuplicate {

    static String find(String str) {
        if (str == null || str.length() == 0) return null;
        char arr[] = str.toCharArray();
        int maxStart = 0, maxLen = 1;
        for (int k = 0; k < arr.length; k++) {
            Set<Character> set = new HashSet<>();
            set.add(arr[k]);
            int start = k, len = 1;
            for (int i = k; i < arr.length; i++) {
                if (!set.contains(arr[i])) {
                    set.add(arr[i]);
                    ++len;
                } else {
                    set.clear();
                    set.add(arr[i]);
                    start = i;
                    len = 1;
                }
                if (maxLen < len) {
                    maxLen = len;
                    maxStart = start;
                }
            }
            set.clear();
        }
        return str.substring(maxStart, maxStart + maxLen);
    }

    /**
     * 复杂度 O(n)
     * @param str
     * @return
     */
    private static String findNoDul(String str) {
        int len = 0;
        if (str == null || (len = str.length()) == 0) return null;
        char arr[] = str.toCharArray();
        int map[] = new int[256];
        Arrays.fill(map, len);
        int next[] = new int[len], first[] = new int[len + 1];
        first[len] = len;
        for (int i = len - 1; i >= 0; i--) {
            next[i] = map[arr[i]];
            map[arr[i]] = i;
            if (next[i] < first[i + 1])
                first[i] = next[i];
            else
                first[i] = first[i + 1];
        }
        int maxlen = 0, begin = 0;
        for (int i = 0; i < len; i++) {
            if (first[i] - i > maxlen) {
                maxlen = first[i] - i;
                begin = i;
            }
        }
        return str.substring(begin, maxlen + 1);
    }

    public static void main(String[] args) {
        String str = "1234156789";
        System.out.println(findNoDul(str));
    }
}
