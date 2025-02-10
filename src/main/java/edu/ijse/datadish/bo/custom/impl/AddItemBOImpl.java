package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddItemBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.AddItemDAOImpl;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddItemBOImpl implements AddItemBO {

    AddItemDAOImpl addItemDAO = (AddItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_ITEM);

    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(FoodDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(FoodDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        addItemDAO.delete(id);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<FoodDto> loadTable() {
        return addItemDAO.loadTable();
    }

    @Override
    public boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException {
        return addItemDAO.deleteFromTable(id);
    }

}
