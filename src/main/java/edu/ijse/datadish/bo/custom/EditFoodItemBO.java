package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EditFoodItemBO extends SuperBO {

    String saveImage(File sourceFile, String itemName) throws IOException;
    ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FoodDto dto) throws SQLException, ClassNotFoundException;
    void update(FoodDto foodDto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    FoodDto search(String id) throws SQLException, ClassNotFoundException;
    boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException;
    String getImagePath(String itemId) throws SQLException, ClassNotFoundException;
}
