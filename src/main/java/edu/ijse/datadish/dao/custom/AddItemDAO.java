package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.FoodDto;
import javafx.collections.ObservableList;


public interface AddItemDAO {
    ObservableList<FoodDto> loadTable();
    boolean delete(String id);
}
