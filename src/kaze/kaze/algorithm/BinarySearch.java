package kaze.kaze.algorithm;

import java.util.Scanner;
import java.util.Random;

public class BinarySearch {
	private static void qSort(int [] sort, int start, int end){
		if(start >= end) return;
		int keyIndex = start;
		int key = sort[keyIndex];
		int left = start;
		int right = end;
		while(left < right){
			while(sort[left]<=key && left<end) left ++;
			while(sort[right]>=key && right>start) right --;
			if(left < right){
				int temp = sort[left];
				sort[left] = sort[right];
				sort[right] = temp;
			}
		}
		if(right <= left) {
			int temp = sort[keyIndex];
			sort[keyIndex] = sort[right];
			sort[right] = temp;
		}
		qSort(sort, right+1, end);
		qSort(sort, start, right-1);
	}
	
	private static int binarySearch(int [] sort, int num) {
		int mid, low, high;
		low = 0;
		high = sort.length;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (num > sort[mid]) {
				low = mid + 1;
			} else if (num < sort[mid]) {
				high = mid - 1;
			} else if(num == sort[mid])return mid + 1;
		}
		return -1;
	}
	public static void main(String [] args) {
		Random ran = new Random();
		int [] sort = new int [10];
		for(int i = 0; i < sort.length; ++ i){
			sort[i] = ran.nextInt(1000);
		}
		System.out.print("beforeSorting:");
		for(int i = 0; i < sort.length; ++ i){
			System.out.print(sort[i] + " ");
		}
		qSort(sort, 0, sort.length - 1);
		System.out.print("\nafterSorting:");
		for(int i = 0; i < sort.length; ++ i){
			System.out.print(sort[i] + " ");
		}
		System.out.print("\n");
		Scanner input = new Scanner(System.in);
		int res = input.nextInt();
		int index = binarySearch(sort, res);
		System.out.println(index);
	}
}