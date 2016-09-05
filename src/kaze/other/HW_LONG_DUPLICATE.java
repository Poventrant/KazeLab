package kaze.other;

/**
 * Created by kaze on 2016/9/5.
 ask not what your country  can do for you ,but what you can do for your  country
 最长的重复子序列：can do for you
 */
public class HW_LONG_DUPLICATE {
    public static void main(String[] args) {
        String line = "ask not what your country can do for you, but what you can do for your country";
        for (int i = line.length()-1; i >= 0; --i) { //长度，从大到小，确保第一个找到的是最长的
            for (int s = 0; s + i < line.length(); s++) { //开始节点+长度
                String temp = line.substring(s, s+i+1);
                int index1 = line.indexOf(temp);
                int index2 = line.lastIndexOf(temp);
                if(index1 != -1 && index1 != index2) {
                    System.out.println(temp);
                    System.exit(0);
                }
            }
        }
    }
}
