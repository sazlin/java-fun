package com.seanazlin.gs1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Student {
    private int studentId;
    private String name;
    private int creditsCompleted;
    private int year;
    private static ObjectMapper mapper = new ObjectMapper();

    public static Student createFromMap(Map<String,String> studentProperties){
        Student student = mapper.convertValue(studentProperties, Student.class);
        return student;
    }

    // TODO: return map<string,string> that represents a student
    public Map<String, String> toMap(){
        // source: https://cassiomolin.com/2016/09/17/converting-pojo-map-vice-versa-with-jackson/
        return mapper.convertValue(this, new TypeReference<Map<String, String>>() {});
    }

    public static void main(String[] args){
        Map<String, String> studentMap = new HashMap<String, String>();
        studentMap.put("studentId", "1");
        studentMap.put("name", "Joe");
        studentMap.put("creditsCompleted", "21");
        studentMap.put("year", "3");
        Student student = Student.createFromMap(studentMap);
        Map<String,String> studentMap2 = student.toMap();
        System.out.println(student);
        System.out.println(studentMap);
        System.out.println(studentMap2);
    }
}


/* Student can give exams
exam has three steps. sequence of steps is fixed
1. take question paper : can't change this step
2. give answers : student can give any answers / own implementation
3. sign attendance : can't change this step
 */