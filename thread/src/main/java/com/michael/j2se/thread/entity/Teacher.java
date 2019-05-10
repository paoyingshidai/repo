package com.michael.j2se.thread.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {

    private String name;

    private List<Student> studentList = new ArrayList<>(10);

}
