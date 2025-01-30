package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddEmployeeSalaryDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEmployeeSalaryDAOImpl implements AddEmployeeSalaryDAO {

    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeNames = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT Name FROM employee");

        if (rst != null) {
            while (rst.next()) {
                employeeNames.add(new EmployeeDto(rst.getString("Name")));
            }
        }

        return employeeNames;
    }


    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(EmployeeDto dto) throws SQLException, ClassNotFoundException {

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

    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO salary (SalaryID, EmployeeID,Amount, PaymentDate) VALUES (?, ?, ?, ?)";
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1, salaryDto.getSalaryId());
//        statement.setString(2, salaryDto.getEmployeeId());
//        statement.setDouble(3, salaryDto.getAmount());
//        statement.setString(4, salaryDto.getDate());
//
//        return statement.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO salary (SalaryID, EmployeeID,Amount, PaymentDate) VALUES (?, ?, ?, ?)",
                salaryDto.getSalaryId(), salaryDto.getEmployeeId(), salaryDto.getAmount(), salaryDto.getDate());
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            if (connection == null) {
//                System.out.println("Database connection failed.");
//                return "S001";
//            }
//
//            String query = "SELECT SalaryID FROM salary ORDER BY SalaryID DESC LIMIT 1";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                String lastID = resultSet.getString("SalaryID");
//                int number = Integer.parseInt(lastID.substring(1));
//                return String.format("S%03d", number + 1);
//            } else {
//                return "S001";
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Exception: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return "S001";

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
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
