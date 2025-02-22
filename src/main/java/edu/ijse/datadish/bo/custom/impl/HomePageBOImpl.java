package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.HomePageBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.CustomerDAOImpl;
import edu.ijse.datadish.dao.custom.impl.MenuDAOImpl;
import edu.ijse.datadish.dao.custom.impl.OrderDAOImpl;
import edu.ijse.datadish.dao.custom.impl.TableViewDAOImpl;
import edu.ijse.datadish.db.DBConnection;
import edu.ijse.datadish.dto.*;
import edu.ijse.datadish.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePageBOImpl implements HomePageBO {

    CustomerDAOImpl customerDAOImpl = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAOImpl orderDAOImpl = (OrderDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    MenuDAOImpl menuDAOIMPL = (MenuDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MENU);
    TableViewDAOImpl tableViewDAOImpl = (TableViewDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TABLE_VIEW);

//    public boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto, CustomerDTO customerDto, TableDto tableDto) throws SQLException, ClassNotFoundException {
//
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            Customer customer = DTOConverter.toEntity(customerDto, Customer.class);
//            Order order = DTOConverter.toEntity(orderDto, Order.class);
//            Table table = DTOConverter.toEntity(tableDto, Table.class);
//            table.setStatus("Reserved");
//
//            boolean customerAdded = customerDAOImpl.save(customer);
//            System.out.println("Customer Added: " + customerAdded);
//
//            if (!customerAdded) {
//                connection.rollback();
//                return false;
//            }
//
//            System.out.println(order.getOrderId());
//
//            boolean orderAdded = orderDAOImpl.save(order);
//
//            System.out.println("Order Added: " + orderAdded);
//
//            if (!orderAdded) {
//                connection.rollback();
//                return false;
//            }
//
////            Order orderEntity = DTOConverter.toEntity(orderDto, Order.class);
////            OrderItem orderItem = DTOConverter.toEntity(orderItemsDto, OrderItem.class);
//            Order orderEntity = new Order();
//            OrderItem orderItem = new OrderItem();
//
//
//            boolean menuAdded = menuDAOIMPL.save(orderItem, orderEntity);
//            System.out.println("Menu Added: " + menuAdded);
//
//            if (!menuAdded) {
//                connection.rollback();
//                return false;
//            }
//
//            boolean tableAdded = tableViewDAOImpl.updateTable(table);
//            System.out.println("Table Added: " + tableAdded);
//            if (!tableAdded) {
//                connection.rollback();
//                return false;
//            }
//
//            connection.commit();
//            return true;
//
//        } catch (SQLException e) {
//            if (connection != null) {
//                connection.rollback();
//            }
//            throw e;
//        } finally {
//            if (connection != null) {
//                connection.setAutoCommit(true);
//            }
//        }
//    }

public boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto, CustomerDTO customerDto, TableDto tableDto) throws SQLException, ClassNotFoundException {
    Connection connection = null;

    try {
        connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Customer customer = DTOConverter.toEntity(customerDto, Customer.class);
        boolean customerAdded = customerDAOImpl.save(customer);
        System.out.println("Customer Added: " + customerAdded);
        if (!customerAdded) {
            connection.rollback();
            return false;
        }

        Order order = DTOConverter.toEntity(orderDto, Order.class);
        boolean orderAdded = orderDAOImpl.save(order);
        System.out.println("Order Added: " + orderAdded);
        if (!orderAdded) {
            connection.rollback();
            return false;
        }

        for (OrderItemDto itemDto : orderItemsDto) {
            OrderItem orderItem = DTOConverter.toEntity(itemDto, OrderItem.class);
            boolean menuAdded = menuDAOIMPL.save(orderItem, order);
            System.out.println("Menu Added: " + menuAdded);
            if (!menuAdded) {
                connection.rollback();
                return false;
            }
        }

        Table table = DTOConverter.toEntity(tableDto, Table.class);
        table.setStatus("Reserved");
        boolean tableUpdated = tableViewDAOImpl.updateTable(table);
        System.out.println("Table Updated: " + tableUpdated);
        if (!tableUpdated) {
            connection.rollback();
            return false;
        }

        connection.commit();
        return true;

    } catch (SQLException e) {
        if (connection != null) {
            connection.rollback();
        }
        throw e;
    } finally {
        if (connection != null) {
            connection.setAutoCommit(true);
        }
    }
}

    public boolean save(List<OrderItemDto> orderItemsDto, OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Food> foodList = menuDAOIMPL.getAll();
        ArrayList<FoodDto> foodDtoList = new ArrayList<>();

        for (Food food : foodList) {
            foodDtoList.add(DTOConverter.toDTO(food, FoodDto.class));
        }

        return foodDtoList;
    }

    public boolean save(FoodDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void update(FoodDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
