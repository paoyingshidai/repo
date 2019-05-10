package com.michael.j2se.thread.service;

import com.michael.j2se.thread.entity.Student;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StudentService {

    public Student createStudent() {
        Student s = new Student();
        s.setName("Michael");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return s;
    }
}
