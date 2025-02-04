package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.QuoryDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;
import edu.ijse.datadish.entity.OrderTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuoryDAOImpl implements QuoryDAO {

    public List<OrderTable> loadIncompleteOrders() throws Exception {
        List<OrderTable> orderList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT o.OrderID,o.TableID,o.TotalAmount,o.CustomerID,m.status FROM orders o JOIN menuorderitem m\n" +
                "ON o.OrderID = m.OrderID WHERE m.status = 'incomplete' GROUP BY o.OrderID ORDER BY o.OrderID;");

        while (resultSet.next()) {
            OrderTable order = new OrderTable();
            order.setOrderId(resultSet.getString("OrderID"));
            order.setTableId(resultSet.getString("TableID"));
            order.setTotalAmount(resultSet.getString("TotalAmount"));
            order.setEmployeeId(resultSet.getString("CustomerID"));
            order.setStatus(resultSet.getString("status"));

            orderList.add(order);
        }

        return orderList;
    }

    public Order getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT\n" +
                "    o.OrderID AS orderId,\n" +
                "    o.EmployeeID AS employeeId,\n" +
                "    o.TableID AS tableId,\n" +
                "    o.TotalAmount AS totalAmount,\n" +
                "    o.Date AS orderDate,\n" +
                "    c.CustomerID AS customerId,\n" +
                "    c.Name AS customerName,\n" +
                "    c.Contact AS customerContact\n" +
                "FROM\n" +
                "    orders o\n" +
                "JOIN\n" +
                "    customer c ON o.CustomerID = c.CustomerID\n" +
                "WHERE\n" +
                "    o.OrderID = ?" , orderId);

        Order order = null;

        if (resultSet.next()) {
            order = new Order(
                    resultSet.getString("orderId"),
                    resultSet.getString("employeeId"),
                    resultSet.getString("tableId"),
                    resultSet.getString("totalAmount"),
                    resultSet.getString("orderDate"),
                    resultSet.getString("customerId"),
                    resultSet.getString("customerName"),
                    resultSet.getString("customerContact")
            );
        }

        return order;
    }

    public List<OrderItem> getItemDetails(String orderId) throws SQLException, ClassNotFoundException {

        List<OrderItem> orderItemDto = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT\n" +
                "    moi.MenuItemID,\n" +
                "    SUM(moi.Qty) AS TotalQty,\n" +
                "    mi.Name,\n" +
                "    mi.Price\n" +
                "FROM\n" +
                "    menuorderitem moi\n" +
                "JOIN\n" +
                "    menuitem mi ON moi.MenuItemID = mi.MenuItemID\n" +
                "WHERE\n" +
                "    moi.OrderID = ?\n" +
                "GROUP BY\n" +
                "    moi.MenuItemID, mi.Name, mi.Price;", orderId);

        while (resultSet.next()) {
                String menuItemID = resultSet.getString("MenuItemID");
                String itemName = resultSet.getString("Name");
                int totalQty = resultSet.getInt("TotalQty");
                double price = Double.parseDouble(resultSet.getString("Price"));

                OrderItem orderItem = new OrderItem(menuItemID, itemName, totalQty, price);
                orderItemDto.add(orderItem);
        }

        return orderItemDto;
    }

}
