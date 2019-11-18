package com.seanazlin.gs1;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.seanazlin.gs1.Employee.EmployeeBuilder;

public class EmployeeTest {
    @Test
    public void testEmployeeConstructor(){
        EmployeeBuilder builder = new EmployeeBuilder(1);
        Employee employee = builder.address("123 Awesome street, Beverly, MA, USA").email("hi@hi.com").employeeName("Sean").build();
        assertEquals(employee.getAddress(), builder.getAddress());
    }
}