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

public class ClientBookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);//默认从第一页
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);//默认显示4条记录
        //调用BookService.page(pageNo, pageSize);
        Page<Book> page = bookService.page(pageNo, pageSize);
        //地址
        page.setUrl("client/bookServlet?action=page");
        //保存到域中
        req.setAttribute("page", page);
        //转发到页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);//默认从第一页
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);//默认显示4条记录
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //调用BookService.page(pageNo, pageSize);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //地址
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格的参数，追加分页的地址参数
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //保存到域中
        req.setAttribute("page", page);
        //转发到页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
