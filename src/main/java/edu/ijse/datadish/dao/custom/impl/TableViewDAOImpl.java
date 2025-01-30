package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.TableViewDAO;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableViewDAOImpl implements TableViewDAO {

    public ArrayList<TableDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tableinfo");
        ArrayList<TableDto> tableList = new ArrayList<>();

        while (resultSet.next()) {
            TableDto table = new TableDto(
                    resultSet.getString("TableID"),
                    resultSet.getString("Status"),
                    resultSet.getInt("Capacity")
            );
            tableList.add(table);
        }

        return tableList;
    }

    @Override
    public boolean save(TableDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    public ObservableList<TableDto> getAvailableTables() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tableinfo WHERE Status = 'Available'");
        ObservableList<TableDto> tableList = FXCollections.observableArrayList();
        return tableList;
    }

    public void delete(String tableId) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM tableinfo WHERE TableID = ?", tableId);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public TableDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public void update(TableDto tableDto) throws SQLException, ClassNotFoundException {

        SQLUtil.execute("UPDATE tableinfo SET Status = ? WHERE TableID = ?", tableDto.getStatus(), tableDto.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
