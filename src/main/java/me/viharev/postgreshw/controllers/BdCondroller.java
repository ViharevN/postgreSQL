package me.viharev.postgreshw.controllers;

import me.viharev.postgreshw.models.Employee;
import me.viharev.postgreshw.services.LoadData;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class BdCondroller {
    private LoadData loadData;

    public BdCondroller(LoadData loadData) {
        this.loadData = loadData;
    }

    @GetMapping
    public List<Employee> getData() throws SQLException {
       return loadData.getData();
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) throws SQLException {
        loadData.addEmployee(employee);
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee employee) throws SQLException {
        loadData.updateEmployee(employee);
    }

    @DeleteMapping
    public void deleteEmployee(@RequestParam int id) throws SQLException {
        loadData.deleteEmployee(id);
    }
}
