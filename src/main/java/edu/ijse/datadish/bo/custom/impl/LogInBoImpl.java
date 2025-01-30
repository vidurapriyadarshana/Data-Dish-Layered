package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.LogInBO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.util.Refarance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInBoImpl implements LogInBO {

    public boolean checkLogin(LogInDto logInDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            String userQuery = "SELECT * FROM user WHERE UserName = ?";
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            userStatement.setString(1, logInDto.getUserName());

            System.out.println("Checking user credentials...");

            ResultSet userResult = userStatement.executeQuery();

            if (!userResult.next()) {
                System.out.println("User not found.");
                connection.rollback();
                return false;
            }

            String password = userResult.getString("Password");
            String role = userResult.getString("Role");
            String email = userResult.getString("Email");

            if (!logInDto.getPassword().equals(password)) {
                System.out.println("Password mismatch.");
                connection.rollback();
                return false;
            }

            logInDto.setRole(role);
            logInDto.setEmail(email);

            System.out.println("User credentials verified.");
            System.out.println("Role: " + role + ", Email: " + email);

            String empIdQuery = "SELECT EmployeeID FROM employee WHERE UserName = ?";
            PreparedStatement empStatement = connection.prepareStatement(empIdQuery);
            empStatement.setString(1, logInDto.getUserName());

            System.out.println("Fetching Employee ID...");

            ResultSet empResult = empStatement.executeQuery();

            if (empResult.next()) {
                String employeeId = empResult.getString("EmployeeID");
                Refarance.employeeID = employeeId;
                System.out.println("Employee ID: " + employeeId);
            } else {
                System.out.println("Employee ID not found.");
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error during login check: " + e.getMessage());
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
