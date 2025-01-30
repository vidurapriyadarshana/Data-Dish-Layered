package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.SQLException;

public interface AddEmployeeBO extends SuperBO {
    boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
}
