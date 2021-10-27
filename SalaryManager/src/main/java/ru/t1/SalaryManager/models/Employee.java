package ru.t1.SalaryManager.models;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private String departmentName;
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(String name, String departmentName, BigDecimal salary) {
        this.name = name;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
