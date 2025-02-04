package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.NotificationBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.NotificationDAOImpl;
import edu.ijse.datadish.dto.NotificationDto;
import edu.ijse.datadish.entity.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationBOImpl implements NotificationBO {

    NotificationDAOImpl notificationDAO = (NotificationDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.NOTIFICATION);

    public ArrayList<NotificationDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(NotificationDto dto) throws SQLException, ClassNotFoundException {
        Notification notification = DTOConverter.toEntity(dto, Notification.class);
        return notificationDAO.save(notification);
    }

    public void update(NotificationDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public NotificationDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
