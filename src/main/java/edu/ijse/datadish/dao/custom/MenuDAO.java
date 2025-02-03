package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Food;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;

import java.sql.SQLException;

public interface MenuDAO extends CrudDAO<Food> {
    boolean save(OrderItem item, Order orderDto) throws SQLException, ClassNotFoundException;
    boolean setStatus(String id) throws SQLException, ClassNotFoundException;
}
