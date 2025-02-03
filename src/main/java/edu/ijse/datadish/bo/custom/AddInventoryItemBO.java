package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.InventoryDto;
import edu.ijse.datadish.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddInventoryItemBO extends SuperBO {

    ArrayList<InventoryDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(InventoryDto inventoryDto) throws SQLException, ClassNotFoundException;
    void update(InventoryDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    InventoryDto search(String id) throws SQLException, ClassNotFoundException;

}
