package kaze.aop;

/**
 * Created by kaze on 2016/7/25.
 */
public class Main {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Kaze kaze = (Kaze) proxy.bind(KazeImpl.class);
        kaze.say();
    }
}
