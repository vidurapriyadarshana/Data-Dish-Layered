package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface EditFoodItemDAO {

    String saveImage(File sourceFile, String itemName) throws IOException;

    boolean updateFoodItem(FoodDto foodDto) throws SQLException, ClassNotFoundException;

    boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException;

    String getImagePath(String itemId) throws SQLException, ClassNotFoundException;
}
