package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.LogInBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.EmployeeViewDAOImpl;
import edu.ijse.datadish.dao.custom.impl.LogInDAOImpl;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.entity.LogIn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInBoImpl implements LogInBO {

    LogInDAOImpl logInDAO = (LogInDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    EmployeeViewDAOImpl employeeViewDAO = (EmployeeViewDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_VIEW);

    public boolean checkLogin(LogInDto logInDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean userInfo = employeeViewDAO.getUserInfo(logInDto.getUserName());

            if (!userInfo) {
                connection.rollback();
                return false;
            }


            boolean employee = employeeViewDAO.getEmployeeID(logInDto.getUserName());

            if (!employee) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean exist(String email) throws SQLException, ClassNotFoundException {
        return logInDAO.exist(email);
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public LogInDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<LogInDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(LogInDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(LogInDto logInDto) throws SQLException, ClassNotFoundException {
        LogIn logIn = DTOConverter.toEntity(logInDto, LogIn.class);
        logInDAO.update(logIn);
    }

}
