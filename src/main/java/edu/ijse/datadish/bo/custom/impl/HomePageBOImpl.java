package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.HomePageBO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;

import java.sql.*;
import java.util.List;

public class HomePageBOImpl implements HomePageBO {
    public boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto) throws SQLException, ClassNotFoundException {

        String customer = "INSERT INTO customer (CustomerID, Name, Contact) VALUES (?,?,?)";
        String orders = "INSERT INTO orders (OrderID, CustomerID, TableID, Date, TotalAmount, EmployeeID) VALUES (?,?,?,?,?,?)";
        String menuOrderItem = "INSERT INTO menuorderitem(MenuItemID, OrderID, Qty) VALUES (?,?,?)";
        String tableInfo = "UPDATE tableinfo SET Status = 'Reserved' WHERE TableID = ?";

        Connection connection = null;
        PreparedStatement customerStatement = null;
        PreparedStatement ordersStatement = null;
        PreparedStatement menuOrderItemStatement = null;
        PreparedStatement tableInfoStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            customerStatement = connection.prepareStatement(customer);
            customerStatement.setString(1, orderDto.getCustomerId());
            customerStatement.setString(2, orderDto.getCustomerName());
            customerStatement.setString(3, orderDto.getCustomerContact());
            int customerRowsInserted = customerStatement.executeUpdate();

            if (customerRowsInserted <= 0) {
                connection.rollback();
                return false;
            }

            ordersStatement = connection.prepareStatement(orders);
            ordersStatement.setString(1, orderDto.getOrderId());
            ordersStatement.setString(2, orderDto.getCustomerId());
            ordersStatement.setString(3, orderDto.getTableId());
            ordersStatement.setDate(4, Date.valueOf(orderDto.getOrderDate()));
            ordersStatement.setString(5, orderDto.getTotalAmount());
            ordersStatement.setString(6, orderDto.getEmployeeId());
            int orderRowsInserted = ordersStatement.executeUpdate();

            if (orderRowsInserted <= 0) {
                connection.rollback();
                return false;
            }

            menuOrderItemStatement = connection.prepareStatement(menuOrderItem);
            for (OrderItemDto item : orderItemsDto) {
                menuOrderItemStatement.setString(1, item.getFoodId());
                menuOrderItemStatement.setString(2, orderDto.getOrderId());
                menuOrderItemStatement.setInt(3, item.getQuantity());
                int itemRowsInserted = menuOrderItemStatement.executeUpdate();

                if (itemRowsInserted <= 0) {
                    connection.rollback();
                    return false;
                }
            }

            tableInfoStatement = connection.prepareStatement(tableInfo);
            tableInfoStatement.setString(1, orderDto.getTableId());
            int tableInfoRowsInserted = tableInfoStatement.executeUpdate();

            if (tableInfoRowsInserted <= 0) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (customerStatement != null) customerStatement.close();
            if (ordersStatement != null) ordersStatement.close();
            if (menuOrderItemStatement != null) menuOrderItemStatement.close();
            if (connection != null) connection.setAutoCommit(true);
        }
    }

    public String[] generateNewIdsAsTransaction() {
        String nextOrderID = "O001";
        String nextCustomerID = "C001";

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement orderStatement = connection.prepareStatement(
                    "SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
                 ResultSet orderResultSet = orderStatement.executeQuery()) {

                if (orderResultSet.next()) {
                    String lastOrderID = orderResultSet.getString("OrderID");
                    if (lastOrderID != null && !lastOrderID.isEmpty()) {
                        int number = Integer.parseInt(lastOrderID.substring(1));
                        nextOrderID = String.format("O%03d", number + 1);
                    }
                }
            }

            try (PreparedStatement customerStatement = connection.prepareStatement(
                    "SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1");
                 ResultSet customerResultSet = customerStatement.executeQuery()) {

                if (customerResultSet.next()) {
                    String lastCustomerID = customerResultSet.getString("CustomerID");
                    if (lastCustomerID != null && !lastCustomerID.isEmpty()) {
                        int number = Integer.parseInt(lastCustomerID.substring(1));
                        nextCustomerID = String.format("C%03d", number + 1);
                    }
                }
            }

            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException rollbackEx) {
                System.out.println("Rollback failed: " + rollbackEx.getMessage());
            }
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException resetEx) {
                System.out.println("Auto-commit reset failed: " + resetEx.getMessage());
            }
        }

        return new String[]{nextOrderID, nextCustomerID};
    }
}
