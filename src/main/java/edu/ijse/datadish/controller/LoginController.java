package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.LogInBoImpl;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.util.Refarance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogIn;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtpassword;

    private final LogInBoImpl logInBo = (LogInBoImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void LogInAction(ActionEvent event) {
        String userName = txtUserName.getText().trim();
        String password = txtpassword.getText().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            showAlert("Login Failed", "Username or password cannot be empty.");
            return;
        }

        LogInDto logInDto = new LogInDto();
        logInDto.setUserName(userName);
        logInDto.setPassword(password);

        try {
            boolean isLoggedIn = logInBo.checkLogin(logInDto);
            if (isLoggedIn) {
                String role = logInBo.getRole(logInDto);
                String id = logInBo.getID(userName);

                Refarance.employeeUserName = userName;
                Refarance.employeeID = id;
                System.out.println(Refarance.employeeID);
                Refarance.employeeRole = role;

                loadDashboard(role);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred: " + e.getMessage());
        }
    }

    private void loadDashboard(String role) throws IOException {
        String fxmlPath = role.equals("Admin") ? "/view/AdminDash.fxml" : "/view/EmployeeDash.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void forgotPassAction(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ForgotPassword.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Forgot Password");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Unable to open Forgot Password window.");
        }
    }
}
