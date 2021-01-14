package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
