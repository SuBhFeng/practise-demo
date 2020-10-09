package com.sufeng.esdemo.util;

import com.sufeng.esdemo.domain.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtils {

    public static List<Goods> getTargetGoods(String keywords) throws IOException {
        List<Goods> goodsList = new ArrayList<>();
        String Url = "https://search.jd.com/Search?keyword="+keywords;
        Document document = Jsoup.parse(new URL(Url),3000);
        Element goods = document.getElementById("J_goodsList");
        Elements lis = goods.getElementsByTag("li");
        for (Element li : lis) {
            String img = li.getElementsByTag("img").eq(0).attr("src");
            String price = li.getElementsByClass("p-price").eq(0).text();
            String name = li.getElementsByClass("p-name").eq(0).text();
            Goods goods1 = new Goods(img, name, price);
            goodsList.add(goods1);
        }
        return goodsList;
    }

    public static List<Goods> getTargetGoods1(String keywords) throws IOException {
        List<Goods> goodsList = new ArrayList<>();
        String Url = "https://www.ctrip.com/"+keywords;
        Document document = Jsoup.parse(new URL(Url),30000);
        Elements goods = document.getElementsByClass("product-item");
        for (Element li : goods) {
            String img = li.getElementsByTag("img").eq(0).attr("src");
            String price = li.getElementsByClass("item-info").eq(0).text();
            String name = li.getElementsByClass("item-name").eq(0).text();
            Goods goods1 = new Goods(img, name, price);
            goodsList.add(goods1);
        }
        return goodsList;
    }

    public static List<Goods> getTargetGoods2(String keywords) throws IOException {
        List<Goods> goodsList = new ArrayList<>();
        String Url = "https://www.ctrip.com"+keywords;
        Document document = Jsoup.parse(new URL(Url),30000);
//        System.out.println(document);
        Elements goods = document.getElementsByClass("product-item");
        System.out.println("===================================");
        System.out.println(goods);
        return goodsList;
    }



}
