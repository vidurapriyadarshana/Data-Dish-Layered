package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddEmployeeSalaryBO extends SuperBO {

    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;

    boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    void update(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    void delete(String id) throws SQLException, ClassNotFoundException;

    String generateNewId(String employeeName) throws SQLException, ClassNotFoundException;

    boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    EmployeeDto search(String id) throws SQLException, ClassNotFoundException;
}
