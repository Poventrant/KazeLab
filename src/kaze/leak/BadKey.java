package kaze.leak;

import java.util.Map;

public class BadKey {
   // no hashCode or equals();
   public final String key;
   public BadKey(String key) { this.key = key; }

    public static void main(String[] args) {
        Map map = System.getProperties();
        while (true) {
            map.put(new BadKey("key"), "value"); // Memory leak even if your threads die.
        }
    }
}