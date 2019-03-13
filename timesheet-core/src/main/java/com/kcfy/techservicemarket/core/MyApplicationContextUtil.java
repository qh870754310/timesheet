package com.kcfy.techservicemarket.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public MyApplicationContextUtil() {
    }

    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        context = contex;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
