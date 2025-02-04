package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TableViewBO extends SuperBO {

    ArrayList<TableDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(TableDto dto) throws SQLException, ClassNotFoundException;
    ObservableList<TableDto> getAvailableTables() throws SQLException, ClassNotFoundException;
    void delete(String tableId) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    TableDto search(String id) throws SQLException, ClassNotFoundException;
    void update(TableDto tableDto) throws SQLException, ClassNotFoundException;
    boolean updateTrnsaction(TableDto tableDto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;

}
