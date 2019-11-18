package com.seanazlin.gs1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

public class EmployeeThread implements Callable<Object> {

    private BufferedWriter bufferedWriter;
    private Employee employee;

    public EmployeeThread(BufferedWriter bufferedWriter, Employee employee){
        this.bufferedWriter = bufferedWriter;
        this.employee = employee;
    }

    @Override
    public Object call() {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(employee.getEmployeeId()).append("+");
        stringBuffer.append(employee.getAddress()).append("+");
        stringBuffer.append(employee.getEmail()).append("+");
        stringBuffer.append(employee.getNumDependents());
        return null;
//        try {
//            bufferedWriter.write(stringBuffer.toString());
//            bufferedWriter.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
