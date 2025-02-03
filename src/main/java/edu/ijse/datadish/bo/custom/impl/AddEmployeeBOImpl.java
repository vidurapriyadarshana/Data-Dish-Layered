package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.AddEmployeeBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.AddEmployeeDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeBOImpl implements AddEmployeeBO {

    AddEmployeeDAO addEmployeeDAO =(AddEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_EMPLOYEE);

    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean employeeSaved = addEmployeeDAO.save(employeeDto);
            if (!employeeSaved) {
                connection.rollback();
                return false;
            }

            boolean userSaved = addEmployeeDAO.addTOUser(employeeDto);
            if (!userSaved) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;

        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }


//    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
////        String employeeSql = "INSERT INTO employee (EmployeeID, Name, Contact, HireDate, Status, Address, Email, UserName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        boolean employee = addEmployeeDAO.save(employeeDto);
////        String userSql = "INSERT INTO user (UserName, Password, Email, Role) VALUES (?, ?, ?, ?)";
//        boolean employeeSql = addEmployeeDAO.addTOUser(employeeDto);
//
//        Connection connection = null;
//        PreparedStatement employeeStatement = null;
//        PreparedStatement userStatement = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            // Hash the password before saving
//            String hashedPassword = employeeDto.getPassword();
//
//            // Save user details
//            userStatement = connection.prepareStatement(userSql);
//            userStatement.setString(1, employeeDto.getUserName());
//            userStatement.setString(2, hashedPassword);
//            userStatement.setString(3, employeeDto.getEmail());
//            userStatement.setString(4, employeeDto.getRole());
//            userStatement.executeUpdate();
//
//            // Save employee details
//            employeeStatement = connection.prepareStatement(employeeSql);
//            employeeStatement.setString(1, employeeDto.getEmployeeID());
//            employeeStatement.setString(2, employeeDto.getEmployeeName());
//            employeeStatement.setString(3, employeeDto.getEmployeeContact());
//            employeeStatement.setString(4, employeeDto.getHireDate());
//            employeeStatement.setString(5, employeeDto.getEmployeeStatus());
//            employeeStatement.setString(6, employeeDto.getAddress());
//            employeeStatement.setString(7, employeeDto.getEmail());
//            employeeStatement.setString(8, employeeDto.getUserName());
//            employeeStatement.executeUpdate();
//
//            connection.commit();
//            return true;
//
//        } catch (SQLException e) {
//            if (connection != null) {
//                connection.rollback();
//            }
//            throw e;
//
//        } finally {
//            if (employeeStatement != null) {
//                employeeStatement.close();
//            }
//            if (userStatement != null) {
//                userStatement.close();
//            }
//            if (connection != null) {
//                connection.setAutoCommit(true);
//            }
//        }
//    }
}
