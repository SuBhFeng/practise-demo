package com.sufeng.mongodbdemo.com.sufeng.service;

import com.sufeng.mongodbdemo.com.sufeng.domain.Book;
import com.sufeng.mongodbdemo.com.sufeng.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book){

        bookRepository.save(book);
    }
}
