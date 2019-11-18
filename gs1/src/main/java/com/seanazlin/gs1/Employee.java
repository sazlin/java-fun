package com.seanazlin.gs1;

import lombok.Data;

@Data
public class Employee {
    private int employeeId;
    private String employeeName;
    private String address;
    private String email;
    private int numDependents;

    public Employee(EmployeeBuilder builder){
        this.employeeId = builder.employeeId;
        this.employeeName = builder.employeeName;
        this.address = builder.address;
        this.email = builder.email;
        this.numDependents = builder.numDependents;
    }

    @Data
    public static class EmployeeBuilder {
        private int employeeId;
        private String employeeName;
        private String address;
        private String email;
        private int numDependents;

        public EmployeeBuilder(int employeeId){
            this.employeeId = employeeId;
        }

        public EmployeeBuilder employeeName(String employeeName){
            this.employeeName = employeeName;
            return this;
        }

        public EmployeeBuilder address(String address){
            this.address = address;
            return this;
        }

        public EmployeeBuilder email(String email){
            this.email = email;
            return this;
        }

        public EmployeeBuilder numDependents(int numDependents){
            this.numDependents = numDependents;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }

    }
}
