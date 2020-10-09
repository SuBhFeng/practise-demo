package com.sufeng.esdemo;

import com.sufeng.esdemo.domain.Goods;
import com.sufeng.esdemo.util.JsoupUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupUtilsTest {

    @Test
    public void test() throws IOException {
        for (Goods java : JsoupUtils.getTargetGoods("java")) {
            System.out.println(java);
            System.out.println("===================================");
        }
    }

    @Test
    public void test1() throws IOException {
        Queue<Integer> q = new ArrayDeque<>();

//        for (Goods java : JsoupUtils.getTargetGoods1("")) {
//            System.out.println(java);
//            System.out.println("===================================");
//        }
        JsoupUtils.getTargetGoods2("");
    }
}
