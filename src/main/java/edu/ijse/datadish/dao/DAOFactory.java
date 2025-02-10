package edu.ijse.datadish.dao;

import edu.ijse.datadish.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADD_EMPLOYEE,
        ADD_EMPLOYEE_SALARY,
        ADD_FOOD_ITEM,
        ADD_INVENTORY_ITEM,
        ADD_ITEM,
        ADD_TABLE,
//        CHECKOUT,
        EDIT_FOOD_ITEM,
        EMPLOYEE_VIEW,
        HOME_PAGE,
        INVENTORY,
        LOGIN,
        PAYMENT_FORM,
        QUERY,
        TABLE_VIEW,
        UPDATE_EMPLOYEE,
        CUSTOMER,
        ORDER,
        MENU,
        PAYMENT,
        NOTIFICATION;
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        return switch (daoTypes) {
            case ADD_EMPLOYEE -> new AddEmployeeDAOImpl();
            case ADD_EMPLOYEE_SALARY -> new AddEmployeeSalaryDAOImpl();
            case ADD_FOOD_ITEM -> new AddFoodItemDAOImpl();
            case ADD_INVENTORY_ITEM -> new AddInventoryItemDAOImpl();
            case ADD_ITEM -> new AddItemDAOImpl();
            case ADD_TABLE -> new AddTableDAOImpl();
//            case CHECKOUT:
//                return new CheckoutDAOImpl();
            case EDIT_FOOD_ITEM -> new EditFoodItemDAOImpl();
            case EMPLOYEE_VIEW -> new EmployeeViewDAOImpl();
            case HOME_PAGE -> new HomePageDAOImpl();
            case INVENTORY -> new InventoryDAOImpl();
            case LOGIN -> new LogInDAOImpl();
            case PAYMENT_FORM -> new PaymentFormDAOImpl();
            case QUERY -> new QuoryDAOImpl();
            case TABLE_VIEW -> new TableViewDAOImpl();
            case UPDATE_EMPLOYEE -> new UpdateEmployeeDAOImpl();
            case CUSTOMER -> new CustomerDAOImpl();
            case ORDER -> new OrderDAOImpl();
            case MENU -> new MenuDAOImpl();
            case PAYMENT -> new PaymentDAOImpl();
            case NOTIFICATION -> new NotificationDAOImpl();
            default -> null;
        };
    }
}
