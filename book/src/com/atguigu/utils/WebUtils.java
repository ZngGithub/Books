package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    /**
     * 把所有请求参数信息都注入到bean对象中
     * @param map
     * @param bean
     * @param <T>自定义泛型,占个坑位，要用的时候在告诉编译器我要什么类型
     * @return
     */
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            System.out.println("注入之前"+bean);
            BeanUtils.populate(bean, map);
            System.out.println("注入之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 类型转换
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        }catch (Exception e){
           /* e.printStackTrace();*/
        }
        return defaultValue;
    }
}
