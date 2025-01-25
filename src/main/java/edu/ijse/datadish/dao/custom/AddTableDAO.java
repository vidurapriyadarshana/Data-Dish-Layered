package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AddTableDAO {
    String generateNextID();
    boolean addNewTable(String id, String capacity) throws SQLException, ClassNotFoundException;
}
