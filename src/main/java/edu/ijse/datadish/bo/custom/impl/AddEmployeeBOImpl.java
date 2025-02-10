package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddEmployeeBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddEmployeeDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEmployeeBOImpl implements AddEmployeeBO {

    AddEmployeeDAO addEmployeeDAO = (AddEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_EMPLOYEE);

    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        Employee employee = DTOConverter.toEntity(employeeDto, Employee.class);

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean userSaved = addEmployeeDAO.addTOUser(employee);
            System.out.println("userSaved: " + userSaved);
            if (!userSaved) {
                connection.rollback();
                return false;
            }

            boolean employeeSaved = addEmployeeDAO.save(employee);
            System.out.println("employeeSaved: " + employeeSaved);
            if (!employeeSaved) {
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

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public void update(EmployeeDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return addEmployeeDAO.generateNewId();
    }

    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean addTOUser(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Employee employee = DTOConverter.toEntity(employeeDto, Employee.class);
        return addEmployeeDAO.addTOUser(employee);
    }
}
