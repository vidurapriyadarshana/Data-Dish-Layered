package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderTableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface CheackoutDAO {
    List<OrderTableDto> loadIncompleteOrders() throws Exception;
}
