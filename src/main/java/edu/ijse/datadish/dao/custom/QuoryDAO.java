package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.SuperDAO;
import edu.ijse.datadish.dto.OrderTableDto;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;
import edu.ijse.datadish.entity.OrderTable;

import java.sql.SQLException;
import java.util.List;

public interface QuoryDAO extends SuperDAO {
    List<OrderTable> loadIncompleteOrders() throws Exception;
    Order getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException;
    List<OrderItem> getItemDetails(String orderId) throws SQLException, ClassNotFoundException;
}