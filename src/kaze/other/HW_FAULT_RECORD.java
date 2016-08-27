package kaze.other;

import java.util.*;

public class HW_FAULT_RECORD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        List<String> queue = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String parts[] = line.split(" +");
                String key = parts[0]+"_"+parts[1];
                Integer count = map.remove(key);
                if(count == null) {
                    count = 1;
                } else {
                    ++ count;
                }
                queue.add(key);
                map.put(key, count);
            }
            for (Map.Entry<String, Integer> e : map.entrySet()) {

            }
            int begin = queue.size()-8<0?0:queue.size()-8;
            for (int i = begin; i < queue.size(); i++) {
                String fileNames[] = queue.get(i).split("\\\\");
                String fileName = fileNames[fileNames.length-1];
                String result[] = fileName.split("_");
                if(result[0].length() > 16) {
                    result[0] = result[0].substring(result[0].length() - 16);
                }
                System.out.println(result[0] + " " + result[1] + " " + map.get(queue.get(i)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
