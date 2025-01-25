package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;

import java.sql.*;
import java.util.List;

public interface HomePageDAO {
    List<FoodDto> getAllMenuItems();
    boolean saveOrder(List<OrderItemDto> orderItemsDto, OrderDto orderDto) throws SQLException, ClassNotFoundException;
    String generateNextOrderID();
    String generateNextCustomerID();
}
