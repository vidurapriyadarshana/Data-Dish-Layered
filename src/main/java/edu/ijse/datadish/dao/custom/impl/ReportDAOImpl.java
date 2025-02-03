package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.ReportDAO;
import edu.ijse.datadish.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportDAOImpl implements ReportDAO {
    public Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT Date, SUM(TotalAmount) AS DailyIncome FROM orders GROUP BY Date", Map.class);
    }

    public Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT MONTH(Date) AS Month, SUM(TotalAmount) AS MonthlyProfit FROM orders GROUP BY MONTH(Date)", Map.class);
    }

    public double getTotalSalaries() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT SUM(Amount) AS TotalSalaries FROM salary", Double.class);
    }

    private String getMonthName(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Unknown";
        }
    }
//
//    @Override
//    public ArrayList getAll() throws SQLException, ClassNotFoundException {
//        return null;
//    }
//
//    @Override
//    public boolean save(Object dto) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public void update(Object dto) throws SQLException, ClassNotFoundException {
//
//    }
//
//    @Override
//    public boolean exist(String id) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public void delete(String id) throws SQLException, ClassNotFoundException {
//
//    }
//
//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return "";
//    }
//
//    @Override
//    public Object search(String id) throws SQLException, ClassNotFoundException {
//        return null;
//    }
}
