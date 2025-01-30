package edu.ijse.datadish.controller;

import edu.ijse.datadish.dao.custom.impl.LogInDAOImpl;
import edu.ijse.datadish.dto.LogInDto;
import edu.ijse.datadish.util.Refarance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    private String email;

    private LogInDAOImpl logInDAOImpl = new LogInDAOImpl();

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage) txtNewPassword.getScene().getWindow();
        stage.close();
    }

    @FXML
    void resetPassword(ActionEvent event) throws SQLException, ClassNotFoundException {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        logInDAOImpl.update(new LogInDto(email, newPassword));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.email = Refarance.email;
    }
}
