package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.LogInDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LogInBO extends SuperBO {

    boolean checkLogin(LogInDto logInDto) throws SQLException, ClassNotFoundException;
    boolean exist(String email) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    LogInDto search(String id) throws SQLException, ClassNotFoundException;
    ArrayList<LogInDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(LogInDto dto) throws SQLException, ClassNotFoundException;
    void update(LogInDto logInDto) throws SQLException, ClassNotFoundException;

}
