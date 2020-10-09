package com.sufeng.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MyStream {
    public static void main(String[] args) {
        System.out.println("Java8 Stream Demo");

        System.out.println("=====================fitler start=======================");
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " +strings);
        long count = strings.stream().filter(t -> t.isEmpty()).count();
        System.out.println("空字符串个数为：" + count);

        count = strings.stream().filter(t -> t.length()==3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = strings.stream().filter(t -> !t.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(t -> !t.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);
        System.out.println("=====================fitler end========================");

        System.out.println("=======================map start=======================");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(t -> t * t).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);

        // 数字统计相关信息
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        System.out.println("列表: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt(t -> t).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        System.out.println("=======================map end=========================");

        System.out.println("=====================parallel start====================");

        // 并行处理
        count = strings.parallelStream().filter(t -> t.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);

        System.out.println("=====================parallel end======================");



    }
}
