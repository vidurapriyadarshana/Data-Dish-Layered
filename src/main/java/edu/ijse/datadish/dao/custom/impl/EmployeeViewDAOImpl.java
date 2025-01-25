package edu.ijse.datadish.dao.custom.impl;

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

public class EmployeeViewDAOImpl implements EmployeeViewDAO {

    public ObservableList<EmployeeDto> loadEmpTable() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeDto> employeeView = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

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

            employeeView.add(employeeDto);
        }
        return employeeView;
    }

    public boolean deleteEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET Status = 'Inactive' WHERE EmployeeID = ?;";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employeeDto.getEmployeeID());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException {
        ObservableList<SalaryDto> salaryView = FXCollections.observableArrayList();

        String sql = "SELECT s.SalaryID, e.Name AS EmployeeName, s.PaymentDate, s.Amount FROM salary s JOIN employee e ON s.EmployeeID = e.EmployeeID";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String salaryId = resultSet.getString("SalaryID");
            String employeeName = resultSet.getString("EmployeeName");
            String paymentDate = resultSet.getString("PaymentDate");
            double amount = resultSet.getDouble("Amount");

            SalaryDto salaryDto = new SalaryDto(salaryId, employeeName, paymentDate, amount);
            salaryView.add(salaryDto);
        }

        return salaryView;
    }
}