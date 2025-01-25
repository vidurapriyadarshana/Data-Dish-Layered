package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AddEmployeeSalaryDAO {
    List<String> getEmployeeNames() throws SQLException, ClassNotFoundException;
    String getEmployeeId(String employeeName) throws SQLException, ClassNotFoundException;
    boolean addSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException;
    String generateNextID();
}
