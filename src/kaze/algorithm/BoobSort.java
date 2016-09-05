package kaze.algorithm;

import java.util.Random;

/**
 * Created by kaze on 16-3-6.
 */
public class BoobSort {

    static void sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if(!flag) {
                System.out.println("\nalready sorted!!");
                break;
            }
        }
    }

    public static void main(String [] args) {
        Random rand = new Random();
        int len = 10000;
        int [] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = rand.nextInt(len*10);
        }
        System.out.println("before:");
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        sort(arr);
        System.out.println("\nafter:");
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
