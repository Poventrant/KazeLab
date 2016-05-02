package com.string.test;


class StringLab {
    //最长公共字符串
    public static String test1 = "ttttttttttxqwrqfvhtrucxsarebfvhtrujtryhxcvbv";
    public static String test2 = "gguvhtrucxtttttttttcxtttttttttx";
    public static void main(String[] args) {
        for(int len = test2.length(); len > 0; -- len) {
            for(int start = 0; start + len <= test2.length(); ++ start) {
                String target = test2.substring(start, start+len);
                if(test1.contains(target)) {
                    System.out.println(target);
                    return;
                }
            }
        }
    }

}