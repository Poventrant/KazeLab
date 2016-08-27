package kaze.huaweioj;
import java.util.*;

public class HW_2282 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparator comparator = new Comparator<Byte[]>() {
            @Override
            public int compare(Byte[] o1, Byte[] o2) {
                int res;
                for (int i = 0; i < o1.length; i++) {
                    if ((res = o1[i].compareTo(o2[i])) != 0) {
                        return res;
                    }
                }
                return 0;
            }
        };
        int times = scanner.nextInt();
        Byte trains[] = new Byte[times];
        for (int i = 0; i < times; i++) {
            trains[i] = scanner.nextByte();
        }
        Byte origin[] = new Byte[times];
        System.arraycopy(trains, 0, origin, 0, times);
        Arrays.sort(trains);

        List<Byte[]> container = new ArrayList<>();

        randstr(trains, 0, origin, container);

        Collections.sort(container, comparator);

        for (int i = 0; i < container.size(); i++) {
            Byte[] tmp = container.get(i);
            for (int j = 0; j < tmp.length - 1; j++) {
                System.out.print(tmp[j] + " ");
            }
            System.out.println(tmp[tmp.length - 1]);
        }
    }

    private static void randstr(Byte[] trains, int index, final Byte[] origin, final List<Byte[]> container) {
        if (!isStackOrder(trains, origin, index)) return;
        if (index == trains.length) {
            Byte[] temp = new Byte[trains.length];
            System.arraycopy(trains, 0, temp, 0, trains.length);
            container.add(temp);
        } else {
            for (int i = index; i < trains.length; i++) {
                byte temp = trains[index];
                trains[index] = trains[i];
                trains[i] = temp;

                randstr(trains, index + 1, origin, container);

                trains[i] = trains[index];
                trains[index] = temp;
            }
        }
    }

    private static boolean isStackOrder(Byte[] trains, final Byte[] origin, int maxLen) {
        if (maxLen >= 1) {
            Stack<Byte> stack = new Stack<>();
            int tp = 0, op = 0;
            while (true) {
                if (!stack.isEmpty() && stack.top() == trains[tp]) {
                    stack.pop();
                    ++tp;
                    if (tp == maxLen) break;
                } else if (op < origin.length) {
                    stack.push(origin[op++]);
                } else return false;
            }
            return true;
        }
        return true;
    }

    private static class Stack<E> {
        private List<E> list = new LinkedList<>();

        public E top() {
            return list.get(0);
        }

        public E pop() {
            return list.remove(0);
        }

        public void push(E e) {
            list.add(0, e);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}

