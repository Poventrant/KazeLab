package kaze.other;

import java.util.*;

/**
 http://www.nowcoder.com/practice/a386fd3a5080435dad3252bac76950a7?tpId=49&tqId=29280&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_FIND_CODER {
    static final String CODER = "coder";

    static class SortStr {
        String str;
        int count;

        public SortStr(String s, int count) {
            this.str = s;
            this.count = count;
        }
    }

    static int kmp(String A) {
        int index = 0, count = 0;
        for (int i = 0; i < A.length(); i++) {
            char tc = Character.toLowerCase(A.charAt(i));
            if (index > 0 && tc != CODER.charAt(index)) {
                index = 0;
            }
            if (tc == CODER.charAt(index)) ++index;
            if(index == CODER.length()) {
                ++count;
                index = 0;
            }
        }
        return count;
    }

    public String[] findCoder(String[] A, int n) {
        List<SortStr> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if((count = kmp(A[i])) != 0) {
                SortStr ss = new SortStr(A[i], count);
                result.add(ss);
            }
        }
        Collections.sort(result, new Comparator<SortStr>() {
            @Override
            public int compare(SortStr o1, SortStr o2) {
                return (o1.count > o2.count) ? -1 : ((o1.count == o2.count) ? 0 : 1);
            }
        });
        String [] strs = new String[result.size()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = result.get(i).str;
        }
        return strs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NOWCODER_FIND_CODER().findCoder(new String[]{
                "i am a coder", "Coder Coder", "Code"
        }, 3)));
    }
}
