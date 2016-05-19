package kaze.algorithm;
import java.util.Random;

public class QuickSortKthMaxNumber {
	
	//快速排序实现部分排序，找出前K个大的数
	
	public static int partition(int [] sort, int left, int right) {
		int l = left, r = right;
		int key = sort[l];
		while(l < r) {
			while(sort[l] <= key && l < right) ++ l;
			while(sort[r] >= key && r > left) -- r;
			if(l < r) {
				int temp = sort[l];
				sort[l] = sort[r];
				sort[r] = temp;
			}
		}
		if(l == r || l-1 == r) {
			sort[left] = sort[r];
			sort[r] = key;
		}
		return r;
	}
	
	public static void qSort(int sort[], int left, int right) {
		if(left >= right || sort == null) return;
		int pos = partition(sort, left, right);
		qSort(sort, left, pos - 1);
		qSort(sort, pos + 1, right);
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
		int [] sort = new int [50];
		for(int i = 0; i < sort.length; ++ i){
			sort[i] = ran.nextInt(50);
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
