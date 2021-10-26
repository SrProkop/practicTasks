package ru.t1.SalaryManager.models;

public class Employee {
    private String id;
    private String name;
    private String idDepartment;
    private int salary;

    public Employee() {
    }

    public Employee(String id, String name, String idDepartment, int salary) {
        this.id = id;
        this.name = name;
        this.idDepartment = idDepartment;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", idDepartment='" + idDepartment + '\'' +
                ", salary=" + salary +
                '}';
    }
}
