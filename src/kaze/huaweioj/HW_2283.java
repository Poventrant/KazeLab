package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/8/14.
 */
public class HW_2283 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] opts = line.split(" +");
            if(opts.length == 1) {
                if("reset".matches(opts[0] + ".*")) {
                    System.out.println("reset what");
                } else {
                    System.out.println("unkown command");
                }
            } else if(opts.length == 2) {
                boolean f1 = "reset".matches(opts[0] + ".*")
                        && "board".matches(opts[1] + ".*");
                boolean f2 = "reboot".matches(opts[0] + ".*")
                        && "backplane".matches(opts[1] + ".*");

                boolean f3 = "board".matches(opts[0] + ".*")
                        && "add".matches(opts[1] + ".*");
                boolean f4 = "backplane".matches(opts[0] + ".*")
                        && "abort".matches(opts[1] + ".*");

                boolean f5 = "board".matches(opts[0] + ".*")
                        && "delete".matches(opts[1] + ".*");

                if((f1 && f2) || (f3 && f4)) {
                    System.out.println("unkown command");
                } else if(f1) {
                    System.out.println("board fault");
                } else if(f2) {
                    System.out.println("impossible");
                } else if(f3) {
                    System.out.println("where to add");
                } else if(f4) {
                    System.out.println("install first");
                } else if(f5) {
                    System.out.println("no board at all");
                } else {
                    System.out.println("unkown command");
                }
            } else {
                System.out.println("unkown command");
            }
        }
        scanner.close();
    }
}
