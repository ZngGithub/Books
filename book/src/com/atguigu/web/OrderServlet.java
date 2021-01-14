package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User login = (User) req.getSession().getAttribute("user");
        if (login == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        //获取用户的id
        Integer id = login.getId();
        //调用service层保存数据
        String orderId = orderService.createOrder(cart, id);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 查询全部订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部信息
        List<Order> orders = orderService.orderAll();
        //保存到域中
        req.setAttribute("orders", orders);
        req.getSession().setAttribute("orders", orders);
        //请求重定向回去
       /* resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");*/
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);

    }

    protected void confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户订单编号
        String orderId = req.getParameter("orderId");
        req.getSession().getAttribute("alterOrder");
        //获取状态
        int status = WebUtils.parseInt(req.getParameter("status"), 0);
        Integer alterOrder = orderService.alterOrder(orderId, status);
        req.getSession().setAttribute("alterOrder", alterOrder);
        //请求重定向回去
        /*resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");*/
        /*req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);*/
        resp.sendRedirect(req.getHeader("Referer"));
        System.out.println(req.getHeader("Referer"));
    }
    protected void orderItemOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取身份证
        String orId = req.getParameter("orId");
        req.getSession().getAttribute("orderItem");
        //通过身份证获取我需要的信息
        List<OrderItem> orderItem = orderService.queryOrderItemsByOrderId(orId);
        //信息我保存在我的口袋好拿出来给别人看
        req.getSession().setAttribute("orderItem", orderItem);
       /* resp.sendRedirect(req.getContextPath() + "/pages/manager/OrderItem.jsp");*/
        req.getRequestDispatcher("/pages/manager/OrderItem.jsp").forward(req, resp);
    }

    protected void OrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户类将user类保存到session
        User loginId = (User) req.getSession().getAttribute("user");
        //如果用户没有登入就跳转登入页面
        if (loginId == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        //有了身份证后前端就知道是哪个用户访问了
        Integer id = loginId.getId();
        List<Order> ordersByUserId = orderService.OrdersByUserId(id);
        //将数据库查询的返回信息保存到session。
        req.getSession().setAttribute("ordersByUserId", ordersByUserId);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

}
