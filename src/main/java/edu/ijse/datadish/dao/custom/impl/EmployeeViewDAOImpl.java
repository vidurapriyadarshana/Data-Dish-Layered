package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.EmployeeViewDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeViewDAOImpl implements EmployeeViewDAO {

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        while (resultSet.next()) {
            String id = resultSet.getString("EmployeeID");
            String name = resultSet.getString("Name");
            String contact = resultSet.getString("Contact");
            String hireDate = resultSet.getString("HireDate");
            String userName = resultSet.getString("UserName");
            String status = resultSet.getString("Status");
            String address = resultSet.getString("Address");

            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setEmployeeID(id);
            employeeDto.setEmployeeName(name);
            employeeDto.setEmployeeContact(contact);
            employeeDto.setHireDate(hireDate);
            employeeDto.setUserName(userName);
            employeeDto.setEmployeeStatus(status);
            employeeDto.setAddress(address);

            employeeList.add(employeeDto);
        }

        resultSet.close();

        return employeeList;
    }

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(EmployeeDto dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE employee SET Status = 'Inactive' WHERE EmployeeID = ?;",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException {
        ObservableList<SalaryDto> salaryView = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute(
                "SELECT s.SalaryID, e.Name AS EmployeeName, s.PaymentDate, s.Amount FROM salary s JOIN employee e ON s.EmployeeID = e.EmployeeID"
        );

        while (resultSet.next()) {
            String salaryId = resultSet.getString("SalaryID");
            String employeeName = resultSet.getString("EmployeeName");
            String paymentDate = resultSet.getString("PaymentDate");
            double amount = resultSet.getDouble("Amount");

            SalaryDto salaryDto = new SalaryDto(salaryId, employeeName, paymentDate, amount);
            salaryView.add(salaryDto);
        }

        resultSet.close();

        return salaryView;
    }

}