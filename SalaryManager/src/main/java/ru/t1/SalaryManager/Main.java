package ru.t1.SalaryManager;

import ru.t1.SalaryManager.models.Department;
import ru.t1.SalaryManager.parsers.DepartmentParser;
import ru.t1.SalaryManager.parsers.IParser;
import ru.t1.SalaryManager.service.TransferEmployees;

import java.util.List;
import java.util.Optional;

public class Main {
    private static IParser parser = new DepartmentParser();

    public static void main(String[] args) {
        Optional<List<Department>> optionalDepartments = parser.getDepartmentWithEmployees(args[0]);
        if (optionalDepartments.isPresent()) {
            TransferEmployees.createFileTransferEmployeesBetweenDepartments(optionalDepartments.get());
        } else {
            System.out.println("Программа завершилась ошибкой");
        }
    }
}
