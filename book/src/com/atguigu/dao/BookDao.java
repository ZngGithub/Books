package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {
    /**
     * 添加的方法
     * @return
     */
    int addBookDao(Book book);
    /**
     * 删除的方法
     * @return
     */
    int deleteBookDao(Integer id);
    /**
     * 替换的方法
     * @return
     */
    int updateBookDao(Book book);
    /**
     * 查询单个的方法
     * @return
     */
    Book queryBookById(Integer id);
    /**
     * 查询多个的方法
     * @return
     */
    List<Book> queryBooks();

    Integer queryForSingleValue();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
