package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.EditFoodItemBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.EditFoodItemDAOImpl;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditFoodItemBOImpl implements EditFoodItemBO {

    EditFoodItemDAOImpl editFoodItemDAO = (EditFoodItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EDIT_FOOD_ITEM);

    public String saveImage(File sourceFile, String itemName) throws IOException {
        return editFoodItemDAO.saveImage(sourceFile, itemName);
    }

    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(FoodDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(FoodDto foodDto) throws SQLException, ClassNotFoundException {
        editFoodItemDAO.update(DTOConverter.toEntity(foodDto, Food.class));
    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException {
        return editFoodItemDAO.saveImagePath(foodId, imagePath);
    }

    public String getImagePath(String itemId) throws SQLException, ClassNotFoundException {
        return editFoodItemDAO.getImagePath(itemId);
    }
}
