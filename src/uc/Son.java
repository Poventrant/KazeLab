package uc;

import java.util.ArrayList;

/**
 * Created by kaze on 16-4-25.
 */
public class Son extends Farther<Test> {

    Son() {
        super();
        Class<Test> e = this.entityClass;
    }

    public static void main(String[] args) {
        new Son();
    }


    public ArrayList test() {
        return null;
    }
}
