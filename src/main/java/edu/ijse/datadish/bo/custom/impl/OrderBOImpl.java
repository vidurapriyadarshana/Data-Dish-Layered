package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.OrderBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.OrderDAOImpl;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAOImpl orderDAO = (OrderDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        Order order = DTOConverter.toEntity(dto, Order.class);
        return orderDAO.save(order);
    }

    public void update(OrderDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
