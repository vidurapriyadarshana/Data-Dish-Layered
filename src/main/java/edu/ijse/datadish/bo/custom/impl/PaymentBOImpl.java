package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.PaymentBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.PaymentDAOImpl;
import edu.ijse.datadish.dto.PaymentDto;
import edu.ijse.datadish.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException {
        Payment payment = DTOConverter.toEntity(dto, Payment.class);
        return paymentDAO.save(payment);
    }

    public void update(PaymentDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }

    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
