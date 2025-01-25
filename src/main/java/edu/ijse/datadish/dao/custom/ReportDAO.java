package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface ReportDAO {

    Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException;

    Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException;

    double getTotalSalaries() throws SQLException, ClassNotFoundException;

}
