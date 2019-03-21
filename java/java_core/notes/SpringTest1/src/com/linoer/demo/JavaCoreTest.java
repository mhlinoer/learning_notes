package com.linoer.demo;

import com.linoer.demo.base.Student;

import java.lang.reflect.Array;

public class JavaCoreTest {

    public static Object goodCopyOf(Object a, int newLength){
        Class c = a.getClass();
        if(!c.isArray()){
            return null;
        }
        Class componentType = c.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

    public static void main(String[] args){

        Student[] students = new Student[100];
        Student[] o = (Student[]) goodCopyOf(students, 200);
        System.out.println(o.length);
    }
}
