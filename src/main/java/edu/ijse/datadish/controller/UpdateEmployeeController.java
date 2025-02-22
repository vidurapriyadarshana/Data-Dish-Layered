package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.EmployeeViewBOImpl;
import edu.ijse.datadish.bo.custom.impl.UpdateEmployeeBOImpl;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dao.custom.impl.UpdateEmployeeDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static edu.ijse.datadish.util.Regex.*;

public class UpdateEmployeeController implements Initializable {

    @FXML
    private CheckBox actionStatus;

    @FXML
    private Button btUpdate;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtAddress;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField txtContact;

    private EmployeeDto employeeDto;

    //private UpdateEmployeeDAOImpl updateEmployeeDAOImpl = new UpdateEmployeeDAOImpl();
    private final EmployeeViewBOImpl employeeViewBOImpl = (EmployeeViewBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE_VIEW);
    private final UpdateEmployeeBOImpl updateEmployeeBO = (UpdateEmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_EMPLOYEE);

    public void setEmployeeData(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        this.employeeDto = employeeDto;
        lblEmployeeId.setText(employeeDto.getEmployeeID());
        System.out.println(employeeDto.getEmployeeName());

        lblName.setText(employeeDto.getEmployeeName());
        System.out.println(employeeDto.getAddress());

        txtAddress.setText(employeeDto.getAddress());
        System.out.println(employeeDto.getEmployeeContact());

        txtContact.setText(employeeDto.getEmployeeContact());
        System.out.println(employeeDto.getHireDate());

        lblDate.setText(employeeDto.getHireDate());
        System.out.println(employeeDto.getEmployeeStatus());

        actionStatus.setSelected("Active".equals(employeeDto.getEmployeeStatus()));


//        txtEmail.setText(updateEmployeeBO.getEmployeeEmail(employeeDto.getEmployeeID()));
        //System.out.println(updateEmployeeBO.getEmployeeEmail(employeeDto.getEmployeeID()));

        try {
            String email = updateEmployeeBO.getEmployeeEmail(employeeDto.getEmployeeID());
            System.out.println("Fetched email: " + email);
            txtEmail.setText(email);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to fetch email: " + e.getMessage());
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String status = actionStatus.isSelected() ? "Active" : "Inactive";
        String email = txtEmail.getText();

        if (!validateEmail(email)) {
            showAlert("Validation Error", "Invalid email address format.");
            return;
        }

        if (!validateContact(contact)) {
            showAlert("Validation Error", "Contact number must be a 10-digit numeric value.");
            return;
        }

        if (!validateAddress(address)) {
            showAlert("Validation Error", "Address must be alphanumeric and within 100 characters.");
            return;
        }

        employeeDto.setAddress(address);
        employeeDto.setEmployeeContact(contact);
        employeeDto.setEmployeeStatus(status);
        employeeDto.setEmail(email);

        updateEmployeeBO.update(employeeDto);

        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
