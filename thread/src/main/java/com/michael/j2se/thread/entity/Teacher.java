package com.michael.j2se.thread.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Teacher implements Serializable {

    private String name;

    private List<Student> studentList = new ArrayList<>(10);

}
