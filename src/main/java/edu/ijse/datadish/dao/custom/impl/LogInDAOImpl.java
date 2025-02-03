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
        return null;
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



}