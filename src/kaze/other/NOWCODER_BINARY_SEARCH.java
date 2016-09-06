package kaze.other;

/**
 * Created by kaze on 2016/9/6.
 */
public class NOWCODER_BINARY_SEARCH {

    public char findFirstRepeat(String A, int n) {
        int mask[] = new int[256];
        char tmp;
        for (int i = 0; i < A.length(); i++) {
            tmp = A.charAt(i);
            ++mask[tmp];
            if(mask[tmp] == 2) {
                return tmp;
            }
        }
        return '\0';
    }

    public int getPos(int[] A, int n, int val) {
        int l = 0, h = n-1, mid;
        while(l<=h) {
            mid = (l+h)>>1;
            if(A[mid] < val)
                l=mid+1;
            else if(A[mid]>val)
                h=mid-1;
            else {
                while(mid-1>=0 && A[mid-1] == val) {
                    --mid;
                }
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new NOWCODER_BINARY_SEARCH().getPos(new int[] {
                1,2,3,4,5,6,7,7,8,9,10
        },11, 7 ));
    }
}
