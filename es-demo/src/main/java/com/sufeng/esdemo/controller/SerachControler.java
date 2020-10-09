package com.sufeng.esdemo.controller;

import com.sufeng.esdemo.service.SearchSearvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es")
public class SerachControler {

    @Autowired
    SearchSearvice searchSearvice;

    @GetMapping("/bulkadd/{keyword}")
    public boolean BulkAddToEs(@PathVariable("keyword") String keyword) throws IOException {
        return searchSearvice.BulkGoods(keyword);
    }


    @GetMapping("/search/{keyword}/{pageNum}/{pageSize}")
    public List<Map<String,Object>> searchGoods(@PathVariable String keyword,@PathVariable int pageNum,@PathVariable int pageSize) throws IOException {
        return searchSearvice.searchGoods(keyword,pageNum,pageSize);
    }

    @GetMapping("/index")
    public String test(){
        return "index";
    }






}
