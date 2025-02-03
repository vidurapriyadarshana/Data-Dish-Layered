package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dao.SuperDAO;

import java.sql.SQLException;
import java.util.Map;

public interface ReportDAO extends SuperDAO {

    Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException;

    Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException;

    double getTotalSalaries() throws SQLException, ClassNotFoundException;

}
