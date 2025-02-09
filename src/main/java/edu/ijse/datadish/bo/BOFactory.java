package edu.ijse.datadish.bo;

import edu.ijse.datadish.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory =
                new BOFactory() : boFactory;

    }

    public enum BOTypes {
        ADD_EMPLOYEE,
        ADD_EMPLOYEE_SALARY,
        ADD_FOOD_ITEM,
        ADD_INVENTORY_ITEM,
        ADD_ITEM,
        ADD_TABLE,
        CUSTOMER,
        EDIT_FOOD_ITEM,
        EMPLOYEE_VIEW,
        HOME_PAGE,
        INVENTORY,
        LOGIN,
        MENU,
        ORDERS,
        PAYMENT,
        PAYMENT_FORM,
        QUARY,
        TABLE_VIEW,
        UPDATE_EMPLOYEE,
        NOTIFICATION
    }

    public SuperBO getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case ADD_EMPLOYEE -> new AddEmployeeBOImpl();
            case ADD_EMPLOYEE_SALARY -> new AddEmployeeSalaryBOImpl();
            case ADD_FOOD_ITEM -> new AddFoodItemBOImpl();
            case ADD_INVENTORY_ITEM -> new AddInventoryItemBOImpl();
            case ADD_ITEM -> new AddItemBOImpl();
            case ADD_TABLE -> new AddTableBOImpl();
            case CUSTOMER -> new CustomerBOImpl();
            case EDIT_FOOD_ITEM -> new EditFoodItemBOImpl();
            case EMPLOYEE_VIEW -> new EmployeeViewBOImpl();
            case HOME_PAGE -> new HomePageBOImpl();
            case INVENTORY -> new InventoryBOImpl();
            case LOGIN -> new LogInBoImpl();
            case MENU -> new MenuBOImpl();
            case ORDERS -> new OrderBOImpl();
            case PAYMENT -> new PaymentBOImpl();
            case PAYMENT_FORM -> new PaymentFormBOImpl();
            case QUARY -> new QuoryBOImpl();
            case TABLE_VIEW -> new TableViewBOImpl();
            case UPDATE_EMPLOYEE -> new UpdateEmployeeBOImpl();
            case NOTIFICATION -> new NotificationBOImpl();
        };
    }
}