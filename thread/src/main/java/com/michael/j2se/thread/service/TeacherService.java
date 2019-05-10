package com.michael.j2se.thread.service;

import com.michael.j2se.thread.entity.Student;
import com.michael.j2se.thread.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TeacherService {

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private StudentService studentService;

    public Teacher getTeacher() {
        Teacher t = new Teacher();
        t.setName("MM");

        List<Future<Student>> futures = new ArrayList();

        for (int i = 0; i < 10; i++) {
            Future<Student> result = executorService.submit(() -> {
                return studentService.createStudent();
            });
            futures.add(result);
        }

        for (Future<Student> future : futures) {
            try {
                t.getStudentList().add(future.get(1, TimeUnit.SECONDS));
            } catch (InterruptedException | TimeoutException e) {
                System.out.println("------------- 超时 -------------");
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return t;
    }


    public Teacher getTeacher2() {
        Teacher t = new Teacher();
        t.setName("MM");

        List<Future<Student>> futures = new ArrayList();

        for (int i = 0; i < 10; i++) {
            Future<Student> result = executorService.submit(() -> {
                t.getStudentList().add(studentService.createStudent());
                return null;
            });
            futures.add(result);
        }

        for (Future<Student> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 不使用多线程
     * @return
     */
    public Teacher getTeacherNoMultiThread() {

        Teacher t = new Teacher();
        t.setName("MM");

        for (int i = 0; i < 10; i++) {
            t.getStudentList().add(studentService.createStudent());
        }
        return t;
    }

}
