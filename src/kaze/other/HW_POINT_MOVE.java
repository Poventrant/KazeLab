package kaze.other;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaze on 2016/9/4.
 */
public class HW_POINT_MOVE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("^([ASDW])([1-9][\\d]?)$");
        while (scanner.hasNextLine()) {
            int x = 0, y = 0;
            String line = scanner.nextLine();
            String alters[] = line.split(";");
            for (String s : alters) {
                Matcher m = p.matcher(s);
                if(m.find()) {
                    int step = Integer.valueOf(m.group(2));
                    char dir = m.group(1).charAt(0);
                    switch (dir) {
                        case 'A' :
                            x -= step;
                            break;
                        case 'S' :
                            y -= step;
                            break;
                        case 'D' :
                            x += step;
                            break;
                        case 'W' :
                            y += step;
                            break;
                    }
                }
            }
            System.out.println(x + "," + y);
        }
    }
}
