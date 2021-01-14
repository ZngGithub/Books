package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao book = new BookDaoImpl();
    @Test
    public void addBookDao() {
        book.addBookDao(new Book(null,"诡秘之主", "爱潜水的乌贼", new BigDecimal(500), 10000, 2000, null));
    }

    @Test
    public void deleteBookDao() {
        book.deleteBookDao(20);
    }

    @Test
    public void updateBookDao() {
        book.updateBookDao(new Book(1,"凡人修仙传", "忘语", new BigDecimal(600), 20000, 5000, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(book.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        book.queryBooks();
        for (Object que : book.queryBooks()){
            System.out.println(que);
        }
    }
}