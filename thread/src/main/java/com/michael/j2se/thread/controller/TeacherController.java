package com.michael.j2se.thread.controller;

import com.michael.j2se.thread.entity.Teacher;
import com.michael.j2se.thread.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @RequestMapping("getTeachers")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @RequestMapping("getTeacher")
    public Teacher getTeacher() {
        return teacherService.getTeacher();
    }

    @RequestMapping("getTeacher2")
    public Teacher getTeacher2() {
        return teacherService.getTeacher2();
    }

    @RequestMapping("getTeacherNoMulti")
    public Teacher getTeacher3() {
        return teacherService.getTeacherNoMultiThread();
    }

}
