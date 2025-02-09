package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.TableViewDAO;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableViewDAOImpl implements TableViewDAO {

    public ArrayList<Table> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tableinfo");
        ArrayList<Table> tableList = new ArrayList<>();

        while (resultSet.next()) {
            Table table = new Table(
                    resultSet.getString("TableID"),
                    resultSet.getString("Status"),
                    resultSet.getInt("Capacity")
            );
            tableList.add(table);
        }

        return tableList;
    }

    @Override
    public boolean save(Table dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    public ObservableList<Table> getAvailableTables() throws SQLException, ClassNotFoundException {
        ObservableList<Table> tableList = FXCollections.observableArrayList();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tableinfo WHERE Status = 'Available'");

        while (resultSet.next()) {
            tableList.add(new Table(
                    resultSet.getString("TableID"),
                    resultSet.getString("Status"),
                    resultSet.getInt("Capacity")
            ));
        }

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
    public Table search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean updateTable(TableDto tableDto) throws SQLException, ClassNotFoundException {
        Table table = DTOConverter.toEntity(tableDto, Table.class);
        return SQLUtil.execute("UPDATE tableinfo SET Status = ? WHERE TableID = ?", table.getStatus(), table.getId());
    }

    public void update(Table tableDto) throws SQLException, ClassNotFoundException {
        
    }


    public boolean updateTrnsaction(Table tableDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE tableinfo SET Status = ? WHERE TableID = ?", tableDto.getStatus(), tableDto.getId());
    }

    @Override
    public boolean updateTable(Table tableDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE tableinfo SET Status = ? WHERE TableID = ?", tableDto.getStatus(), tableDto.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


}
