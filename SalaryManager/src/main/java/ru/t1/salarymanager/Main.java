package ru.t1.salaryManager;

import ru.t1.salaryManager.models.Department;
import ru.t1.salaryManager.parsers.DepartmentParserTxt;
import ru.t1.salaryManager.parsers.IParser;
import ru.t1.salaryManager.service.TransferEmployees;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Main {
    private static IParser parser = new DepartmentParserTxt();

    public static void main(String[] args) {
        if (args.length == 2) {
            Optional<Map<String, Department>> optionalDepartments = parser.getDepartmentWithEmployees(args[0]);
            if (optionalDepartments.isPresent()) {
                TransferEmployees.createFileTransferEmployeesBetweenDepartments(new ArrayList<>(optionalDepartments.get().values()), args[1]);
            } else {
                System.out.println("Программа завершилось ошибкой");
            }
        } else {
            System.out.println("Ошибка ввода аргументов");
        }
    }
}
