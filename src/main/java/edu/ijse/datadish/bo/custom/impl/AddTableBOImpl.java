package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.AddTableBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.custom.impl.AddTableDAOImpl;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.entity.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddTableBOImpl implements AddTableBO {

    AddTableDAOImpl addTableDAO = (AddTableDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_TABLE);

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return addTableDAO.generateNewId();
    }

    public TableDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<TableDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(TableDto tableDto) throws SQLException, ClassNotFoundException {
        Table table = DTOConverter.toEntity(tableDto, Table.class);
        return addTableDAO.save(table);
    }

    public void update(TableDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

}
