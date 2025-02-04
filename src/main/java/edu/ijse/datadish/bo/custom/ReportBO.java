package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;

import java.sql.SQLException;
import java.util.Map;

public interface ReportBO extends SuperBO {

    Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException;
    Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException;
    double getTotalSalaries() throws SQLException, ClassNotFoundException;
}
