package com.sufeng.algorithm;

/**
 * @author xiekangkang
 * @date 2020/9/15 17:22
 */
public class Solution12 {

    public static void main(String[] args) {
        System.out.println(StrToInt("-114411"));
    }

    /**
     * 符合条件的字符串转为对应的整数
     * @param str
     * @return
     */
    public static int StrToInt(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length;
        int index = 0;
        int result = 0;
        boolean sign = true;  // true表示正数
        if(str.startsWith("+") || str.startsWith("-")){
            index = 1;
            sign = false;
        }
        do{
            if(48 < chars[index] && chars[index] < 57){
                int currNum = (int) (chars[index] - 48);
                result += currNum * Math.pow(10, length - index - 1);
            }else{
                return 0;
            }
            index++;
        }while(index < chars.length);
        return sign ? result : -result;

    }
}
