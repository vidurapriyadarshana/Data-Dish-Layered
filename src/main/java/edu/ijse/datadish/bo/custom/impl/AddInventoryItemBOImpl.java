package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddInventoryItemBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.AddInventoryItemDAOImpl;
import edu.ijse.datadish.dto.InventoryDto;
import edu.ijse.datadish.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddInventoryItemBOImpl implements AddInventoryItemBO {

    AddInventoryItemDAOImpl addInventoryItemDAO = (AddInventoryItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_INVENTORY_ITEM);

    public ArrayList<InventoryDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(InventoryDto inventoryDto) throws SQLException, ClassNotFoundException {
        Inventory inventory = DTOConverter.toEntity(inventoryDto, Inventory.class);
        return addInventoryItemDAO.save(inventory);
    }

    public void update(InventoryDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return addInventoryItemDAO.generateNewId();
    }

    public InventoryDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
