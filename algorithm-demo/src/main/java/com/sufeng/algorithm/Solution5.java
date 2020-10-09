package com.sufeng.algorithm;

import java.util.ArrayList;

public class Solution5 {
    public static void main(String[] args) {
        System.out.println(maxInWindows(new int[]{10,14,12,11},5));
    }

    public static ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        if(size == 0 || size > num.length){
            return integers;
        }
        for (int i = 0; i <= num.length-size; i++) {
            int[] arr = new int[size];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = num[i+j];
            }
            integers.add(maxInArr(arr));
        }
        return integers;
    }
    public static int maxInArr(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
}
