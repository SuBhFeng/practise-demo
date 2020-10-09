package com.sufeng.algorithm;

public class Solution8 {
    public static void main(String[] args) {
        /**
         *  [0,1,2,3,4,5,6,7]
         *  [3,4,5,6,7,0,1,2]  数组旋转后找指定的值
         */
        int[] nums = new int[]{3,4,5,6,7,0,1,2};
        System.out.println(search(nums, 2));


    }

    static int search(int[] nums,int target){
        int start = 0;
        int end = nums.length;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] >= nums[start]){
                if(nums[start] <= target && nums[mid] > target){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }else{
                if(nums[mid] < target && nums[end] >= target){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}

