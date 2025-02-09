package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.CustomerDAO;
import edu.ijse.datadish.dto.CustomerDTO;
import edu.ijse.datadish.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        Customer customer = DTOConverter.toEntity(dto, Customer.class);
        return SQLUtil.execute("INSERT INTO customer (CustomerID, Name, Contact) VALUES (?,?,?)", customer.getCustomerID(), customer.getName(), customer.getContact());
    }

//    @Override
//    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("INSERT INTO customer (CustomerID, Name, Contact) VALUES (?,?,?)", dto.getCustomerID(), dto.getName(), dto.getContact());
//    }

    public boolean save(CustomerDTO customerDto) throws SQLException, ClassNotFoundException {
        Customer customer = DTOConverter.toEntity(customerDto, Customer.class);
        return SQLUtil.execute("INSERT INTO customer (CustomerID, Name, Contact) VALUES (?,?,?)", customer.getCustomerID(), customer.getName(), customer.getContact());
    }

    @Override
    public void update(Customer dto) throws SQLException, ClassNotFoundException {

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
        ResultSet rst = SQLUtil.execute("SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1");

        if (rst.next()) {
            String lastID = rst.getString("CustomerID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("C%03d", number + 1);
        } else {
            return "C001";
        }
    }


    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
