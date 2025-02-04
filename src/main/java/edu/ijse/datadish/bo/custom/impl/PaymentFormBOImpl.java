package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.PaymentFormBo;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.*;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.PaymentDto;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;
import edu.ijse.datadish.entity.Payment;
import edu.ijse.datadish.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormBOImpl implements PaymentFormBo {

    QuoryDAOImpl quoryDAO = (QuoryDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    TableViewDAOImpl tableViewDAO = (TableViewDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TABLE_VIEW);
    MenuDAOImpl menuDAO = (MenuDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MENU);
    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    PaymentFormDAOImpl paymentFormDAO = (PaymentFormDAOImpl)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT_FORM);

    public List<OrderItemDto> getItemDetails(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderItem> orderItem = quoryDAO.getItemDetails(orderId);

        List<OrderItemDto> orderItemDto = new ArrayList<>();
        for (OrderItem orderItem1 : orderItem) {
            orderItemDto.add(DTOConverter.toDTO(orderItem1, OrderItemDto.class));
        }

        return orderItemDto;
    }

    public OrderDto getCustomerDetails(String orderId) throws SQLException, ClassNotFoundException {
        Order order = quoryDAO.getCustomerDetails(orderId);
        return DTOConverter.toDTO(order, OrderDto.class);
    }

    public boolean completeOrder(OrderDto orderDto, PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Table tableDto = new Table();
        tableDto.setId(orderDto.getTableId());
        tableDto.setStatus("Available");

        boolean tableInfo = tableViewDAO.updateTrnsaction(tableDto);

        if(!tableInfo){
            connection.commit();
            return false;
        }

        boolean menuInfo = menuDAO.setStatus(orderDto.getOrderId());

        if(!menuInfo){
            connection.commit();
            return false;
        }

        Payment paymentEntity = DTOConverter.toEntity(paymentDto, Payment.class);
        boolean paymentInfo = paymentDAO.save(paymentEntity);

        if(!paymentInfo){
            connection.commit();
            return false;
        }

        connection.commit();
        return true;
    }

    public String[] generateNextIDs() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        if (connection == null) {
            System.out.println("Database connection failed.");
            return null;
        }

        connection.setAutoCommit(false);

        try {
            String nextPayID = paymentDAO.generateNewId();
            String nextNotificationID = paymentFormDAO.generateNewId();

            if (nextPayID == null || nextNotificationID == null) {
                System.out.println("Failed to generate IDs. Rolling back...");
                connection.rollback();
                return null;
            }

            connection.commit();
            return new String[]{nextPayID, nextNotificationID};

        } catch (SQLException e) {
            connection.rollback();
            throw e;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException {
        Payment payment = DTOConverter.toEntity(dto, Payment.class);
        return paymentDAO.save(payment);
    }

}
