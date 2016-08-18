package kaze.jiekou;

/**
 * Created by kaze on 16-8-18.
 */
public interface AccessTest {
    default void test() {
        //java 8 new profile
        int data [] = {
                1,2
        };
        System.out.println("test");
    }

}
