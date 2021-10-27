package ru.t1.SalaryManager.parsers;

import ru.t1.SalaryManager.models.Department;

import java.util.List;
import java.util.Optional;

public interface IParser {

    public Optional<List<Department>> getDepartmentWithEmployees(String path);

}
