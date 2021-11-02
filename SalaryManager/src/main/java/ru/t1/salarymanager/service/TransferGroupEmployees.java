package ru.t1.salarymanager.service;

import ru.t1.salarymanager.models.Department;
import ru.t1.salarymanager.models.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransferGroupEmployees {

    private static Department departmentI;
    private static Department departmentJ;
    private static BigDecimal avgSalaryDepartmentI;
    private static BigDecimal avgSalaryDepartmentJ;

    public static void createFileTransferEmployees(List<Department> departments,
                                                   String path) {
        departments.sort((o1, o2) -> o2.getAvgSalary().compareTo(o1.getAvgSalary()));
        try(FileWriter writer = new FileWriter(path)) {
            for (int i = 0; i < departments.size(); i++) {
                departmentI = departments.get(i);
                departmentI.getEmployees().sort((Comparator.comparing(Employee::getSalary)));
                avgSalaryDepartmentI = departments.get(i).getAvgSalary();
                for (int j = i + 1; j < departments.size(); j++) {
                    departmentJ = departments.get(j);
                    avgSalaryDepartmentJ = departments.get(j).getAvgSalary();
                    transferEmployeesBetweenDepartment(writer, new ArrayList<>(), 0);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }

    }

    private static void transferEmployeesBetweenDepartment(FileWriter writer, List<Employee> employees, int count){
        for (int i = count; i < departmentI.getEmployees().size();i++) {
            employees.add(departmentI.getEmployees().get(i));
            if (avgSalaryDepartmentI.compareTo(Department.getAvgSalaryEmployees(employees)) > 0) {
                transferEmployeesBetweenDepartment(writer, employees, (i + 1));
            } else {
                employees.remove(departmentI.getEmployees().get(i));
                break;
            }
            employees.remove(departmentI.getEmployees().get(i));
        }
        if (employees.size() > 1
                && Department.getAvgSalaryEmployees(employees).compareTo(avgSalaryDepartmentJ) > 0) {
            writeFile(employees, writer);
        }
    }

    private static void writeFile(List<Employee> employees, FileWriter writer) {
        try {
            writer.write("Из отдела " + departmentI.getName() +
                    " со средней зп " + avgSalaryDepartmentI +
                    " в отдел " + departmentJ.getName() +
                    " со средней зп " + avgSalaryDepartmentJ +
                    "\nПеревести следующих сотрудников: " + listToString(employees) +
                    "После перевода средняя зп в отделе " + departmentI.getName() +
                    " будет " + departmentI.getAvgWithoutEmployees(employees) +
                    ", а в отделе " + departmentJ.getName() +
                    " средня зп будет " + departmentJ.getAvgWithEmployees(employees) +
                    "\n\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }

    private static String listToString(List<Employee> employees) {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (Employee employee : employees) {
            stringBuilder.append(employee.getName()).append(" - ").append(employee.getSalary()).append("\n");
        }
        return stringBuilder.toString();
    }
}