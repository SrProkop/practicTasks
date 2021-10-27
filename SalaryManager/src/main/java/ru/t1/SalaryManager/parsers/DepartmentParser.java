package ru.t1.SalaryManager.parsers;

import ru.t1.SalaryManager.models.Department;
import ru.t1.SalaryManager.models.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DepartmentParser implements IParser{

    @Override
    public Optional<List<Department>> getDepartmentWithEmployees(String path) {
        List<Department> departments = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                if (lineSplit.length == 3 && isNumeric(lineSplit[2])) {
                    Employee employee = new Employee(lineSplit[0], lineSplit[1], new BigDecimal(lineSplit[2]));
                    Department department = new Department(lineSplit[1]);
                    int indexDepartment = departments.indexOf(department);
                    if (indexDepartment >= 0) {
                        departments.get(indexDepartment).getEmployees().add(employee);
                    } else {
                        department.getEmployees().add(employee);
                        departments.add(department);
                    }
                    line = reader.readLine();
                } else {
                    throw new IOException();
                }
            }
            return Optional.of(departments);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return Optional.empty();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return Optional.empty();
        }
    }

    private boolean isNumeric(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("На месте ожидаемой зарплаты встретилась строка, вместо числа");
            return false;
        }
    }
}
