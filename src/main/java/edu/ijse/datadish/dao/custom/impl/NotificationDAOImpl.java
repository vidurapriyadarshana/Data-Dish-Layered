package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.NotificationDAO;
import edu.ijse.datadish.entity.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAOImpl implements NotificationDAO {

    @Override
    public ArrayList<Notification> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Notification dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO notification (NotificationID, Description, Date , CustomerID) VALUES (?, ?, ?, ?)",
                dto.getNotificationId(), dto.getDesc(), dto.getDate(), dto.getCustomerId());
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
