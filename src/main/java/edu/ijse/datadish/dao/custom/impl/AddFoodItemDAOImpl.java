package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddFoodItemDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddFoodItemDAOImpl implements AddFoodItemDAO {

    private static final String IMAGES_DIR = "src/main/resources/assets/food/";

    static {
        try {
            Files.createDirectories(Paths.get(IMAGES_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Food foodDto) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO MenuItem (MenuItemID, Name, Price, Category, Availability, ImageData) VALUES (?,?,?,?,?,?)";
//
//        try (Connection connection = DBConnection.getInstance().getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1, foodDto.getFoodId());
//            statement.setString(2, foodDto.getFoodName());
//            statement.setDouble(3, foodDto.getFoodPrice());
//            statement.setString(4, foodDto.getFoodCategory());
//            statement.setString(5, foodDto.getFoodAvailability());
//            statement.setString(6, foodDto.getFoodImagePath());
//
//            int rowsAffected = statement.executeUpdate();
//            return rowsAffected > 0;
//        }

        return SQLUtil.execute("INSERT INTO MenuItem VALUES (?,?,?,?,?,?)", foodDto.getFoodId(), foodDto.getFoodName(), foodDto.getFoodPrice(), foodDto.getFoodCategory(), foodDto.getFoodAvailability(), foodDto.getFoodImagePath());
    }

    @Override
    public void update(Food dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT MenuItemID FROM MenuItem ORDER BY MenuItemID DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString("MenuItemID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("M%03d", number + 1);
        } else {
            return "M001";
        }
    }

    @Override
    public Food search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String saveImage(File sourceFile, String itemName) throws IOException {
        String fileExtension = getFileExtension(sourceFile.getName());
        String uniqueFilename = "_" + System.currentTimeMillis() + fileExtension;
        Path destinationPath = Paths.get(IMAGES_DIR, uniqueFilename);
        Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        return destinationPath.toString();
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


}
