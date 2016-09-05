package kaze.algorithm;

import java.util.Random;
import java.util.Scanner;

public class HeapSortTopK {
	/*
	 * 大顶堆排序，用于升序
	 */
	public static void adjustMaxHeap(int sort[], int index, int n) {
		int i = index;
		int lc, maxc;
		while(true) {
			lc = i*2 + 1;										//数组下标从0开始，所以+1
			if(lc < n) maxc = lc; else break;
			if(lc + 1 < n && sort[maxc] < sort[lc + 1]) 
				maxc ++;										//取得左右孩子中大的那个的下标
			if(sort[maxc] > sort[i]) {							//建立大顶堆
				int temp = sort[i];
				sort[i] = sort[maxc];
				sort[maxc] = temp;
				i = maxc;										//以该孩子节点作为下一轮调整的父节点
				continue;
			} else break;										//父节点比孩子节点都大，所以不用调整
		}
		
	}
	
	/*
	 * 小顶堆排序, 用于降序
	 */
	public static void adjustMinHeap(int sort[], int index, int n) {
		int i = index;
		int lc, minc;
		while(true) {
			lc = i*2 + 1;										//数组下标从0开始，所以+1
			if(lc < n) minc = lc; else break;
			if(lc + 1 < n && sort[minc] > sort[lc + 1]) 
				minc ++;										//取得左右孩子中大的那个的下标
			if(sort[minc] < sort[i]) {							//建立大顶堆
				int temp = sort[i];
				sort[i] = sort[minc];
				sort[minc] = temp;
				i = minc;										//以该孩子节点作为下一轮调整的父节点
				continue;
			} else break;										//父节点比孩子节点都大，所以不用调整
		}
		
	}
	
	
	public static void buildHeap(int sort[], int n) {
		int node = n / 2 - 1;									//最后一个非叶子节点
		for(int i = node; i >= 0; i --) {
			adjustMinHeap(sort, i, n);
		}
	}
	
	public static void hSort(int [] sort, int n) {
		int len = n;
		while(len > 0) {
			int temp = sort[len-1];
			sort[len-1] = sort[0];
			sort[0] = temp;
			adjustMinHeap(sort, 0, --len);
		}
	}
	
	private static void findTopK(int[] sort, int k, int length) {
		for(int i = k+1; i < length; ++ i) {
			if(sort[0] < sort[i]) {
				sort[0] = sort[i];
				adjustMinHeap(sort, 0, k);
			}
		}
	}
	
	public static void main(String [] args) {
		int sort[] = new int[10];
		Scanner input = new Scanner(System.in);
		int k;
		while(true) {
			k = input.nextInt();				//K值
			if(k > sort.length) {
				System.out.println("K should be smaller than sort's length.");
			} else break;
		}
		
		Random rand = new Random();
		System.out.print("before sorting: ");
		for(int i = 0; i < sort.length; ++ i) {
			sort[i] = rand.nextInt(100);
			System.out.print(sort[i] + " ");
		}
		buildHeap(sort, k);						//先建立一个K大小的小顶堆
		findTopK(sort, k, sort.length);
		System.out.print("\nThe top k: ");
		for(int i = 0; i < k; ++ i) {
			System.out.print(sort[i] + " ");
		}
		
	}
}
