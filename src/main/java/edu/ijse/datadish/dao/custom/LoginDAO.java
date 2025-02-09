package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.entity.LogIn;

import java.sql.SQLException;

public interface LoginDAO extends CrudDAO<LogIn> {
    boolean getEmployeeID(String userName) throws SQLException, ClassNotFoundException;
    boolean getUserInfo(String userName) throws SQLException, ClassNotFoundException;
    String getRole(LogIn logIn) throws SQLException, ClassNotFoundException;
    String getID(String userName) throws SQLException, ClassNotFoundException;
}
