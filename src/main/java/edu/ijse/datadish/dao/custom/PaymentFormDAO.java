package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.NotificationDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentFormDAO {
    List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException;

    OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException;

    boolean completeOrder(OrderDto orderDto, PaymentDto paymentDto) throws SQLException, ClassNotFoundException;

    String generateNextPayID();

    String generateNotificationId();

    boolean saveNotification(NotificationDto notificationDto) throws SQLException, ClassNotFoundException;
}
