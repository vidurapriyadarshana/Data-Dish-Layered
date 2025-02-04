package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.ReportBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.ReportDAOImpl;

import java.sql.SQLException;
import java.util.Map;

public class ReportBOImpl implements ReportBO {

    ReportDAOImpl reportDAO = (ReportDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.REPORT);

    public Map<String, Double> getDailyIncomeData() throws SQLException, ClassNotFoundException {
        return reportDAO.getDailyIncomeData();
    }

    public Map<String, Double> getMonthlyProfitData() throws SQLException, ClassNotFoundException {
        return reportDAO.getMonthlyProfitData();
    }

    public double getTotalSalaries() throws SQLException, ClassNotFoundException {
        return reportDAO.getTotalSalaries();
    }
}
