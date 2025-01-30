package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface EmployeeViewDAO extends CrudDAO<EmployeeDto> {
    ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException;
}
