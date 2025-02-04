package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.QuoryBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.QuoryDAO;
import edu.ijse.datadish.dao.custom.impl.QuoryDAOImpl;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.OrderTableDto;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;
import edu.ijse.datadish.entity.OrderTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuoryBOImpl implements QuoryBO {

    QuoryDAOImpl quoryDAO = (QuoryDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    public List<OrderTableDto> loadIncompleteOrders() throws Exception {
        List<OrderTable> orderList = quoryDAO.loadIncompleteOrders();

        List<OrderTableDto> orderTableDtos = new ArrayList<>();
        for (OrderTable orderTable : orderList) {
            orderTableDtos.add(DTOConverter.toDTO(orderTable, OrderTableDto.class));
        }

        return orderTableDtos;
    }

    public OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException {
        Order order = quoryDAO.getCustomerDetails(orderId);
        return DTOConverter.toDTO(order, OrderDto.class);
    }

    public List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderItem> orderItem = quoryDAO.getItemDetails(orderId);

        List<OrderItemDto> orderItemDto = new ArrayList<>();
        for (OrderItem orderItem1 : orderItem) {
            orderItemDto.add(DTOConverter.toDTO(orderItem1, OrderItemDto.class));
        }

        return orderItemDto;
    }

}
