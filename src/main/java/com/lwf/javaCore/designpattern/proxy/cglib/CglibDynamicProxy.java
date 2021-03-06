/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.lwf.javaCore.designpattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liweifan
 * @version $Id: CglibDynamicProxy, v 0.1 2018/4/20 上午10:06 liweifan Exp $
 */
public class CglibDynamicProxy implements MethodInterceptor {

    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        // 声明增强类实例
        Enhancer enhancer = new Enhancer();
        // 设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
        enhancer.setSuperclass(this.target.getClass());
        // 设置要代理的拦截器，回调函数，即一个方法拦截， new MethodInterceptor()
        enhancer.setCallback(this);
        // 创建代理对象实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 在代理真实对象前 我们可以添加一些自己的操作
        System.out.println("前置代理,增强处理");

        proxy.invokeSuper(obj, args);

        // 在代理真实对象操作后 我们也可以添加一些自己的操作
        System.out.println("后置代理,增强处理");
        return null;
    }
}