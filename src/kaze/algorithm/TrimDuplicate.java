package kaze.algorithm;

import java.util.*;

/**
 * Created by kaze on 2016/9/20.
 */
public class TrimDuplicate {

    static List<Integer> removeDuplicate(List<Integer> arr) {
        Set<Integer> set = new HashSet<>();
        for (Integer i : arr) {
            set.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (Integer i : arr) {
            if(set.contains(i)) {
                result.add(i);
                set.remove(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result  = removeDuplicate(Arrays.asList(1,1,1,2,2,3,1,2,3,1,5,6,9,4,5));
        for(Integer i : result) {
            System.out.println(i);
        }
    }
}
