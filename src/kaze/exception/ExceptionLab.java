package kaze.exception;

/**
 * Created by kaze on 16-6-4.
 */
public class ExceptionLab {
    public static void main(String[] args) {
        try {
            test(true);
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test(boolean flag) throws Exception {
        if(flag) throw new CustomerException("CustomerException");
        else throw new Exception("Exception");
    }

    static class CustomerException extends Exception {
        CustomerException(String msg) {
            super(msg);
        }
    }
}
