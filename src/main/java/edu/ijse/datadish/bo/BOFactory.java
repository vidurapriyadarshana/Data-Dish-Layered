package edu.ijse.datadish.bo;

import edu.ijse.datadish.bo.custom.impl.AddEmployeeBOImpl;
import edu.ijse.datadish.bo.custom.impl.HomePageBOImpl;
import edu.ijse.datadish.bo.custom.impl.LogInBoImpl;
import edu.ijse.datadish.bo.custom.impl.PaymentFormBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory =
                new BOFactory() : boFactory;

    }

    public enum BOTypes {
        ADD_EMPLOYEE, HOME_PAGE, LOGIN,PAYMENT_FORM
    }

    public SuperBO getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case ADD_EMPLOYEE -> new AddEmployeeBOImpl();
            case HOME_PAGE -> new HomePageBOImpl();
            case LOGIN -> new LogInBoImpl();
            case PAYMENT_FORM -> new PaymentFormBOImpl();
        };
    }
}