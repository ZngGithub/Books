package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    //购物车对象
    //购物车商品
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        //通过商品编号获取购物车商品信息
        //当前商品
        CartItem item = items.get(cartItem.getId());
        //判断商品编号是否存在
        if (item == null){
            //不存在添加商品
            items.put(cartItem.getId(), cartItem);
        } else {
            //如果存在的话
            //当前商品加1
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        //删除方法
        items.remove(id);
    }

    public void clear(){
        //清空
        items.clear();
    }

    public void updateCount(Integer id, Integer count){
        CartItem item = items.get(id);
        //判断有无商品
        if (item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        //默认赋值
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
