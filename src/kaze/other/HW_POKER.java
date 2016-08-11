package kaze.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
http://www.nowcoder.com/practice/d290db02bacc4c40965ac31d16b1c3eb?tpId=37&tqId=21311&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
@SuppressWarnings("all")
public class HW_POKER {

    final static Map<String, Integer> maping = new HashMap<String, Integer>() {{
        /*3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER*/
        put("3", 0);put("4", 1);put("5", 2);put("6", 3);put("7", 4);put("8", 5);
        put("9", 6);put("10", 7);put("J", 8);put("Q", 9);put("K", 10);put("A", 11);
        put("2", 12);put("joker", 13);put("JOCKER", 14);
    }};


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line == null || line.equals("")) break;
            String pokers[] = line.split("-");
            String left[] = pokers[0].split(" ");
            String right[] = pokers[1].split(" ");
            if(isBomb(left)) {

            }
            if(left.length == 1 || right.length == 1) { //个子
                if(left.length != right.length) {
                    if(isBomb(left) || isJoker(left)) {
                        System.out.println(pokers[0]);
                    } else if(isBomb(right) || isJoker(right)) {
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                } else {
                    if(maping.get(left[0]) > maping.get(right[0])) {
                        System.out.println(pokers[0]);
                    } else if(maping.get(left[0]) < maping.get(right[0])){
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                }
            } else if(left.length == 3 || right.length == 3) {
                if(left.length != right.length) {
                    if(isBomb(left) || isJoker(left)) {
                        System.out.println(pokers[0]);
                    } else if(isBomb(right) || isJoker(right)) {
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                } else {
                    if(maping.get(left[0]) > maping.get(right[0])) {
                        System.out.println(pokers[0]);
                    } else if(maping.get(left[0]) < maping.get(right[0])){
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                }
            } else if(left.length == 5 || right.length == 5) {
                if(left.length != right.length) {
                    if(isBomb(left) || isJoker(left)) {
                        System.out.println(pokers[0]);
                    } else if(isBomb(right) || isJoker(right)) {
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                } else {
                    if(maping.get(left[0]) > maping.get(right[0])) {
                        System.out.println(pokers[0]);
                    } else if(maping.get(left[0]) < maping.get(right[0])){
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                }
            } else if(left.length == 2 || right.length == 2) { //对子 王
                if(left.length != right.length) { // 2 4
                    if(isJoker(left)) {
                        System.out.println(pokers[0]);
                    } else if(isJoker(right)){
                        System.out.println(pokers[1]);
                    } else if(isBomb(left)) {
                        System.out.println(pokers[0]);
                    } else if(isBomb(right)) {
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                } else {
                    if(isJoker(left)) {
                        System.out.println(pokers[0]);
                    } else if(isJoker(right)) {
                        System.out.println(pokers[1]);
                    } else if(maping.get(left[0]) > maping.get(right[0])) {
                        System.out.println(pokers[0]);
                    } else if(maping.get(left[0]) < maping.get(right[0])){
                        System.out.println(pokers[1]);
                    } else {
                        System.out.println("ERROR");
                    }
                }
            } else if(left.length == 4 || right.length == 4) {  //炸弹
                if(maping.get(left[0]) > maping.get(right[0])) {
                    System.out.println(pokers[0]);
                } else if(maping.get(left[0]) < maping.get(right[0])){
                    System.out.println(pokers[1]);
                } else {
                    System.out.println("ERROR");
                }
            }
        }
    }

    private static boolean isBomb(String[] left) {
        if(left.length != 4) return false;
        for (int i = 0; i < left.length - 1; i++) {
            if(!left[i].equals(left[i+1])) return false;
        }
        return true;
    }

    private static boolean isJoker(String[] left) {
        if(left.length != 2) return false;
        return left[0].toLowerCase().equals("joker")
                && left[1].toLowerCase().equals("joker");
    }
}
