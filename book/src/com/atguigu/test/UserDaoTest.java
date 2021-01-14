package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("abcd") == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }

    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("abcd","123456") == null){
            System.out.println("用户名或密码错误，请重新输入");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"xiaoge","123456","abcd.@qq.com"))== -1 ? "操作成功":"操作失败");
    }
}