package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.InventoryDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface InventoryDAO {

    ObservableList<InventoryDto> getAllInventoryItems() throws SQLException, ClassNotFoundException;

    boolean removeItem(String id) throws SQLException, ClassNotFoundException;
}
