package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.NotificationDto;
import edu.ijse.datadish.entity.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationBO extends SuperBO {

    ArrayList<NotificationDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(NotificationDto dto) throws SQLException, ClassNotFoundException;
    void update(NotificationDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    NotificationDto search(String id) throws SQLException, ClassNotFoundException;

}
