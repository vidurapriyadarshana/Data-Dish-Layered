package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.SQLException;

public interface UpdateEmployeeDAO {
    boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;

    String getEmployeeEmail(String employeeID) throws SQLException, ClassNotFoundException;
}
