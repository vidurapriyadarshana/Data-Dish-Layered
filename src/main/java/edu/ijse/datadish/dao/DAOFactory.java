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
        REPORT,
        TABLE_VIEW,
        UPDATE_EMPLOYEE,
        CUSTOMER,
        ORDER,
        MENU,
        PAYMENT,
        NOTIFICATION;
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ADD_EMPLOYEE:
                return new AddEmployeeDAOImpl();
            case ADD_EMPLOYEE_SALARY:
                return new AddEmployeeSalaryDAOImpl();
            case ADD_FOOD_ITEM:
                return new AddFoodItemDAOImpl();
            case ADD_INVENTORY_ITEM:
                return new AddInventoryItemDAOImpl();
            case ADD_ITEM:
                return new AddItemDAOImpl();
            case ADD_TABLE:
                return new AddTableDAOImpl();
//            case CHECKOUT:
//                return new CheckoutDAOImpl();
            case EDIT_FOOD_ITEM:
                return new EditFoodItemDAOImpl();
            case EMPLOYEE_VIEW:
                return new EmployeeViewDAOImpl();
            case HOME_PAGE:
                return new HomePageDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case LOGIN:
                return new LogInDAOImpl();
            case PAYMENT_FORM:
                return new PaymentFormDAOImpl();
            case QUERY:
                return new QuoryDAOImpl();
            case REPORT:
                return new ReportDAOImpl();
            case TABLE_VIEW:
                return new TableViewDAOImpl();
            case UPDATE_EMPLOYEE:
                return new UpdateEmployeeDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case MENU:
                return new MenuDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case NOTIFICATION:
                return new NotificationDAOImpl();
            default:
                return null;
        }
    }

}
