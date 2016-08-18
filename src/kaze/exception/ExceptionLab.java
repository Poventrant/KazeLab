package kaze.exception;

import java.io.InterruptedIOException;

/**
 * Created by kaze on 16-6-4.
 */
public class ExceptionLab {
    public static void main(String[] args) {
        double d1 = -0.5;
        System.out.println(Math.ceil(d1));
        System.out.println(Math.floor(d1));
        try {
            test(true);
        } catch (InterruptedIOException | CustomerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("...........................");
        }
    }

    public static void test(boolean flag) throws Exception {
        if(flag) throw new CustomerException("CustomerException");
        else throw new Exception("Exception");
    }

    static class CustomerException extends RuntimeException {
        CustomerException(String msg) {
            super(msg);
        }
    }
}
