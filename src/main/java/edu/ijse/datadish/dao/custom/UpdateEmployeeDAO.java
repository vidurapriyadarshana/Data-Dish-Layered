package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.entity.Employee;

import java.sql.SQLException;

public interface UpdateEmployeeDAO extends CrudDAO<Employee> {
    String getEmployeeEmail(String employeeID) throws SQLException, ClassNotFoundException;
}
