package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.UpdateEmployeeBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.UpdateEmployeeDAOImpl;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateEmployeeBOImpl implements UpdateEmployeeBO {

    UpdateEmployeeDAOImpl updateEmployeeDAOImpl = (UpdateEmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UPDATE_EMPLOYEE);

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Employee employeeDto1 = DTOConverter.toEntity(employeeDto, Employee.class);
        updateEmployeeDAOImpl.update(employeeDto1);
    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getEmployeeEmail(String employeeID) throws SQLException, ClassNotFoundException {
//        return updateEmployeeDAOImpl.getEmployeeEmail(employeeID);
        String email = updateEmployeeDAOImpl.getEmployeeEmail(employeeID);
        System.out.println(email);
        return email;
    }

}
