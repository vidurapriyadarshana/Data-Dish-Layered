package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.*;

public class EditFoodItemDAOImpl {

    private static final String PROFILE_IMAGES_DIR = "src/main/resources/assets/food/";
    private FoodDto foodDto = new FoodDto();

    static {
        try {
            Files.createDirectories(Paths.get(PROFILE_IMAGES_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String saveImage(File sourceFile, String itemName) throws IOException {
        String fileExtension = getFileExtension(sourceFile.getName());
        String uniqueFilename = itemName + "_" + System.currentTimeMillis() + fileExtension;
        Path destinationPath = Paths.get(PROFILE_IMAGES_DIR, uniqueFilename);
        Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        return destinationPath.toString();
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public boolean updateFoodItem(FoodDto foodDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE menuitem SET Name = ?, Price = ?, Category = ?, Availability = ? WHERE MenuItemID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, foodDto.getFoodName());
        statement.setDouble(2, foodDto.getFoodPrice());
        statement.setString(3, foodDto.getFoodCategory());
        statement.setString(4, foodDto.getFoodAvailability());
        statement.setString(5, foodDto.getFoodId());

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

    public boolean saveImagePath(String foodId, String imagePath) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE menuitem SET ImageData = ? WHERE MenuItemID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, imagePath);
        statement.setString(2, foodId);

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

    public String getImagePath(String itemId) throws SQLException, ClassNotFoundException {
        String query = "SELECT ImageData FROM menuitem WHERE MenuItemID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, itemId);

        ResultSet resultSet = preparedStatement.executeQuery();

        String imagePath = null;
        if (resultSet.next()) {
            imagePath = resultSet.getString("ImageData");
            foodDto.setFoodImagePath(imagePath);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return imagePath;
    }
}
