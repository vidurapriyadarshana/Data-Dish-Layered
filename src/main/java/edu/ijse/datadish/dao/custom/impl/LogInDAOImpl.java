package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.LoginDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.entity.LogIn;
import edu.ijse.datadish.util.Refarance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInDAOImpl implements LoginDAO {

    LogIn logIn = new LogIn();

    public boolean exist(String email) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT Email FROM user WHERE Email = ?", email);
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public LogIn search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE Email = ?", id);
        logIn.setRole(resultSet.getString("Role"));
        return logIn;
    }

    @Override
    public ArrayList<LogIn> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(LogIn dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(LogIn logInDto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE user SET Password = ? WHERE Email = ?", logInDto.getEmail(), logInDto.getPassword());
    }

    @Override
    public boolean getEmployeeID(String userName) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("SELECT EmployeeID FROM employee WHERE UserName = ?" , userName);
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT EmployeeID FROM employee WHERE UserName = ?");
        pstm.setObject(1, userName);
        ResultSet rst = pstm.executeQuery();
        return rst.next();
    }

    @Override
    public boolean getUserInfo(String userName) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("SELECT * FROM user WHERE UserName = ?", userName);

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE UserName = ?");
        pstm.setObject(1, userName);
        ResultSet rst = pstm.executeQuery();
        return rst.next();

    }

    @Override
    public String getRole(LogIn logIn) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Role FROM user WHERE UserName = ?", logIn.getUserName());

        if (rst.next()) {
            return rst.getString("Role");
        } else {
            return null;
        }
    }

    @Override
    public String getID(String userName) throws SQLException, ClassNotFoundException {
//        ResultSet rst = SQLUtil.execute("SELECT EmployeeID FROM EMPLOYEE WHERE UserName = ?", logIn.getUserName());
//
//        if (rst.next()) {
//            return rst.getString("EmployeeID");
//        } else {
//            return null;
//        }

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT EmployeeID FROM EMPLOYEE WHERE UserName = ?");
        pstm.setObject(1, userName);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return rst.getString("EmployeeID");
        } else {
            return null;
        }
    }


}