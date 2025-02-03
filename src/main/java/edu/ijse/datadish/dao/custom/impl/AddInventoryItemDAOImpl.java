package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddInventoryItemDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.InventoryDto;
import edu.ijse.datadish.entity.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddInventoryItemDAOImpl implements AddInventoryItemDAO {

    @Override
    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Inventory inventoryDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO inventory (InventoryID, ItemName, Qty, StockLevel) VALUES (?,?,?,?);", inventoryDto.getId(), inventoryDto.getName(), inventoryDto.getQty(), inventoryDto.getStockLevel());
    }

    @Override
    public void update(Inventory dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
//        String nextID = null;
//
//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            String query = "SELECT InventoryID FROM inventory ORDER BY InventoryID DESC LIMIT 1";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                String lastID = resultSet.getString("InventoryID");
//                int number = Integer.parseInt(lastID.substring(1));
//                nextID = String.format("I%03d", number + 1);
//            } else {
//                nextID = "INV001";
//            }
//
//        } catch (SQLException e) {
//            System.out.println("SQL Exception: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return nextID;



        ResultSet resultSet = SQLUtil.execute("SELECT InventoryID FROM inventory ORDER BY InventoryID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastID = resultSet.getString("InventoryID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("I%03d", number + 1);
        } else {
            return "INV001";
        }

    }

    @Override
    public Inventory search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
