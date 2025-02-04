package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.TableViewBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.TableViewDAOImpl;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.formula.functions.T;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableViewBOImpl implements TableViewBO {

    TableViewDAOImpl tableViewDAO = (TableViewDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TABLE_VIEW);

    public ArrayList<TableDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Table> table = tableViewDAO.getAll();
        ArrayList<TableDto> tableList = new ArrayList<>();
        for (Table table1 : table) {
            tableList.add(new TableDto(table1.getId(), table1.getStatus(), table1.getCapacity()));
        }
        return tableList;
    }

    public boolean save(TableDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ObservableList<TableDto> getAvailableTables() throws SQLException, ClassNotFoundException {
        ObservableList<TableDto> tableList = FXCollections.observableArrayList();

        List<Table> tables = tableViewDAO.getAvailableTables();

        if (tables != null) {
            for (Table table : tables) {
                tableList.add(new TableDto(
                        table.getId(),
                        table.getStatus(),
                        table.getCapacity()
                ));
            }
        }

        return tableList;
    }

    public void delete(String tableId) throws SQLException, ClassNotFoundException {
        tableViewDAO.delete(tableId);
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public TableDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public void update(TableDto tableDto) throws SQLException, ClassNotFoundException {
        Table table = DTOConverter.toEntity(tableDto, Table.class);
        tableViewDAO.update(table);
    }

    public boolean updateTrnsaction(TableDto tableDto) throws SQLException, ClassNotFoundException {
        Table table = DTOConverter.toEntity(tableDto, Table.class);
        return tableViewDAO.updateTrnsaction(table);
    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

}
