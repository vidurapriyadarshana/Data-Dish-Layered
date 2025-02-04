package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(OrderDto dto) throws SQLException, ClassNotFoundException;
    void update(OrderDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    OrderDto search(String id) throws SQLException, ClassNotFoundException;

}
