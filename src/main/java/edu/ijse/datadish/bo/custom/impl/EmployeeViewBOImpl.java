package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.EmployeeViewBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.EmployeeViewDAOImpl;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.entity.Employee;
import edu.ijse.datadish.entity.Salary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeViewBOImpl implements EmployeeViewBO {

    EmployeeViewDAOImpl employeeViewDAO = (EmployeeViewDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_VIEW);

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeViewDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDtos.add(DTOConverter.toDTO(employee, EmployeeDto.class));
        }

        return employeeDtos;
    }

    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(EmployeeDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        employeeViewDAO.delete(id);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ObservableList<SalaryDto> loadSalaryTable() throws SQLException, ClassNotFoundException {
        ObservableList<Salary> salaryDto = employeeViewDAO.loadSalaryTable();
        ObservableList<SalaryDto> salaryDtos = FXCollections.observableArrayList();

        for (Salary salary : salaryDto) {
            salaryDtos.add(DTOConverter.toDTO(salary, SalaryDto.class));
        }

        return salaryDtos;
    }

}
