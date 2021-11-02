package ru.t1.salaryManager.parsers;

import ru.t1.salaryManager.models.Department;

import java.util.Map;
import java.util.Optional;

public interface IParser {

    public Optional<Map<String, Department>> getDepartmentWithEmployees(String path);

}
