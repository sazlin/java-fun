package com.seanazlin.gs1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class EmployeeListBetter {
    public static void main(String[] args) throws IOException {
        File f = new File("1.csv");
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter writer = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("employeeId,employeeName,address,email,numDependents");
        bufferedWriter.newLine();
        Collection<EmployeeThread> employees = new ArrayList<>();
        for(int i = 0; i < 500000; ++i){
            Employee.EmployeeBuilder builder = new Employee.EmployeeBuilder(i);
            employees.add(new EmployeeThread(bufferedWriter, builder.employeeName("John").build()));
        }
        ExecutorService executor = Executors.newFixedThreadPool(100);
        long startTime = System.currentTimeMillis();
        try {
            executor.invokeAll(employees);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        for(Employee employee : employees){
////            EmployeeThread empThread = new EmployeeThread(bufferedWriter, employee);
//            // Thread e = new Thread(empThread);
//            executor.execute(empThread);
////            e.start();
//        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.flush();
            bufferedWriter.close();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }


        // System.out.println(employees);
    }
}

// TODO: Run ThreadExperiment1 and measure the time
// TODO: Run ThreadExperiment2 and measure the time