package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dto.TableDto;
import javafx.collections.ObservableList;

public interface TableViewDAO {
    ObservableList<TableDto> getAllTables();

    ObservableList<TableDto> getAvailableTables();

    boolean deleteTable(String tableId);

    boolean updateTableStatus(String tableId, String status);
}
