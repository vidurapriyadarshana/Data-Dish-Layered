package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.MenuDAO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Food;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Food> foodItems = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM menuitem");

        while (resultSet.next()) {
            Food foodItem = new Food(
                    resultSet.getString("MenuItemID"),
                    resultSet.getString("Name"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("Category"),
                    resultSet.getString("Availability"),
                    resultSet.getString("ImageData")
            );
            foodItems.add(foodItem);
        }

        resultSet.close();

        return foodItems;
    }

    @Override
    public boolean save(Food dto) throws SQLException, ClassNotFoundException {
        return false;
    }

//    public boolean save(OrderItem item, Order orderDto) throws SQLException, ClassNotFoundException {
//        String query = "INSERT INTO menuorderitem(MenuItemID, OrderID, Qty) VALUES (?,?,?)";
//        Object[] params = { item.getFoodId(), orderDto.getOrderId(), item.getQuantity() };
//
//        return SQLUtil.execute(query, params);
//    }

    public boolean save(OrderItemDto itemDto, OrderDto orderDto) throws SQLException, ClassNotFoundException {
        OrderItem orderItem = DTOConverter.toEntity(itemDto, OrderItem.class);
        Order order = DTOConverter.toEntity(orderDto, Order.class);
        return SQLUtil.execute("INSERT INTO menuorderitem(MenuItemID, OrderID, Qty) VALUES (?,?,?)", orderItem.getFoodId(), order.getOrderId(), orderItem.getQuantity());
    }

    @Override
    public boolean save(OrderItem item, Order orderDto) throws SQLException, ClassNotFoundException {

        OrderItem orderItem = DTOConverter.toEntity(item, OrderItem.class);
        Order order = DTOConverter.toEntity(orderDto, Order.class);
        return SQLUtil.execute("INSERT INTO menuorderitem(MenuItemID, OrderID, Qty) VALUES (?,?,?)", orderItem.getFoodId(), order.getOrderId(), orderItem.getQuantity());
    }

    @Override
    public boolean setStatus(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE menuorderItem SET status = 'completed' WHERE OrderID = ?", id);
    }

    @Override
    public void update(Food dto) throws SQLException, ClassNotFoundException {

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
        return null;
    }

    @Override
    public Food search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


}
