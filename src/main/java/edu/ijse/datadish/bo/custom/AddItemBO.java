package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddItemBO extends SuperBO {

    ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FoodDto dto) throws SQLException, ClassNotFoundException;
    void update(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    FoodDto search(String id) throws SQLException, ClassNotFoundException;
    ObservableList<FoodDto> loadTable();
    boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException;
}
