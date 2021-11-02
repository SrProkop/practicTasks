package ru.t1.salarymanager.parsers;

import ru.t1.salarymanager.models.Department;

import java.util.Map;
import java.util.Optional;

public interface IParser {

    public Optional<Map<String, Department>> getDepartmentWithEmployees(String path);

}
