package com.linoer.demo;

import com.linoer.demo.base.Human;
import com.linoer.demo.base.People;
import com.linoer.demo.base.Student;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public class JavaCoreTest {

    class TraceHandler implements InvocationHandler{

        private Object target;

        public TraceHandler(Object obj){
            this.target = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(target, args);
        }
    }

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

    public static Object myCopyOfArray(Object object, int length){
        Class cls = object.getClass();
        if(!cls.isArray()) return null;
        Class type = cls.getComponentType();
        int len = Array.getLength(object);
        Object newObj = Array.newInstance(type, length);
        System.arraycopy(object, 0, newObj,0, Math.min(len, length));
        return newObj;
    }

    public static void printBuddies(Human<? super People> s){
        Human<Student> studentHuman = new Human<>();
    }

    public static void main(String[] args){
        Student[] students = new Student[100];
        Student[] o = (Student[]) myCopyOfArray(students, 200);
        System.out.println(o.length);
        Logger.getGlobal().warning("hello man");
//        () -> {
//            for (int i=0; i>=0; i--) System.out.println(i);
//        }
//        printBuddies();
    }
}
