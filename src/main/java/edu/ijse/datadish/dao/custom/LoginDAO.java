package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.LogInDto;
import java.sql.SQLException;

public interface LoginDAO {

    boolean checkLogin(LogInDto logInDto) throws SQLException, ClassNotFoundException;

    boolean checkEmail(String email) throws SQLException, ClassNotFoundException;

    boolean updatePassword(String email, String newPassword) throws SQLException, ClassNotFoundException;
}
