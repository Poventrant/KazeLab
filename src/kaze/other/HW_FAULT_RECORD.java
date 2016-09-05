package kaze.other;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HW_FAULT_RECORD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> map = new LinkedHashMap<>();
        Integer count;
        while (scanner.hasNext()) {
            String part0 = scanner.next();
            String fileNames[] = part0.split("\\\\");
            String fileName = fileNames[fileNames.length - 1];
            if (fileName.length() > 16) {
                fileName = fileName.substring(fileName.length() - 16);
            }
            String part1 = scanner.next();
            String key = fileName + " " + part1;
            if ((count = map.get(key)) != null)
                ++count;
            else
                count = 1;
            map.put(key, count);
        }
        int index = 0, len = map.size();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            ++index;
            if (index > len - 8) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
        }
    }
}
