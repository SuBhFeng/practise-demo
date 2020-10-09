package com.sufeng.mongodbdemo.com.sufeng.repository;

import com.sufeng.mongodbdemo.com.sufeng.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,Integer> {
}
