package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.PaymentFormBo;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.MenuDAOImpl;
import edu.ijse.datadish.dao.custom.impl.PaymentDAOImpl;
import edu.ijse.datadish.dao.custom.impl.QuoryDAOImpl;
import edu.ijse.datadish.dao.custom.impl.TableViewDAOImpl;
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
