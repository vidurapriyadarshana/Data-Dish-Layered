package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.InventoryBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.InventoryDAOImpl;
import edu.ijse.datadish.dto.InventoryDto;
import edu.ijse.datadish.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAOImpl inventoryDAO = (InventoryDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);

    public ArrayList<InventoryDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> itemList = inventoryDAO.getAll();

        ArrayList<InventoryDto> itemDtoList = new ArrayList<>();

        for (Inventory inventory : itemList) {
            itemDtoList.add(new InventoryDto(inventory.getId(), inventory.getName(), inventory.getQty(), inventory.getStockLevel()));
        }
        return itemDtoList;
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
        inventoryDAO.delete(id);
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
