package com.sufeng.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiekangkang
 * @date 2020/9/11 13:25
 */
public class Solution10 {
    public static void main(String[] args) {
        System.out.println(movingCount(10,1,100 ));
    }

    public static int movingCount(int k, int rows , int cols){
        int count = 0;
        for(int i = 0; i < rows ; i++){
            int sum1 = nums(i).stream().reduce(Integer::sum).orElse(0);
            for(int j = 0; j < cols ; j++){
                int sum2 = nums(j).stream().reduce(Integer::sum).orElse(0);
                if(sum1 + sum2 <= k){
                    count++;
                }
            }
        }
        return count;
    }

    public static List<Integer> nums(int number){
        List<Integer> numList = new ArrayList<Integer>();
        while(number != 0){
            numList.add(number % 10);
            number /= 10;
        }
        return numList;
    }


}
