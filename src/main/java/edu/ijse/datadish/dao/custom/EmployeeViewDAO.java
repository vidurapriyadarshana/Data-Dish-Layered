package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface EmployeeViewDAO {

    ObservableList<EmployeeDto> loadEmpTable() throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;

    ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException;
}
