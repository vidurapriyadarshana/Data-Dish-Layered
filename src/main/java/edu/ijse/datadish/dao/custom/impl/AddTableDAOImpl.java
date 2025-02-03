package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.AddTableDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddTableDAOImpl implements AddTableDAO {

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT TableID FROM tableinfo ORDER BY TableID DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString("TableID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("T%03d", number + 1);
        } else {
            return "T001";
        }

    }

    @Override
    public Table search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Table> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Table tableDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO tableinfo (TableID, Capacity , Status) VALUES (?, ? ,?)", tableDto.getId(), String.valueOf(tableDto.getCapacity()), "Available");
    }

    @Override
    public void update(Table dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }
}
