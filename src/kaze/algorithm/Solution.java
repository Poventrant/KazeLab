package kaze.algorithm;

import java.util.*;

public class Solution {

    public static void adjust(Map.Entry<Integer, Integer> [] entry, int index, int len) {
        while(true) {
            int minIndex = index * 2 + 1;
            if(minIndex >= len) break;
            if(minIndex + 1 < len && 
                entry[minIndex+1].getValue() < entry[minIndex].getValue()) {
                minIndex ++;
            }
            if(entry[minIndex].getValue() < entry[index].getValue()) {
                Map.Entry<Integer, Integer> temp = entry[minIndex];
                entry[minIndex] = entry[index];
                entry[index] = temp;
                index = minIndex;
            } else break;
        }
    }

    public static void build(Map.Entry<Integer, Integer> [] entry, int len) {
        int index = len / 2 - 1;
        for(int i = index; i >= 0; -- i) {
            adjust(entry, i, len);
        }
    }

    public static List<Integer> find(Map<Integer, Integer> map, int k) {
        int count = 0;
        Map.Entry<Integer, Integer>[] entries = new Map.Entry[k];
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(count < k) {
                entries[count ++] = entry;
            } else {
                if(count == k) {
                    build(entries, k);
                    count ++;
                }
                if(entries[0].getValue() < entry.getValue()) {
                    entries[0] = entry;
                    adjust(entries, 0, k);
                }
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        sort(entries);
        for( Map.Entry<Integer, Integer> e : entries) {
            list.add(e.getKey());
        }
        return list;
    }

    private static void sort(Map.Entry<Integer, Integer>[] entries) {
        int index = entries.length - 1;
        for (int i = 0; i < entries.length; i++) {
            Map.Entry<Integer, Integer> temp = entries[0];
            entries[0] = entries[index];
            entries[index] = temp;
            adjust(entries, 0, index --);
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(k);
        for(int i = 0; i < nums.length; ++ i) {
            Integer temp = map.get(nums[i]);
            if(temp == null) {
                map.put(nums[i], 1);
            } else map.put(nums[i], temp + 1);
        }

        return find(map, k);
    }

    public static void main(String[] args) {
        List<Integer> reuslt = topKFrequent(new int[] {1,1,1,2,2,3,4,8,9,1,2,6,8,7,9,8,7,4,5,6,6,6,6,6,8,7,9,1,2,3,6,4,9,7,3,1,1,3,4,6,7}, 3);
        for (Integer i : reuslt) {
            System.out.println(i);
        }
    }
}