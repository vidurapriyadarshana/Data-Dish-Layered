package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;

import java.sql.SQLException;
import java.util.List;

public interface HomePageBO extends SuperBO {
    boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto) throws SQLException, ClassNotFoundException;
    String[] generateNewIdsAsTransaction();
}
