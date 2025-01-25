package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AddEmployeeDAO {
    boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    String generateNextID();
}
