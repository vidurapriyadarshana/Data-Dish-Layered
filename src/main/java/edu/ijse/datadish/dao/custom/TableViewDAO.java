package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.CrudDAO;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TableViewDAO extends CrudDAO<Table> {
    ObservableList<Table> getAvailableTables() throws SQLException, ClassNotFoundException;
    boolean updateTrnsaction(Table tableDto) throws SQLException, ClassNotFoundException;
}
