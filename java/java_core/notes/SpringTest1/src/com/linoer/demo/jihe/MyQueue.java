package com.linoer.demo.jihe;

public interface MyQueue<E> {
    void add(E element);
    E remove();
    int size();
}
