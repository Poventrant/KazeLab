package kaze.algorithm;

public class KazeTrie {

    final Node root = new Node();

    public void build(String str) {
        int len = str.length();
        Node p = root;
        for (int i = 0; i < len; i++) {
            int index = str.charAt(i) - '0';
            if(p.next[index] == null) {
                Node child = new Node();
                p.next[index] = child;
                p.value = 1;
            } else {
                p.next[index].value ++;
            }
            p = p.next[index];
        }
        p.value = -1;
    }

    public int find(String str) {
        int len = str.length();
        Node p = root;
        for (int i = 0; i < len; i++) {
            int index = str.charAt(i) - '0';
            p = p.next[index];
            if(p == null) return 0;
            if(p.value == -1) return -1;
        }
        return 1;
    }

    static class Node {
        private static final int MAX = 256;
        int value;
        Node next[];

        public Node() {
            value = -1;
            next = new Node[MAX];
        }
    }

    public static void main(String[] args) {
        KazeTrie t = new KazeTrie();
        t.build("eiewnbhfougehwg90tryh2403wtrhoiehwfojhb89ekjrhbo9u23yrhf803ehr8923y589h2weofrhosehfr893qfryhpiwafhjilsahjf89y2389tryhfoiewsahnglsahewfuitygw9qorfholejbhfkjgawq79r28935trghbekewr08923htoih23wtbg3kiqwftbghoeoaq3wght89oq3tgho83ewhto89gew");
        t.build("f32e532tewdhuth89032uyt0893ghdfrgh4r4e54ty5443e2ht");
        t.build("gdfsgr436y43yh45reyhrefhbrdey3486u");
        t.build("f436tgoiefgjoiedsjg95i8932htgreyghdsfgds");
        t.build("54oituhjpdfmgbcdbdfbreojty04308932ht");
        t.build("oi3etji43ujt0i43u90tt");
        t.build("f32e53252352352353bfdbhfdt");
        t.build("tewdhuth89032uyt0893325423523bt08932ht");

        System.out.println(t.find("oi3etji43ujt0i43u90t"));
    }
}
