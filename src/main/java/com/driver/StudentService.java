package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
            StudentRepository sr;

    public void addStudent(Student student) {
        String name= student.getName();
       sr.studentData.put(name,student);
    }

    public void addTeacher(Teacher teacher) {
        String name= teacher.getName();
        sr.teacherData.put(name,teacher);
    }

    public void addPair(String student, String teacher) {
        if(sr.pairs.containsKey(teacher)) {
           List<String> students= sr.pairs.get(teacher);
           students.add(student);
           sr.pairs.put(teacher,students);
        }
        else
        {
            List<String> students=new ArrayList<>();
            students.add(student);
            sr.pairs.put(teacher,students);
        }
    }

    public Student getStudent(String name) {
        return sr.studentData.get(name);
    }

    public Teacher getTeacher(String name) {
        return sr.teacherData.get(name);
    }

    public List<String> getStudentList(String teacher) {
        for(String t:sr.pairs.keySet())
        {
            if(teacher.equals(t))
                return sr.pairs.get(t);
        }
        return new ArrayList<String>();
    }

    public List<String> getAllStudents() {
        List<String> allStudents=new ArrayList<>();
        for(String student:sr.studentData.keySet())
        {
            allStudents.add(student);
        }
        return allStudents;
    }

    public void deleteTeacherAndStudents(String teacher) {
        //removing students of the teacher
        List<String> st_list=sr.pairs.get(teacher);
        for(String student:st_list){
                sr.studentData.remove(student);
        }
        //removing teacher
        sr.teacherData.remove(teacher);
        sr.pairs.remove(teacher);

    }

    public void deleteRecords() {
        for(List<String> st_list:sr.pairs.values())
        {
            for(int i=0;i<st_list.size();i++)
                sr.studentData.remove(st_list.get(i));
        }
        sr.teacherData.clear();
        sr.pairs.clear();
    }
}
