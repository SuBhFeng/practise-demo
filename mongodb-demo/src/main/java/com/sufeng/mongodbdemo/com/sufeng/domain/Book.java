package com.sufeng.mongodbdemo.com.sufeng.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "book")
public class Book {
    @Id
    int id;
    String name;
    int type;
}
