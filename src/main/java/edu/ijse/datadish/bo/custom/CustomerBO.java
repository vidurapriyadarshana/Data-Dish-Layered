package edu.ijse.datadish.bo.custom;

import edu.ijse.datadish.bo.SuperBO;
import edu.ijse.datadish.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    void update(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    CustomerDTO search(String id) throws SQLException, ClassNotFoundException;

}
