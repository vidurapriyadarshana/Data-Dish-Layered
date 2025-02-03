package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface EditFoodItemDAO extends CrudDAO<Food> {
    String saveImage(File sourceFile, String itemName) throws IOException;
    String getFileExtension(String fileName);
    boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException;
    String getImagePath(String itemId) throws SQLException, ClassNotFoundException;
}
