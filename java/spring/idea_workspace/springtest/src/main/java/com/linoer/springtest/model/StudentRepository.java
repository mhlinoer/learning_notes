package com.linoer.springtest.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {
    private final Map<String, Student> studentMap = new ConcurrentHashMap<>();

    public Student save(String className, Student student){
        student.setClassName(className);
        return studentMap.put(className, student);
    }

    public Student save(Student student){
        return save(student.getClassName(), student);
    }

    public Student findOne(String className){
        return studentMap.get(className);
    }

    public List<Student> findAll(){
        return new ArrayList<>(studentMap.values());
    }

    public void delete(String className){
        studentMap.remove(className);
    }

    public boolean exists(String className){
        return studentMap.containsKey(className);
    }
}
