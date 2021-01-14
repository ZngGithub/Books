package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    //用户功能的数据库操作代码
    private UserService userService = new UserServiceImpl();

    protected void ajaxUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名
        String username = req.getParameter("username");
        //返回true说明用户名已经有了
        boolean exists = userService.existsUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("exists", exists);
        Gson gson = new Gson();
        String exJson = gson.toJson(map);
        resp.getWriter().write(exJson);
    }

    /**
     * 处理登入的代码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //所有请求参数的代码
        User user = userService.login(new User(null, username, password, null));
        //判断用户名和密码是否正确
        if ( user == null){
            //账号密码不正确的话返回登入页面
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            //正确的话跳转登入成功页面
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注册的代码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //销毁验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //所有请求参数的代码
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //2.检查验证码是否正确
        if(token != null && token.equals(code)){
            //正确的话检查用户名是否可用
            if (userService.existsUsername(username)){//不可用
                req.setAttribute("username","用户名["+username+"]已存在！");
                req.setAttribute("name", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);//跳回注册页面
            } else {//用户名可用
                userService.registUser(new User(null, username, password, email));
                System.out.println("注册成功");
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);//跳回注册成功页面
            }
        }else{
            //错误的话就返回注册页面
            req.setAttribute("code", "验证码{"+code+"}错误");
            req.setAttribute("name", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注销功能
        req.getSession().invalidate();
        //重定向返回
        resp.sendRedirect(req.getContextPath() + "/index.jsp");

    }
}
