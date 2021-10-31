package ru.t1.salaryManager.service;

import ru.t1.salaryManager.models.Department;
import ru.t1.salaryManager.models.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class TransferEmployees {

    public static void createFileTransferEmployeesBetweenDepartments(List<Department> departments,
                                                                     String path) {
        Collections.sort(departments, (o1, o2) -> o2.getAvgSalary().compareTo(o1.getAvgSalary()));
        try (FileWriter writer = new FileWriter(path, false)) {
            for (int i = 0; i < departments.size(); i++) {
                BigDecimal avgSalaryDepartmentI = departments.get(i).getAvgSalary();
                for (int j = i + 1; j < departments.size(); j++) {
                    BigDecimal avgSalaryDepartmentJ = departments.get(j).getAvgSalary();
                    transferEmployeesBetweenDepartment(departments.get(i),
                            avgSalaryDepartmentI,
                            departments.get(j),
                            avgSalaryDepartmentJ,
                            writer);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    private static void transferEmployeesBetweenDepartment(Department departmentI,
                                                           BigDecimal avgSalaryI,
                                                           Department departmentJ,
                                                           BigDecimal avgSalaryJ,
                                                           FileWriter writer) {
        departmentI.getEmployees().stream()
                .filter(employee -> avgSalaryI.compareTo(employee.getSalary()) > 0
                        && employee.getSalary().compareTo(avgSalaryJ) > 0)
                .forEach(employee -> writeFile(employee,
                        departmentI,
                        departmentJ,
                        writer));

    }

    private static void writeFile(Employee employee,
                                  Department departmentI,
                                  Department departmentJ,
                                  FileWriter writer) {
        try {
            writer.write("Имя: " + employee.getName() +
                    " из отдела " + departmentI.getName() +
                    " с средней зп " + departmentI.getAvgSalary() +
                    " в отдел " + departmentJ.getName() +
                    " с средней зп " + departmentJ.getAvgSalary() +
                    "\n" +
                    "После перевода в отделе " + departmentI.getName() +
                    " средняя зп станет " + departmentI.getAvgWithoutEmployee(employee) +
                    ", а в отделе " + departmentJ.getName() +
                    " средняя зп станет " + departmentJ.getAvgWithEmployee(employee) +
                    "\n\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
            return;
        }
    }
}
