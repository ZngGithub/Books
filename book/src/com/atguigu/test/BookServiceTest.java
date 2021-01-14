package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "我的传奇人生", "Zng", new BigDecimal(500), 10000, 2000, null));

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(19);
    }

    @Test
    public void queryBookById() {
        bookService.queryBookById(23);
    }

    @Test
    public void queryBooks() {
        for (Object object : bookService.queryBooks()){
            System.out.println(object);
        }
    }
}