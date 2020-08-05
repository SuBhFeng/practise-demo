package com.patsnap.resttemplatedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Target;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Solution1 {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10],
     * target = 8
     * 输出: [3,4]
     */
    @Test
    public void test(){
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int start = searchFirst(nums,target);
        // 可能数组中元素全部小于target 导致start越界
        if(nums[start] == target && start < nums.length){
            int end = searchLast(nums,target);
            System.out.println(start+","+end);
//            System.out.println(new int[]{start,end});
        }else{
            System.out.println("-1,-1");
//            System.out.println(new int[]{-1,-1});
        }
    }
    /**
     * 查找target可能出现的最小位置，该位置可能不是target(target不存在)
     * 当数组元素全部小于target 返回start=nums.length()
     * 当数组元素全部大于target 返回start=0
     * 其他情况 返回0<start<nums.length()
     * @param nums
     * @param target
     * @return
     */
    int searchFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int mid = 0;
        while(start <= end){
            mid = start + (end - start) / 2;
            if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start;
    }

    /**
     * 查找target可能出现的最大位置，该位置可能不是target(target不存在)
     * 当数组元素全部小于target 返回end=nums.length-1
     * 当数组元素全部大于target 返回end=0
     * 其他情况 返回0<end<nums.length()-1
     * @param nums
     * @param target
     * @return
     */
    int searchLast(int[] nums,int target){
        int start = 0;
        int end = nums.length-1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return end;
    }

    @Test
    public void test2(){
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int start = 0;
        int end = 0;
        int index = searchOne(nums,target);
        if(nums[index] == target){
            start = index - 1;
            end = index + 1;
            while(start>=0 && nums[start] == target){
                start--;
            }
            while(end<nums.length && nums[end] == target){
                end++;
            }
            System.out.println((start+1)+","+(end-1));
        }else{
            System.out.println("-1,-1");
        }

    }

    int searchOne(int[] nums,int target){
        int start = 0;
        int end = nums.length-1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }


}
