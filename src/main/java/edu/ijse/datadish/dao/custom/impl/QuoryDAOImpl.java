package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.QuoryDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderTableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuoryDAOImpl implements QuoryDAO {
    public List<OrderTableDto> loadIncompleteOrders() throws Exception {
        List<OrderTableDto> orderList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT o.OrderID,o.TableID,o.TotalAmount,o.CustomerID,m.status FROM orders o JOIN menuorderitem m\n" +
                "ON o.OrderID = m.OrderID WHERE m.status = 'incomplete' GROUP BY o.OrderID ORDER BY o.OrderID;");

        while (resultSet.next()) {
            OrderTableDto order = new OrderTableDto();
            order.setOrderId(resultSet.getString("OrderID"));
            order.setTableId(resultSet.getString("TableID"));
            order.setTotalAmount(resultSet.getString("TotalAmount"));
            order.setEmployeeId(resultSet.getString("CustomerID"));
            order.setStatus(resultSet.getString("status"));

            orderList.add(order);
        }

        return orderList;
    }
}
