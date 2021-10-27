package ru.t1.SalaryManager.service;

import ru.t1.SalaryManager.models.Department;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class TransferEmployees {

    public static void createFileTransferEmployeesBetweenDepartments(List<Department> departments) {
        Collections.sort(departments, (o1, o2) -> o2.getAvgSalary().compareTo(o1.getAvgSalary()));
        try (FileWriter writer = new FileWriter("transferEmployees.txt", false)) {
            for (int i = 0; i < departments.size(); i++) {
                BigDecimal avgSalaryDepartmentI = departments.get(i).getAvgSalary();
                int finalI = i;
                for (int j = i + 1; j < departments.size(); j++) {
                    BigDecimal avgSalaryDepartmentJ = departments.get(j).getAvgSalary();
                    int finalJ = j;
                    departments.get(i)
                            .getEmployees()
                            .stream()
                            .filter(employee -> avgSalaryDepartmentI.compareTo(employee.getSalary()) > 0
                                    && employee.getSalary().compareTo(avgSalaryDepartmentJ) > 0)
                            .forEach(employee -> {
                                try {
                                    writer.write("Имя: " + employee.getName() +
                                            " из отдела " + departments.get(finalI).getName() +
                                            " в отдел " + departments.get(finalJ).getName() +
                                            "\n");
                                } catch (IOException e) {
                                    System.out.println("Ошибка записи файла");
                                    return;
                                }
                            });
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return;
        }
    }
}
