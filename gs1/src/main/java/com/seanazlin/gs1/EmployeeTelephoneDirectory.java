package com.seanazlin.gs1;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.Set;

// TODO: maintain a telephone directory
// Employee can have 1 or more telephone number
// telephone can be shared among employees
// lookup phone # by emp id
// lookup emp id by phone #
// be efficient - avoid duplication
// be as clean as possible
@SuppressWarnings("ALL")
public class EmployeeTelephoneDirectory {
    private MutableGraph<String> directory = GraphBuilder.undirected().build();

    public void addEmployeePhone(String employee, String phone){
        directory.putEdge(employee, phone);
    }

    public void removeEmployeePhone(String employee, String phone){
        directory.removeEdge(employee, phone);
    }

    public Set<String> lookupByEmployeeId(String employeeId){
        return directory.adjacentNodes(employeeId);
    }

    public Set<String> lookupByPhone(String phone){
        return directory.adjacentNodes(phone);
    }

    @Override
    public String toString() {
        return directory.toString();
    }

    public static void main(String[] args){
        EmployeeTelephoneDirectory dir = new EmployeeTelephoneDirectory();
        dir.addEmployeePhone("John", "123");
        dir.addEmployeePhone("Sally", "123");
        dir.addEmployeePhone("Sally", "456");
        dir.addEmployeePhone("Bill", "789");
        dir.addEmployeePhone("Bill", "789"); // overwrites

        System.out.println(dir);
        System.out.println(dir.lookupByEmployeeId("John"));
        System.out.println(dir.lookupByEmployeeId("Sally"));
        System.out.println(dir.lookupByEmployeeId("Bill"));
        System.out.println(dir.lookupByPhone("123"));
        System.out.println(dir.lookupByPhone("456"));
        System.out.println(dir.lookupByPhone("789"));
    }
}
