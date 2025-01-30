package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import java.sql.SQLException;
import java.util.Map;

public interface ReportDAO extends CrudDAO {

    Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException;

    Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException;

    double getTotalSalaries() throws SQLException, ClassNotFoundException;

}
