package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {

        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(2, "诡秘之主", 1, new BigDecimal(600), new BigDecimal(600)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {

        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(2, "诡秘之主", 1, new BigDecimal(600), new BigDecimal(600)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(2, "诡秘之主", 1, new BigDecimal(600), new BigDecimal(600)));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(1, "凡人修仙传", 1, new BigDecimal(500), new BigDecimal(500)));
        cart.addItem(new CartItem(2, "诡秘之主", 1, new BigDecimal(600), new BigDecimal(600)));
        System.out.println(cart.getTotalPrice());
    }
}