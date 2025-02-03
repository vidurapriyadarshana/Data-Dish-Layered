package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddTableBO extends SuperBO {

    String generateNewId() throws SQLException, ClassNotFoundException;
    TableDto search(String id) throws SQLException, ClassNotFoundException;
    ArrayList<TableDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(TableDto tableDto) throws SQLException, ClassNotFoundException;
    void update(TableDto dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;

}
