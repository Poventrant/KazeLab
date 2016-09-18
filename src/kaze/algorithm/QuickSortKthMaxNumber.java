package kaze.algorithm;

import java.util.Random;

public class QuickSortKthMaxNumber {
	
	//快速排序实现部分排序，找出前K个大的数

    public static int partition(int [] sort, int left, int right) {
//	    if(right - left <= 7) {
//            for (int i = left; i < right; i++) {
//                int temp = sort[i+1];
//                for (int j = i; j >= left; j--) {
//                    if(sort[j] > temp) {
//                        sort[j+1] = sort[j];
//                    } else {
//                        sort[j+1] = temp;
//                        break;
//                    }
//                }
//            }
//            return -1;
//        }
        int key = sort[left];
        int l = left, r = right;
        while(l < r) {
            while(r > l && sort[r] > key ) -- r;
            while(l < r && sort[l] <= key) ++ l;
            int temp = sort[l];
            sort[l] = sort[r];
            sort[r] = temp;
        }
        sort[left] = sort[r];
        sort[r] = key;
        return r;
    }

    public static void qSort(int sort[], int left, int right) {
        if(left >= right || sort == null) return;
        int pos = partition(sort, left, right);
        if(pos >= 0) {
            qSort(sort, left, pos - 1);
            qSort(sort, pos + 1, right);
        }
    }

	/*
	 * 查找函数第K大的数
	 */
	public static int count = 0;
	public static int find_k_th_max_num(int sort[], int left, int right, int k){
		if(left >= right || sort == null) return -1;
		int l = left, r = right;
        int targetIdx = right - k + 1;
		while(true) {
			int pos = partition(sort, l, r);
			if(pos == targetIdx) return sort[pos];
			else if(pos > targetIdx){
				r = pos - 1;
			} else {
				l = pos + 1;
			}
		}
	}

	public static void main(String [] args) {
		Random ran = new Random();
		int [] sort = new int [20];
		for(int i = 0; i < sort.length; ++ i){
			sort[i] = ran.nextInt(20);
		}
		System.out.print("beforeSorting:");
		for(int i = 0; i < sort.length; ++ i){
			System.out.print(sort[i] + " ");
		}
		System.out.println("\n "+count + ";kth: " + find_k_th_max_num(sort, 0, sort.length - 1, 7));

		qSort(sort, 0, sort.length - 1);
		System.out.print("\nafterSorting:");
		for(int i = 0; i < sort.length; ++ i){
			System.out.print(sort[i] + " ");
		}
	}
}
