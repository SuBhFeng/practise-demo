package com.sufeng.mongodbdemo.com.sufeng.controller;

import com.sufeng.mongodbdemo.com.sufeng.domain.Book;
import com.sufeng.mongodbdemo.com.sufeng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@RestController
@RequestMapping("/mongo")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }
}
