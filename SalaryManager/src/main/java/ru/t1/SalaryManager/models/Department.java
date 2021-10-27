package ru.t1.SalaryManager.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public BigDecimal getAvgSalary() {
        if (employees.size() > 0) {
            BigDecimal avgSalary = new BigDecimal(0);
            for (Employee employee : employees) {
                avgSalary = avgSalary.add(employee.getSalary());
            }
            return avgSalary.divide(new BigDecimal(employees.size()), 2, RoundingMode.HALF_UP);
        } else {
            System.out.println("В этом отделе нет сотрудников");
            return new BigDecimal(0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
