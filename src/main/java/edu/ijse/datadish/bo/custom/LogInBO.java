package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.LogInDto;

import java.sql.SQLException;

public interface LogInBO extends SuperBO {
    boolean checkLogin(LogInDto logInDto) throws SQLException, ClassNotFoundException;
}
