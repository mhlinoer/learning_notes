package com.linoer.springtest.service;

import com.linoer.springtest.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    public List search(String searchType){
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setClassId(1000 + i);
            student.setClassName("class " + i);
            student.setTeacherId(5900 + i);
            students.add(student);
        }
        return students;
    }
}
