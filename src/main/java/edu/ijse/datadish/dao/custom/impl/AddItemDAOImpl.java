package edu.ijse.datadish.dao.custom.impl;

import com.mysql.cj.protocol.Resultset;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddItemDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddItemDAOImpl implements AddItemDAO {

    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Food> foodItems = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT MenuItemID, Name, Price, Category, Availability FROM MenuItem");

        if (rst != null) {
            while (rst.next()) {
                String id = rst.getString("MenuItemID");
                String name = rst.getString("Name");
                double price = rst.getDouble("Price");
                String category = rst.getString("Category");
                String availability = rst.getString("Availability");

                Food foodDto = new Food(id, name, price, category, availability, null);
                foodItems.add(foodDto);
            }
        }

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

    public void delete(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("DELETE FROM MenuItem WHERE MenuItemID = ?", id);
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
