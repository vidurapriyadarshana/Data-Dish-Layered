package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.InventoryDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.InventoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {

    public ArrayList<InventoryDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryDto> itemList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM inventory");

        while (resultSet.next()) {
            itemList.add(new InventoryDto(
                    resultSet.getString("InventoryID"),
                    resultSet.getString("ItemName"),
                    resultSet.getInt("Qty"),
                    resultSet.getInt("StockLevel")
            ));
        }
        return itemList;
    }

    @Override
    public boolean save(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(InventoryDto dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM inventory WHERE inventoryID = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public InventoryDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
