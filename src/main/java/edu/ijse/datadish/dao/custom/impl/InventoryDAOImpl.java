package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.custom.InventoryDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.InventoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAOImpl implements InventoryDAO {
    public ObservableList<InventoryDto> getAllInventoryItems() throws SQLException, ClassNotFoundException {
        ObservableList<InventoryDto> itemView = FXCollections.observableArrayList();

        String sql = "SELECT * FROM inventory";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString("InventoryID");
            String name = resultSet.getString("ItemName");
            int qty = resultSet.getInt("Qty");
            int stockLevel = resultSet.getInt("StockLevel");

            InventoryDto inventoryDto = new InventoryDto(id, name, qty, stockLevel);
            itemView.add(inventoryDto);
        }
        return itemView;
    }
    public boolean removeItem(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM inventory WHERE inventoryID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }
}
