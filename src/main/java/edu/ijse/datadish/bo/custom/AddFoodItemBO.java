package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AddFoodItemBO extends SuperBO {

    ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FoodDto foodDto) throws SQLException, ClassNotFoundException;
    void update(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    FoodDto search(String id) throws SQLException, ClassNotFoundException;
    String saveImage(File sourceFile, String itemName) throws IOException;

}
