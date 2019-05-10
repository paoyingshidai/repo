package com.michael.j2se.thread.service;

import com.michael.j2se.thread.entity.Student;
import com.michael.j2se.thread.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TeacherService {

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private StudentService studentService;


    /**
     * 20 个老师，每个老师添加 1 个学生, 利用多线程实现最快的执行速度，其中获取学生的方法会阻塞 1s
     * @return
     */
    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>(20);
        for (int i = 0; i < 10; i++) {
            teachers.add(Teacher.builder().name("name" + i).studentList(new ArrayList<>()).build());
        }

        List<Future<Void>> futures = new ArrayList<>(10);
        for (Teacher teacher : teachers) {
            System.out.println("getStudent");
            Future<Void> result = executorService.submit(() -> {
                teacher.getStudentList().add(studentService.createStudent());
               return null;
            });
            futures.add(result);
            System.out.println("doAfter");
        }
        // 需要等待所有线程执行完后才能往下执行
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return teachers;
    }

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
                System.out.println(" ----- doBefore -----");
                Student s = future.get(1, TimeUnit.SECONDS);
                System.out.println("------ doAfter ------");
                t.getStudentList().add(s);
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
