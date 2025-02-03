package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeViewBO extends SuperBO {

    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    void update(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    EmployeeDto search(String id) throws SQLException, ClassNotFoundException;
    ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException;

}
