package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface AddFoodItemDAO {

    boolean addItem(FoodDto foodDto) throws SQLException, ClassNotFoundException;
    String generateNextID();
    String saveImage(File sourceFile, String itemName) throws IOException;

}
