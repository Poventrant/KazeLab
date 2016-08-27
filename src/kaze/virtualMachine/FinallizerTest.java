package kaze.virtualMachine;

/**
 * Created by kaze on 2016/8/27.
 */
public class FinallizerTest {

    static FinallizerTest hooker = null;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finallizer");
        hooker = this;
    }

    public static void main(String[] args) {
        hooker = new FinallizerTest();
        hooker = null;
        System.gc();
        System.out.println(hooker == null);

        hooker = null;

        System.gc();
        System.out.println(hooker == null);
    }
}
