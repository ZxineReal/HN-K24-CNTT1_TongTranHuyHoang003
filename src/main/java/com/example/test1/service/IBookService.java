package com.example.test1.service;

import com.example.test1.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    void save(Book book);
    void deleteById(Long id);
    Optional<Book> findById(Long id);
}
