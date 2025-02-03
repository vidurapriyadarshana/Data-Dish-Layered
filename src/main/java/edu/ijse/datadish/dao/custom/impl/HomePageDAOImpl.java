package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.HomePageDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePageDAOImpl implements HomePageDAO {

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
        return "";
    }

    @Override
    public Food search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
