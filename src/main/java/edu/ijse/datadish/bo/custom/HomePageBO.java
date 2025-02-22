package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.*;
import edu.ijse.datadish.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HomePageBO extends SuperBO {

    boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto, CustomerDTO customerDto, TableDto tableDto) throws SQLException, ClassNotFoundException;
    boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto) throws SQLException, ClassNotFoundException;
    ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FoodDto dto) throws SQLException, ClassNotFoundException;
    void update(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    FoodDto search(String id) throws SQLException, ClassNotFoundException;

}
