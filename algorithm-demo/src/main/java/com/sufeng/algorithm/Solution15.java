package com.sufeng.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiekangkang
 * @date 2020/9/18 13:35
 */
public class Solution15 {
    /**
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
     * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的
     * 那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....
     * 这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,
     * 哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * <p>
     * 如果没有小朋友，请返回-1
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution(4, 3));
    }

    public static int LastRemaining_Solution(int n, int m) {
        int[] ids = new int[n];
        int count = 0;
        int num = -1;
        int index = 0;
        while (count < n - 1) {
            // 未出列 报数+1
            if (ids[index] != -1) {
                num++;
                // 报数为m-1的同学 标记为-1表示出列 从0开始报数 并记录出列同学的个数
                if (num == m - 1) {
                    ids[index] = -1;
                    count++;
                    num = -1;
                }
            }
            // 学生移动
            if (index == n - 1) {
                index = 0;
            } else {
                index++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (ids[i] != -1) return i;
        }
        return -1;
    }
}
