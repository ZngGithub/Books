package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    public void addBook(Book book) {
        bookDao.addBookDao(book);
    }


    public void deleteBookById(Integer id) {
        bookDao.deleteBookDao(id);
    }


    public void updateBook(Book book) {
        bookDao.updateBookDao(book);
    }


    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }


    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page();
        //设置每页的数量
        page.setPageSize(pageSize);
        //求总记录条数
        Integer pageTotalCount = bookDao.queryForSingleValue();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页数
        page.setPageNo(pageNo);
        //求当前页数据的索引
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page();
        //设置每页的数量
        page.setPageSize(pageSize);
        //求总记录条数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页数
        page.setPageNo(pageNo);
        //求当前页数据的索引
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
