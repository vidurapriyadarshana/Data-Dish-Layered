package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.custom.PaymentFormBo;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormBOImpl implements PaymentFormBo {
    public List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderItemDto> orderItemDto = new ArrayList<>();

        String sql = "SELECT\n" +
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
                "    moi.MenuItemID, mi.Name, mi.Price;";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, orderId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String menuItemID = resultSet.getString("MenuItemID");
                String itemName = resultSet.getString("Name");
                int totalQty = resultSet.getInt("TotalQty");
                double price = Double.parseDouble(resultSet.getString("Price"));

                OrderItemDto orderItem = new OrderItemDto(menuItemID, itemName, totalQty, price);
                orderItemDto.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orderItemDto;
    }

    public OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n" +
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
                "    o.OrderID = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, orderId);

        ResultSet resultSet = statement.executeQuery();

        OrderDto orderDto = null;

        if (resultSet.next()) {
            orderDto = new OrderDto(
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

        resultSet.close();
        statement.close();
        connection.close();

        return orderDto;
    }

    public boolean completeOrder(OrderDto orderDto, PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        String tableInfo = "UPDATE TableInfo SET Status = 'Available' WHERE TableID = ?";
        String menuOrderItem = "UPDATE menuorderItem SET status = 'completed' WHERE OrderID = ?";
        String payment = "INSERT INTO payment (PaymentID, OrderID, Amount, Date) VALUES (?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statementTableInfo = connection.prepareStatement(tableInfo);
        PreparedStatement statementMenuOrderItem = connection.prepareStatement(menuOrderItem);
        PreparedStatement statementPayment = connection.prepareStatement(payment);

        try {
            statementTableInfo.setString(1, orderDto.getTableId());

            statementMenuOrderItem.setString(1, orderDto.getOrderId());

            int rowsUpdatedTableInfo = statementTableInfo.executeUpdate();
            int rowsUpdatedMenuOrderItem = statementMenuOrderItem.executeUpdate();

            statementPayment.setString(1, paymentDto.getPayId());
            statementPayment.setString(2, paymentDto.getOrderId());
            statementPayment.setString(3, paymentDto.getAmount());
            statementPayment.setString(4, paymentDto.getDate());

            int rowsInsertedPayment = statementPayment.executeUpdate();

            if (rowsUpdatedTableInfo > 0 && rowsUpdatedMenuOrderItem > 0 && rowsInsertedPayment > 0) {
                connection.commit();
                System.out.println("Order completed successfully.");
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to complete the order. Rolling back.");
                return false;
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("An error occurred while completing the order. Rolling back.");
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
            statementTableInfo.close();
            statementMenuOrderItem.close();
            statementPayment.close();
            connection.close();
        }
    }

    public String[] generateNextIDs() throws SQLException, ClassNotFoundException {
        String nextPayID = null;
        String nextNotificationID = null;
        Connection connection = DBConnection.getInstance().getConnection();

        if (connection == null) {
            System.out.println("Database connection failed.");
            return null;
        }

        connection.setAutoCommit(false);

        try {
            System.out.println("Generating Payment ID...");
            String payQuery = "SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1";
            try (PreparedStatement payStatement = connection.prepareStatement(payQuery);
                 ResultSet payResultSet = payStatement.executeQuery()) {

                if (payResultSet.next()) {
                    String lastID = payResultSet.getString("PaymentID");
                    int number = Integer.parseInt(lastID.substring(1));
                    nextPayID = String.format("P%03d", number + 1);
                } else {
                    nextPayID = "P001";
                }
                System.out.println("New Payment ID: " + nextPayID);
            }

            System.out.println("Generating Notification ID...");
            String notificationQuery = "SELECT NotificationID FROM notification ORDER BY NotificationID DESC LIMIT 1";
            try (PreparedStatement notificationStatement = connection.prepareStatement(notificationQuery);
                 ResultSet notificationResultSet = notificationStatement.executeQuery()) {

                if (notificationResultSet.next()) {
                    String lastID = notificationResultSet.getString("NotificationID");
                    int number = Integer.parseInt(lastID.substring(1));
                    nextNotificationID = String.format("N%03d", number + 1);
                } else {
                    nextNotificationID = "N001";
                }
                System.out.println("New Notification ID: " + nextNotificationID);
            }

            connection.commit();
            return new String[]{nextPayID, nextNotificationID};

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("SQL Exception: " + e.getMessage());
            return null;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}
