package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddEmployeeDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEmployeeDAOImpl implements AddEmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (EmployeeID, Name, Contact, HireDate, Status, Address, Email, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dto.getEmployeeID(), dto.getEmployeeName(), dto.getEmployeeContact(), dto.getHireDate(), dto.getEmployeeStatus(), dto.getAddress(), dto.getEmail(), dto.getUserName());
    }

    @Override
    public void update(Employee dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT EmployeeID FROM employee ORDER BY EmployeeID DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString("EmployeeID");
            int newCustomerId = Integer.parseInt(lastID.substring(1)) + 1;
            return String.format("E%03d", newCustomerId);
        } else {
            return "E001";
        }
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean addTOUser(Employee employeeDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (UserName, Password, Email, Role) VALUES (?, ?, ?, ?)",
                employeeDto.getUserName(), employeeDto.getPassword(), employeeDto.getEmail(), employeeDto.getRole());
    }
}