package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.entity.Employee;
import edu.ijse.datadish.entity.Salary;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface EmployeeViewDAO extends CrudDAO<Employee> {
    ObservableList<Salary> loadSalaryTable() throws SQLException, ClassNotFoundException;
    boolean getEmployeeID(String userName) throws SQLException, ClassNotFoundException;
    boolean getUserInfo(String userName) throws SQLException, ClassNotFoundException;
}
