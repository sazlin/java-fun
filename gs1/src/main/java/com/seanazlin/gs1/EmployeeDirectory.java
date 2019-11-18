package com.seanazlin.gs1;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDirectory {
    public static void main(String[] args){
        Table<String, String, String> employeeSSSToIDtToName = HashBasedTable.create();
        employeeSSSToIDtToName.put("1234567890", "1", "John Doe");
        employeeSSSToIDtToName.put("2345678901", "2", "Jane Doe");

        System.out.println(employeeSSSToIDtToName.row("1234567890")); // John Doe
        System.out.println(employeeSSSToIDtToName.column("1")); // John Doe

        System.out.println(employeeSSSToIDtToName.row("2345678901")); // Jane Doe
        System.out.println(employeeSSSToIDtToName.column("2")); // Jane Doe
    }
}