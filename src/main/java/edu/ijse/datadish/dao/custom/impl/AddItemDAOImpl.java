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

    @Override
    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public ObservableList<FoodDto> loadTable() {
        ObservableList<FoodDto> foodItems = FXCollections.observableArrayList();

        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            String query = "SELECT MenuItemID, Name, Price, Category, Availability FROM MenuItem";
//            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = SQLUtil.execute("SELECT MenuItemID, Name, Price, Category, Availability FROM MenuItem");

            while (resultSet.next()) {
                String id = resultSet.getString("MenuItemID");
                String name = resultSet.getString("Name");
                double price = resultSet.getDouble("Price");
                String category = resultSet.getString("Category");
                String availability = resultSet.getString("Availability");

                FoodDto foodDto = new FoodDto(id, name, price, category, availability, null);
                foodItems.add(foodDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return foodItems;
    }

    public boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException {
//        String sql = "DELETE FROM menuitem WHERE MenuItemID = ?";
//        try (Connection connection = DBConnection.getInstance().getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1, id);
//            return statement.executeUpdate() > 0;
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }

        return SQLUtil.execute("DELETE FROM MenuItem WHERE MenuItemID = ?", id);
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
