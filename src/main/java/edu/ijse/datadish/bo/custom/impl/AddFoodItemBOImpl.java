package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddFoodItemBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.AddFoodItemDAOImpl;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddFoodItemBOImpl implements AddFoodItemBO {

    AddFoodItemDAOImpl addFoodItemDAOImpl = (AddFoodItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_FOOD_ITEM);

    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(FoodDto foodDto) throws SQLException, ClassNotFoundException {
        Food food = DTOConverter.toEntity(foodDto, Food.class);
        return addFoodItemDAOImpl.save(food);
    }

    public void update(FoodDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return addFoodItemDAOImpl.generateNewId();
    }

    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String saveImage(File sourceFile, String itemName) throws IOException {
        return addFoodItemDAOImpl.saveImage(sourceFile, itemName);
    }

}
