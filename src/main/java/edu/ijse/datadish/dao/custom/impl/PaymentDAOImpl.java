package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.PaymentDAO;
import edu.ijse.datadish.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment (PaymentID, OrderID, Amount, Date) VALUES (?,?,?,?)", dto.getPayId(), dto.getOrderId(), dto.getAmount(), dto.getDate());
    }

    @Override
    public void update(Payment dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString("PaymentID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("P%03d", number + 1);
        } else {
            return "P001";
        }
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
