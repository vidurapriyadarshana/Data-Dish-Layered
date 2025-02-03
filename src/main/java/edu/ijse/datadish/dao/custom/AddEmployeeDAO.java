package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.entity.Employee;

import java.sql.SQLException;

public interface AddEmployeeDAO extends CrudDAO<Employee> {
    boolean addTOUser(Employee employeeDto) throws SQLException, ClassNotFoundException;
}
