package ru.t1.salarymanager;

import ru.t1.salarymanager.models.Department;
import ru.t1.salarymanager.parsers.DepartmentParserTxt;
import ru.t1.salarymanager.parsers.IParser;
import ru.t1.salarymanager.service.TransferEmployees;
import ru.t1.salarymanager.service.TransferGroupEmployees;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Main {
    private static IParser parser = new DepartmentParserTxt();

    public static void main(String[] args) {
        if (args.length == 2) {
            Optional<Map<String, Department>> optionalDepartments = parser.getDepartmentWithEmployees(args[0]);
            if (optionalDepartments.isPresent()) {
                TransferGroupEmployees.createFileTransferEmployees(new ArrayList<>(optionalDepartments.get().values()), args[1]);
            } else {
                System.out.println("Программа завершилось ошибкой");
            }
        } else {
            System.out.println("Ошибка ввода аргументов. В аргументах нужно передавать пути к входному и выходном файлам через пробел");
        }
    }
}
