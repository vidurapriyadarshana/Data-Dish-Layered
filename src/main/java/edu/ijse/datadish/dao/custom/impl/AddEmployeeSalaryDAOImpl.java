package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddEmployeeSalaryDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.entity.Employee;
import edu.ijse.datadish.entity.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeSalaryDAOImpl implements AddEmployeeSalaryDAO {

//    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
//        ArrayList<Employee> employeeNames = new ArrayList<>();
//
//        ResultSet rst = SQLUtil.execute("SELECT Name FROM employee");
//
//        if (rst != null) {
//            while (rst.next()) {
//                employeeNames.add(new Employee(rst.getString("Name")));
//            }
//        }
//
//        return employeeNames;
//    }

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT EmployeeID, Name, Address, Contact, Email, HireDate, Status FROM employee");

        if (rst != null) {
            while (rst.next()) {
                employees.add(new Employee(
                        rst.getString("EmployeeID"),
                        rst.getString("Name"),
                        rst.getString("Address"),
                        rst.getString("Contact"),
                        rst.getString("Email"),
                        rst.getString("HireDate"),
                        rst.getString("Status")
                ));
            }
        }

        return employees;
    }


    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(Employee dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }


    public String generateNewId(String employeeName) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT EmployeeID FROM employee WHERE Name = ?";
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, employeeName);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        if (resultSet.next()) {
//            return resultSet.getString("EmployeeID");
//        } else {
//            return null;
//        }

        ResultSet rst = SQLUtil.execute("SELECT EmployeeID FROM employee WHERE Name = ?", employeeName);

        if (rst != null && rst.next()) {
            return rst.getString("EmployeeID");
        }

        return null;
    }

    @Override
    public List<String> getEmployeeNames() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT Name FROM employee";
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        ResultSet resultSet = statement.executeQuery();
        List<String> employeeNames = new ArrayList<>();
//
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM employee");
        while (resultSet.next()) {
            employeeNames.add(resultSet.getString("Name"));
        }

        return employeeNames;
    }

    public boolean save(Salary salaryDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary (SalaryID, EmployeeID,Amount, PaymentDate) VALUES (?, ?, ?, ?)",
                salaryDto.getSalaryId(), salaryDto.getEmployeeId(), salaryDto.getAmount(), salaryDto.getDate());
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SalaryID FROM salary ORDER BY SalaryID DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString("SalaryID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("S%03d", number + 1);
        } else {
            return "S001";
        }
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
