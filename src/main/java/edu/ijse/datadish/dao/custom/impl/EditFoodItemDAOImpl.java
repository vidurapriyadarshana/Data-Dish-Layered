package edu.ijse.datadish.dao.custom.impl;

import com.mysql.cj.protocol.Resultset;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.EditFoodItemDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.*;
import java.util.ArrayList;

public class EditFoodItemDAOImpl implements EditFoodItemDAO {

    private static final String PROFILE_IMAGES_DIR = "src/main/resources/assets/food/";
    private FoodDto foodDto = new FoodDto();

    static {
        try {
            Files.createDirectories(Paths.get(PROFILE_IMAGES_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String saveImage(File sourceFile, String itemName) throws IOException {
        String fileExtension = getFileExtension(sourceFile.getName());
        String uniqueFilename = itemName + "_" + System.currentTimeMillis() + fileExtension;
        Path destinationPath = Paths.get(PROFILE_IMAGES_DIR, uniqueFilename);
        Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        return destinationPath.toString();
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(FoodDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(FoodDto foodDto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE menuitem SET Name = ?, Price = ?, Category = ?, Availability = ? WHERE MenuItemID = ?",
                foodDto.getFoodName(), foodDto.getFoodPrice(), foodDto.getFoodCategory(), foodDto.getFoodAvailability(), foodDto.getFoodId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE menuitem SET ImageData = ? WHERE MenuItemID = ?", imagePath, foodId);
    }

    public String getImagePath(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT ImageData FROM menuitem WHERE MenuItemID = ?", itemId);

        if(resultSet.next()){
            return resultSet.getString("ImageData");
        }
        return null;
    }
}
