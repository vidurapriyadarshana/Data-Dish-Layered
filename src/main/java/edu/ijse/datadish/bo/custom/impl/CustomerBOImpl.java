package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.CustomerBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.CustomerDAOImpl;
import edu.ijse.datadish.dto.CustomerDTO;
import edu.ijse.datadish.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Customer customer = DTOConverter.toEntity(dto, Customer.class);
        return customerDAO.save(customer);
    }

    public void update(CustomerDTO dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
