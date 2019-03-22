package com.linoer.demo.jihe;

import com.linoer.demo.base.Student;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class MyJiheTest {
    public static void main(String[] args){
        MyQueue<Student> studentMyQueue = new LoopArrayQueue<>(10);
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1916, 1, 1));
        pq.add(LocalDate.of(1926, 2, 3));
        pq.add(LocalDate.of(1806, 7, 6));
        for(LocalDate localDate: pq){
            System.out.println(localDate);
        }
    }
}
