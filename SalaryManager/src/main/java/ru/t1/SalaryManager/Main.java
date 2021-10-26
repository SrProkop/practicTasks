package ru.t1.SalaryManager;

import ru.t1.SalaryManager.models.Department;
import ru.t1.SalaryManager.models.Employee;
import ru.t1.SalaryManager.parsers.EmployeeParser;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Department, List<Employee>> departmentListMap = EmployeeParser.getEmployee("file\\employee.txt");
        System.out.println(departmentListMap);
        for (Map.Entry<Department, List<Employee>> entry : departmentListMap.entrySet()) {
            System.out.println(entry.getKey().getAvgSalary());
        }
    }
}
