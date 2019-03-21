package com.linoer.demo.imp;

import com.linoer.demo.interfaces.CompactDisc;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component("newSgtPeppers")
public class SgtPeppers implements CompactDisc, BeanNameAware, BeanFactoryAware {

    private String title = "SgtPeppers";
    private String artist = "The Beatles";
    private BeanFactory myBeanFactory;

    @Override
    public void play() {
        System.out.println("playing:" + title + "  by:" + artist);
        System.out.println(myBeanFactory.toString());
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("SgtPeppers name:" + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        myBeanFactory = beanFactory;
    }
}
