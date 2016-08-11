package kaze.other;

import java.util.Scanner;

/**
 3
 A B C

 */
public class HW_TRAIN_STATION {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int times = scanner.nextInt();
            char trains[] = new char[times];
            for (int i = 0; i < times; i++) {
                trains[i] = scanner.next().charAt(0);
            }
            randstr(trains, 0);
        }
    }

    private static void randstr(char[] trains, int index) {
        if (index == trains.length) {
            for (int i = 0; i < trains.length; i++) {
                System.out.print(trains[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = index; i < trains.length; i++) {
                char temp = trains[index];
                trains[index] = trains[i];
                trains[i] = temp;

                char next[] = new char[trains.length - index - 1];
                System.arraycopy(trains, index, next, 0, next.length);
                randstr(next, index + 1);
                randstr(trains, index + 1);

                trains[i] = trains[index];
                trains[index] = temp;
            }
        }
    }
}
