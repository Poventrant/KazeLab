package kaze.algorithm;

/**
 * Created by kaze on 2016/8/27.
 */
public class KMP {

    public static int[] getNext(String str) {
        int next[] = new int[str.length() + 1];
        int index = 0;
        next[index] = next[index+1] = 0;
        for (int i = 1; i < str.length(); i++) {
            while (index > 0 && str.charAt(i) != str.charAt(index)) {
                index = next[index];
            }
            if(str.charAt(i) == str.charAt(index)) ++ index;
            next[i+1] = index;
        }
        return next;
    }

    public static boolean contains(String str, String target) {
        if(str == null || target == null) return false;
        int next[] = getNext(target);
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            while (index > 0 && str.charAt(i) != target.charAt(index)) {
                index = next[index];
            }
            if(str.charAt(i) == target.charAt(index)) {
                ++ index;
            }
            if(index == target.length()) {
                System.out.println(i-target.length());
                index = next[index];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        contains("uiwaqhftrhaqw99fo89ihqewtrffo89ihqewtrf0e3tghfo89ihfofo89ihqewtrf89ihqewtrfj0hnewvgoieswfo89ihfo89ihqewtrfqewtrfbgiewh90rfthfo89ihqewtrfo89ihqewtrf","fo89ihqewtrf");
    }
}
