package edu.ijse.datadish.dao;

import edu.ijse.datadish.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        ADD_EMPLOYEE,
        ADD_EMPLOYEE_SALARY,
        ADD_FOOD_ITEM,
        ADD_INVENTORY_ITEM,
        ADD_ITEM,
        ADD_TABLE,
        CHEACKOUT,
        EDIT_FOOD_ITEM,
        EMPLOYEE_VIEW,
        HOME_PAGE,
        INVENTORY,
        LOGIN,
        PAYMENT_FORM,
        REPORT,
        TABLE_VIEW,
        UPDATE_EMPLOYEE;

    }

    public SuperDAO getDAO(DAOTypes daoTypes){
//        switch (daoTypes){
//            case ADD_EMPLOYEE:
//                return new AddEmployeeDAOImpl();
//            case ADD_EMPLOYEE_SALARY:
//                return new AddEmployeeDAOImpl();
//            case ORDER:
//                return new OrderDAOImpl();
//            case ORDER_DETAIL:
//                return new OrderDetailsDAOImpl();
//            case QUERY:
//                return new QueryDAOImpl();
//            default:
//                return null;
//        }

        return null;
    }

}
