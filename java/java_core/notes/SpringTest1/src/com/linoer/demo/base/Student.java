package com.linoer.demo.base;

public class Student extends People{
    public String name;
    public int age;

    public Student(String sex, String name, int age, int height){
        super(sex, height);
        this.name = name;
        this.age = age;
    }
    public Student(){
        super();
        this.name = "";
        this.age = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

}
