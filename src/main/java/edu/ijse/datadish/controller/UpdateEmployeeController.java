package edu.ijse.datadish.controller;

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

    private UpdateEmployeeDAOImpl updateEmployeeDAOImpl = new UpdateEmployeeDAOImpl();

    public void setEmployeeData(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        this.employeeDto = employeeDto;
        lblEmployeeId.setText(employeeDto.getEmployeeID());
        lblName.setText(employeeDto.getEmployeeName());
        txtAddress.setText(employeeDto.getAddress());
        txtContact.setText(employeeDto.getEmployeeContact());
        lblDate.setText(employeeDto.getHireDate());
        actionStatus.setSelected("Active".equals(employeeDto.getEmployeeStatus()));
        txtEmail.setText(updateEmployeeDAOImpl.getEmployeeEmail(employeeDto.getEmployeeID()));
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

        boolean result = updateEmployeeDAOImpl.updateEmployee(employeeDto);

        if (result) {
            showAlert("Success", "Employee Updated Successfully");
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "Employee Update Failed");
        }

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
