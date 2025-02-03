package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.PaymentFormDAO;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.NotificationDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.dto.PaymentDto;
import edu.ijse.datadish.entity.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormDAOImpl implements PaymentFormDAO {

    @Override
    public ArrayList<Notification> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Notification notificationDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO notification (NotificationID, Description, Date , CustomerID) VALUES (?, ?, ?, ?)",
                notificationDto.getNotificationId(),
                notificationDto.getDesc(),
                notificationDto.getDate(),
                notificationDto.getCustomerId()
        );
    }

    @Override
    public void update(Notification dto) throws SQLException, ClassNotFoundException {

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Notification search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }



}
