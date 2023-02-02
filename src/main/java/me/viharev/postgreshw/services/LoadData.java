package me.viharev.postgreshw.services;

import me.viharev.postgreshw.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface LoadData {
    List<Employee> getData() throws SQLException;

    void addEmployee(Employee employee) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;
}
