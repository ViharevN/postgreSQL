package me.viharev.postgreshw.services.impl;

import me.viharev.postgreshw.models.Employee;
import me.viharev.postgreshw.services.LoadData;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadDataImpl implements LoadData {
    List<Employee> employees = new ArrayList<>();
    private String DB_URL = "jdbc:postgresql://localhost:5432/skypro";
    private String DB_USER = "postgres";
    private String DB_PASSWORD = "00000";
    Connection connection;
    {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Employee> getData() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt("id");
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            String gender = result.getString("gender");
            int age = result.getInt("age");
            employees.add(new Employee(id, first_name, last_name, gender, age));
        }
        return employees;
    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee (first_name, last_name, gender, age) VALUES(?,?,?,?)");
        preparedStatement.setString(1, employee.getFirst_name());
        preparedStatement.setString(2, employee.getLast_name());
        preparedStatement.setString(3, employee.getGender());
        preparedStatement.setInt(4, employee.getAge());
        preparedStatement.executeUpdate();
    }
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET first_name = ?, last_name = ?, gender = ?, age = ? WHERE id =?");
        preparedStatement.setString(1, employee.getFirst_name());
        preparedStatement.setString(2, employee.getLast_name());
        preparedStatement.setString(3, employee.getGender());
        preparedStatement.setInt(4, employee.getAge());
        preparedStatement.setInt(5, employee.getId());
        preparedStatement.executeUpdate();
    }
    @Override
    public void deleteEmployee(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}
