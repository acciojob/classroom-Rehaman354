package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentData=new HashMap<>();//student name,student object
    HashMap<String,Teacher> teacherData=new HashMap<>();//teacher name,teacher object
    HashMap<String, List<String>> pairs=new HashMap<>();//teacher is key->list of students is value


}
