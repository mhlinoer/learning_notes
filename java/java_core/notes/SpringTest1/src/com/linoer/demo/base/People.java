package com.linoer.demo.base;

public class People {
    public String sex;
    public int height;
    public People(String sex, int height){
        this.height = height;
        this.sex = sex;
    }
    public People(){
        this.height = 0;
        this.sex = "null";
    }
}
