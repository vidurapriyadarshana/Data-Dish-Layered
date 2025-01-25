package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.InventoryDto;

import java.sql.SQLException;

public interface AddInventoryItemDAO {
    boolean addItem(InventoryDto inventoryDto) throws SQLException, ClassNotFoundException;
    String generateNextID();
}
