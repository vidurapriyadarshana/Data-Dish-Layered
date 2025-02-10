package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddEmployeeSalaryBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.AddEmployeeSalaryDAOImpl;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.entity.Employee;
import edu.ijse.datadish.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeSalaryBOImpl implements AddEmployeeSalaryBO {

    AddEmployeeSalaryDAOImpl addEmployeeSalaryDAO = (AddEmployeeSalaryDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_EMPLOYEE_SALARY);

//    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
//        ArrayList<Employee> employees = addEmployeeSalaryDAO.getAll();
//
//        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
//
//        for (Employee employee : employees) {
//            employeeDtos.add(DTOConverter.toDTO(employee, EmployeeDto.class));
//        }
//
//        return employeeDtos;
//    }

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = addEmployeeSalaryDAO.getAll();
        return DTOConverter.toDTOList(employees, EmployeeDto.class);
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

    }

    public String generateNewId(String employeeName) throws SQLException, ClassNotFoundException {
        return addEmployeeSalaryDAO.generateNewId(employeeName);
    }

    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        Salary salary = DTOConverter.toEntity(salaryDto, Salary.class);
        return addEmployeeSalaryDAO.save(salary);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return addEmployeeSalaryDAO.generateNewId();
    }

    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public List<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
        return addEmployeeSalaryDAO.getEmployeeNames();
    }
}
