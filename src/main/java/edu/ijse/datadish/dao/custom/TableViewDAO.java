package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.TableDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TableViewDAO extends CrudDAO<TableDto> {
    ObservableList<TableDto> getAvailableTables() throws SQLException, ClassNotFoundException;
}
