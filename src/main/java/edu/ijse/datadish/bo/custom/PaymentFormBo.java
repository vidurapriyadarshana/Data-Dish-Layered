package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PaymentFormBo extends SuperBO {
    List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException;
    OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException;
    boolean completeOrder(OrderDto orderDto, PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
    String[] generateNextIDs() throws SQLException, ClassNotFoundException;
}
