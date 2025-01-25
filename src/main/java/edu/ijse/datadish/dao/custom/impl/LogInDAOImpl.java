package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.custom.LoginDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.util.Refarance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInDAOImpl implements LoginDAO {

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

    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String query = "SELECT Email FROM user WHERE Email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public boolean updatePassword(String email, String newPassword) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user SET Password = ? WHERE Email = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, newPassword);
        statement.setString(2, email);

        return statement.executeUpdate() > 0;
    }

}