package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.PaymentDto;
import edu.ijse.datadish.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException;
    void update(PaymentDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    PaymentDto search(String id) throws SQLException, ClassNotFoundException;

}
