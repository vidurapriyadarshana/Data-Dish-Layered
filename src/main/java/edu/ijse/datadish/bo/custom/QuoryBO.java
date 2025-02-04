package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.OrderTableDto;

import java.sql.SQLException;
import java.util.List;

public interface QuoryBO extends SuperBO {

    List<OrderTableDto> loadIncompleteOrders() throws Exception;
    OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException;
    List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException;

}
