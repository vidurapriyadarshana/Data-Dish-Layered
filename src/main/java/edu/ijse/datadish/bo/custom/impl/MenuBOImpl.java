package edu.ijse.datadish.bo.custom.impl;

import edu.ijse.datadish.bo.DTOConverter;
import edu.ijse.datadish.bo.custom.MenuBO;
import edu.ijse.datadish.dao.DAOFactory;
import edu.ijse.datadish.dao.SQLUtil;
import edu.ijse.datadish.dao.custom.impl.MenuDAOImpl;
import edu.ijse.datadish.dto.FoodDto;
import edu.ijse.datadish.dto.OrderDto;
import edu.ijse.datadish.dto.OrderItemDto;
import edu.ijse.datadish.entity.Food;
import edu.ijse.datadish.entity.Order;
import edu.ijse.datadish.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuBOImpl implements MenuBO {

    MenuDAOImpl menuDAO = (MenuDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MENU);

    public ArrayList<FoodDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Food> foodList = menuDAO.getAll();
        ArrayList<FoodDto> foodDtoList = new ArrayList<>();

        for (Food food : foodList) {
            foodDtoList.add(DTOConverter.toDTO(food, FoodDto.class));
        }

        return foodDtoList;
    }

    public boolean save(FoodDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(OrderItemDto item, OrderDto orderDto) throws SQLException, ClassNotFoundException {
        OrderItem orderItem = DTOConverter.toEntity(item, OrderItem.class);
        Order order = DTOConverter.toEntity(orderDto, Order.class);
        return menuDAO.save(orderItem, order);
    }

    public boolean setStatus(String id) throws SQLException, ClassNotFoundException {
        return menuDAO.setStatus(id);
    }

    public void update(FoodDto dto) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    public FoodDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
