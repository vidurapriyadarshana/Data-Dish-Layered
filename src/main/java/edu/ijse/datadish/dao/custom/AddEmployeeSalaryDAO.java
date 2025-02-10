package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface AddEmployeeSalaryDAO extends CrudDAO<Employee> {
    String generateNewId(String employeeName) throws SQLException, ClassNotFoundException;
    List<String> getEmployeeNames() throws SQLException, ClassNotFoundException;
}
