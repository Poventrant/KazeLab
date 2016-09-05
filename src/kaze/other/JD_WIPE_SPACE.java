package kaze.other;

/**
 * Created by kaze on 2016/9/5.
 * 给定字符串（ASCII码0-255）数组，请在不开辟额外空间的情况下删除开始和结尾处的空格，并将中间的多个连续的空格合并成一个。例如：
 * "   i    am a      little boy.    "，变成
 * "i am a little boy",语言不限，但不要用伪代码作答，函数输入输出请参考如下的函数原型：
 */
public class JD_WIPE_SPACE {
    public static void main(String[] args) {
        StringBuilder line = new StringBuilder("   i    am a      little boy.    ");
        int l = 0, h = 0, len = line.length();
        while(line.charAt(h) == ' ') ++h;
        while(h < len) {
            char temp = line.charAt(h);
            if (temp != ' ' || (h + 1 < len && line.charAt(h + 1) != ' ')) {
                line.setCharAt(l, temp);
                ++l;
            }
            ++h;
        }
        System.out.println(line.substring(0, l));
    }
}
