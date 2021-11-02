package ru.t1.salaryManager.parsers;

import ru.t1.salaryManager.models.Department;
import ru.t1.salaryManager.models.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DepartmentParserTxt implements IParser{

    @Override
    public Optional<Map<String, Department>> getDepartmentWithEmployees(String path) {
        Map<String, Department> departments = new HashMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int numberLine = 1;
            while (line != null) {
                String[] lineSplit = line.split(",");
                if (isValidLine(lineSplit, numberLine)) {
                    Employee employee = new Employee(lineSplit[0], new BigDecimal(lineSplit[2]));
                    Department department = new Department(lineSplit[1]);
                    departments.putIfAbsent(lineSplit[1].trim(), department);
                    departments.get(lineSplit[1]).getEmployees().add(employee);
                }
                line = reader.readLine();
                numberLine++;
            }
            printInformationDepartment(departments.values());
            return Optional.of(departments);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return Optional.empty();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return Optional.empty();
        }
    }

    private boolean isValidLine(String[] lineArray, int numberLine) {
        if (lineArray.length == 1 && lineArray[0].trim().length() == 0) {
            return false;
        }
        if (lineArray.length != 3) {
            System.out.println("Ошибка в строке " + numberLine +
                    ": не удалось прочитать информацию о сотруднике");
            return false;
        }
        if (!isNumeric(lineArray[2], numberLine)) {
            return false;
        }
        for (int i = 0; i < lineArray.length; i++) {
            if (lineArray[i].trim().length() == 0) {
                System.out.println("Ошибка в строке " + numberLine +
                        ": пустое значение у " + (i + 1) + " поля");
                return false;
            }
        }
        return true;
    }

    private boolean isNumeric(String line, int numberLine) {
        try {
            BigDecimal bigDecimal = new BigDecimal(line);
            String[] splitLine = line.split("\\.");
            if (splitLine.length == 2 && splitLine[1].toCharArray().length > 2) {
                System.out.println("Ошибка в строке " + numberLine +
                        ": округлите зарплату до сотых копеек");
                return false;
            }
            if (bigDecimal.intValue() < 0) {
                System.out.println("Ошибка в строке " + numberLine +
                        ": зарплата не может быть отрицательной");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в строке " + numberLine +
                    ": на месте зарплаты встретилась строка");
            return false;
        }
    }

    private void printInformationDepartment(Collection<Department> list) {
        for (Department department : list) {
            System.out.println(department.getName() + " - " + department.getAvgSalary() +
                    "\nСотрудники: ");
            for ( Employee employee : department.getEmployees()) {
                System.out.println(employee.getName() + " - " + employee.getSalary());
            }
        }
    }
}
