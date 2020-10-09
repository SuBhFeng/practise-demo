package com.sufeng.algorithm;

import java.util.concurrent.ForkJoinPool;

/**
 * @author xiekangkang
 * @date 2020/9/16 15:13
 */
public class Solution14 {
    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。
     * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    public static void main(String[] args) {
        System.out.println(match("a".toCharArray(), "a*".toCharArray()));
    }

    public static boolean match(char[] str, char[] pattern)
    {
        if(str.length == 0 && (pattern.length == 0 || pattern.length == 2 && pattern[1] == '*')){
            return true;
        }
        if(str.length == 1 && pattern.length == 1 && pattern[0] == '.'){
            return true;
        }

        for (int i = 0; i < str.length;) {
            for (int j = 0; j <= pattern.length;) {
                if(i < str.length && j < pattern.length){
                    // 当前pattern为.  且下一位为*
                    if(str[i] != pattern[j]  && pattern[j] == '.' && pattern[j+1] == '*'){
                        i++;
                        if(str.length == 1 ){
                            j += 2;
                            continue;
                        }
                        // 如果到重复的最后一位 i j 都应该变化
                        if(i == str.length-1 || str[i] != str[i+1]){
                            j += 2;
                            i++;
                            continue;
                        }
                    }

                    if(str[i] == pattern[j] || pattern[j] == '.'){
                        // 如果pattern为* 且 str的位当前为不等于下一
                        if(i < str.length-1 && j < pattern.length-1 && pattern[j+1] == '*' && str[i] != str[i+1]){
                            i++;
                            j += 2;
                            if(j == pattern.length - 1){
                                j = pattern.length;
                            }
                            continue;
                        }else{
                            i++;
                            j++;
                            continue;
                        }
                    }

                    if(str[i] == '-'){
                        // 如果pattern为* 且 str的位当前为不等于下一
                        if(i < str.length-1 && j < pattern.length-1 && pattern[j+1] == '*'){
                            j += 2;
                            i += 2;
                            continue;
                        }else{
                           return false;
                        }
                    }

                    // 当前位pattern为*  如果当前位不同 且 当前位与pattern的前一位相同 且 下一位与pattern中的下一位相同
                    if(str[i] != pattern[j] && str[i] == pattern[j-1] && pattern[j] == '*' && str[i+1] == pattern[j+1]){
                        i++;
                        j++;
                        continue;
                    }
                    // 当前pattern位为*  如果当前位不同 且 当前位与pattern的前一位相同 且 下一位与pattern中的前一位相同
                    if(str[i] != pattern[j] && str[i] == pattern[j-1] && pattern[j] == '*' && str[i+1] != pattern[j-1]){
                        i++;
                        continue;
                    }
                    if(str[i] != pattern[j] && pattern[j+1] == '*'){
                        j += 2;
                        continue;
                    }
                }
                if(i == str.length && j == pattern.length){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
}
