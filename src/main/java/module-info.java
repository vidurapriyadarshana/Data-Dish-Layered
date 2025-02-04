module edu.ijse.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires mysql.connector.j;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires jbcrypt;
    requires modelmapper;
    requires org.apache.poi.poi;


    opens edu.ijse.datadish.controller to javafx.fxml;
    exports edu.ijse.datadish;
}