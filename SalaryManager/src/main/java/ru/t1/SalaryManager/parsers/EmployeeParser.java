package ru.t1.SalaryManager.parsers;

import ru.t1.SalaryManager.models.Department;
import ru.t1.SalaryManager.models.Employee;

import java.io.*;
import java.util.*;

public class EmployeeParser {

    public static Map<Department, List<Employee>> getEmployee(String path) {
        return departmentEmployeeMap(path);
    }

    private static List<Employee> parser(String path) {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                Employee employee = new Employee(lineSplit[0], lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]));
                employeeList.add(employee);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private static Map<Department, List<Employee>> departmentEmployeeMap(String path) {
        List<Employee> employeeList = parser(path);
        Map <Department, List<Employee>> employeeMap = new HashMap<>();
        for (Employee employee : employeeList) {
            Department department = new Department(employee.getIdDepartment());
            if (!employeeMap.containsKey(department)) {
                employeeMap.put(department, new ArrayList<Employee>());
                employeeMap.get(department).add(employee);
            } else {
                employeeMap.get(department).add(employee);
            }
        }
        return calculateAvgSalary(employeeMap);
    }

    private static Map<Department, List<Employee>> calculateAvgSalary(Map<Department, List<Employee>> departmentListMap) {
        for (Map.Entry<Department, List<Employee>> entry : departmentListMap.entrySet()) {
            double avg = entry.getValue().stream().mapToDouble(x -> x.getSalary()).average().getAsDouble();
            entry.getKey().setAvgSalary(avg);
        }
        return departmentListMap;

    }
}
