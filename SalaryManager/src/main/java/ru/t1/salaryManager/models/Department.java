package ru.t1.salaryManager.models;

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

    public BigDecimal getAvgSalary() {
        return getBigDecimal(employees);
    }

    private BigDecimal getAvgSalary(List<Employee> list) {
        return getBigDecimal(list);
    }

    private BigDecimal getBigDecimal(List<Employee> list) {
        if (list.size() > 0) {
            BigDecimal avgSalary = new BigDecimal(0);
            for (Employee employee : list) {
                avgSalary = avgSalary.add(employee.getSalary());
            }
            return avgSalary.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP);
        } else {
            System.out.println("В " + this.name + " нет сотрудников");
            return new BigDecimal(0);
        }
    }

    public BigDecimal getAvgWithoutEmployee(Employee employee) {
        List<Employee> list = new ArrayList<>(employees);
        list.remove(employee);
        return getAvgSalary(list);
    }

    public BigDecimal getAvgWithEmployee(Employee employee) {
        List<Employee> list = new ArrayList<>(employees);
        list.add(employee);
        return getAvgSalary(list);
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
