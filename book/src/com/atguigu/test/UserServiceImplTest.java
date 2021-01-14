package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"xiaozeng","123456","xiaozeng@qq.com"));
    }

    @Test
    public void login() {
        System.out.println( userService.login(new User(null,"xiaozeng","123456",null)) == null ? "登入失败":"登入成功");
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("xiaozeng") == true ? "用户名已存在":"用户名可用");
    }
}