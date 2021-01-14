package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    //调用Dao层
    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //获取图书的参数信息
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用数据添加方法
        bookService.addBook(book);
        //调用重定向转向查询页面,需要身份为list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //通过id删除数据
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书的参数信息
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用update修改方法
        bookService.updateBook(book);
        //重定向到查询list页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取数据库中的所有数据
        List<Book> books = bookService.queryBooks();
        //2.添加到域中让jsp页面访问
        req.setAttribute("books", books);
        //3.请求转发到jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //通过id获取数据
        Book book = bookService.queryBookById(id);
        //保存到域中
        req.setAttribute("book", book);
        //请求转发到edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);//默认从第一页
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);//默认显示4条记录
        //调用BookService.page(pageNo, pageSize);
        Page<Book> page = bookService.page(pageNo, pageSize);
        //地址
        page.setUrl("manager/bookServlet?action=page");
        //保存到域中
        req.setAttribute("page", page);
        //转发到页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
