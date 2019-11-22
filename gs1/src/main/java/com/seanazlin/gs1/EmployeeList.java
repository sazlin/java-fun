package com.seanazlin.gs1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    public static void main(String[] args) throws IOException {
        File f = new File("1.csv");
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter writer = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("employeeId,employeeName,address,email,numDependents");
        bufferedWriter.newLine();
        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < 500000; ++i){
            Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder(i);
            employees.add(builder.employeeName("John").build());
        }
        long startTime = System.currentTimeMillis();
        for(Employee employee : employees){
            StringBuffer stringBuffer = new StringBuffer("");
            stringBuffer.append(employee.getEmployeeId()).append("+");
            stringBuffer.append(employee.getAddress()).append("+");
            stringBuffer.append(employee.getEmail()).append("+");
            stringBuffer.append(employee.getNumDependents());
//            bufferedWriter.write(stringBuffer.toString());
//            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        //System.out.println(employees);
    }
}
