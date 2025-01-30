package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.UpdateEmployeeDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateEmployeeDAOImpl implements UpdateEmployeeDAO {

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE employee SET Address = ? , Contact = ?, Status = ? , Email = ? WHERE EmployeeID = ?",
                employeeDto.getAddress(),
                employeeDto.getEmployeeContact(),
                employeeDto.getEmployeeStatus(),
                employeeDto.getEmail(),
                employeeDto.getEmployeeID());
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
        return "";
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getEmployeeEmail(String employeeID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT Email FROM employee WHERE EmployeeID = ?", employeeID);
    }
}
