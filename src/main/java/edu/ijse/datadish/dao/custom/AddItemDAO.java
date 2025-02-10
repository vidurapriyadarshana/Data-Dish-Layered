package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.entity.Food;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public interface AddItemDAO extends CrudDAO<Food> {
    ObservableList<FoodDto> loadTable();
    boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException;
}
