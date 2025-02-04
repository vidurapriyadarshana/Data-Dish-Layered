package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Food;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuBO extends SuperBO {

    ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean save(OrderItemDto item, OrderDto orderDto) throws SQLException, ClassNotFoundException;
    boolean setStatus(String id) throws SQLException, ClassNotFoundException;
    void update(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    FoodDto search(String id) throws SQLException, ClassNotFoundException;

}
