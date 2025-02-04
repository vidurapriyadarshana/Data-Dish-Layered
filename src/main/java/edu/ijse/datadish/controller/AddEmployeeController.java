
package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.AddEmployeeBOImpl;
import edu.ijse.datadish.dao.custom.AddEmployeeDAO;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dao.custom.impl.AddEmployeeDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.ResourceBundle;

import static edu.ijse.datadish.util.Regex.*;

public class AddEmployeeController implements Initializable {

    @FXML
    private Tab btDetails;

    @FXML
    private Tab btLogInInfo;

    @FXML
    private Button btNextPage;

    @FXML
    private Button btSignUp;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblHireDate;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private ChoiceBox<String> choiceBox;

    private final EmployeeDto employeeDto = new EmployeeDto();
    private final AddEmployeeBOImpl addEmployeeBO = (AddEmployeeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_EMPLOYEE);
    private final String[] roleChoice = {"Admin","Employee"};

    @FXML
    void signUpOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        if (!validateName(name)) {
            showAlert("Error", "Name must contain only letters and spaces.");
            return;
        }

        if (!validateContact(contact)) {
            showAlert("Error", "Contact number must be a valid 10-digit number.");
            return;
        }

        if (!validateAddress(address)) {
            showAlert("Error", "Address cannot be empty and should not contain special characters.");
            return;
        }

        if (!validateEmail(email)) {
            showAlert("Error", "Please enter a valid email address.");
            return;
        }

        if (choiceBox.getValue() == null) {
            showAlert("Error", "Please select a role.");
            return;
        }

        employeeDto.setEmployeeID(lblEmpId.getText());
        employeeDto.setEmployeeName(name);
        employeeDto.setEmployeeContact(contact);
        employeeDto.setHireDate(lblHireDate.getText());
        employeeDto.setAddress(address);
        employeeDto.setEmail(email);
        employeeDto.setRole(choiceBox.getValue());
        employeeDto.setEmployeeStatus("Active");

        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        if (password.length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        employeeDto.setUserName(userName);
        employeeDto.setPassword(password);

        boolean isSaved = addEmployeeBO.save(employeeDto);

        if (isSaved) {
            showAlert("Success", "Employee saved successfully.");
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "Employee saving failed.");
        }
    }

    @FXML
    void navigateToSignUp(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            lblEmpId.setText(new AddEmployeeDAOImpl().generateNewId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lblHireDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        choiceBox.getItems().addAll(roleChoice);

        btNextPage.setOnAction(event -> {
            int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
            if (currentIndex < tabPane.getTabs().size() - 1) {
                tabPane.getSelectionModel().select(currentIndex + 1);
            }
        });

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
