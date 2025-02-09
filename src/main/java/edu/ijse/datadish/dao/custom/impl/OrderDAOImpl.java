package edu.ijse.datadish.dao.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.OrderDAO;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        Order order = DTOConverter.toEntity(dto, Order.class);
        return SQLUtil.execute("INSERT INTO orders (OrderID, CustomerID, TableID, Date, TotalAmount, EmployeeID) VALUES (?,?,?,?,?,?)",
                order.getOrderId(), order.getCustomerId(), order.getTableId(), Date.valueOf(order.getOrderDate()), order.getTotalAmount(), order.getEmployeeId());
    }

//    @Override
//    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("INSERT INTO orders (OrderID, CustomerID, TableID, Date, TotalAmount, EmployeeID) VALUES (?,?,?,?,?,?)" ,
//                dto.getOrderId(), dto.getCustomerId(), dto.getTableId(), dto.getOrderDate(), dto.getTotalAmount(), dto.getEmployeeId());
//    }

    public boolean save(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Order order = DTOConverter.toEntity(orderDto, Order.class);
        return SQLUtil.execute("INSERT INTO orders (OrderID, CustomerID, TableID, Date, TotalAmount, EmployeeID) VALUES (?,?,?,?,?,?)",
                order.getOrderId(), order.getCustomerId(), order.getTableId(), Date.valueOf(order.getOrderDate()), order.getTotalAmount(), order.getEmployeeId());
    }

    @Override
    public void update(Order dto) throws SQLException, ClassNotFoundException {

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
        ResultSet rst = SQLUtil.execute("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");

        if (rst.next()) {
            String lastID = rst.getString("OrderID");
            int number = Integer.parseInt(lastID.substring(1));
            return String.format("O%03d", number + 1);
        } else {
            return "O001";
        }
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
