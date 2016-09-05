package kaze.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by kaze on 2016/8/31.
 */
public class InvoiceTest {
    public static void main(String[] args) {
        List<String> engines = new ArrayList<String>();
        engines.add("http://www.google.com/?q=");
        engines.add("http://duckduckgo.com/?q=");
        engines.add("http://www.bing.com/search?q=");

        // get element as soon as it is available
        Optional<String> result = engines.stream().parallel().map((base) -> {
            // open connection and fetch the result
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return base;
        }).findAny();
        System.out.println(result.get());
    }
}
