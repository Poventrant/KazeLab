package kaze.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kaze on 16-5-28.
 */
public class ThreadSafeDateParser implements Runnable{

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
//        String str = "1995-03-17";
//        try {
//            Date date = sdf.parse(str);
//            System.out.println(date.getTime());
//            System.out.println(sdf.format(System.currentTimeMillis()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < 20; i++) {
            new Thread(new ThreadSafeDateParser()).start();
        }
    }

    @Override
    public void run() {
        synchronized (sdf) {
            String str = "1995-03-17";
            Date date = null;
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("-------------- getTime -----------" + date.getTime());
            System.out.println(sdf.format(System.currentTimeMillis()));
        }
    }
}
