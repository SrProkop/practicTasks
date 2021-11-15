package ru.t1.salarymanager.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department() {
        this.employees = new ArrayList<>();
    }

    public Department(String name) {
        this.name = name.trim();
        this.employees = new ArrayList<>();
    }

    public static BigDecimal getAvgSalaryEmployees(List<Employee> employees) {
        if (employees.size() > 0) {
            BigDecimal avgSalary = BigDecimal.ZERO;
            for (Employee employee : employees) {
                avgSalary = avgSalary.add(employee.getSalary());
            }
            return avgSalary.divide(new BigDecimal(employees.size()), 2, RoundingMode.HALF_UP);
        } else {
            System.out.println("Список пустой");
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getAvgSalary() {
        return Department.getAvgSalaryEmployees(employees);
    }

    public BigDecimal getAvgWithoutEmployee(Employee employee) {
        List<Employee> list = new ArrayList<>(employees);
        list.remove(employee);
        return Department.getAvgSalaryEmployees(list);
    }

    public BigDecimal getAvgWithEmployee(Employee employee) {
        List<Employee> list = new ArrayList<>(this.employees);
        list.add(employee);
        return Department.getAvgSalaryEmployees(list);
    }

    public BigDecimal getAvgWithoutEmployees(List<Employee> employees) {
        List<Employee> list = new ArrayList<>(this.employees);
        list.removeAll(employees);
        return Department.getAvgSalaryEmployees(list);
    }

    public BigDecimal getAvgWithEmployees(List<Employee> employees) {
        List<Employee> list = new ArrayList<>(employees);
        list.addAll(employees);
        return Department.getAvgSalaryEmployees(list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
