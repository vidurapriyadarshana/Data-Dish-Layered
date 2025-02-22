package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddEmployeeBO extends SuperBO {

    boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    void update(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    EmployeeDto search(String id) throws SQLException, ClassNotFoundException;
    boolean addTOUser(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;

}
