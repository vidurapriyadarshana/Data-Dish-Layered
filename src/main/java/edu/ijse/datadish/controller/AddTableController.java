package edu.ijse.datadish.controller;

import edu.ijse.datadish.dao.custom.impl.AddTableDAOImpl;
import edu.ijse.datadish.dto.TableDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTableController implements Initializable {

    @FXML
    private Button btAddNewTable;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Label setTableId;

    @FXML
    private TextField txtCapacity;

    @FXML
    void addNewTableAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean result = AddTableDAOImpl.addNewTable(new TableDto(setTableId.getText(), Integer.parseInt(txtCapacity.getText())));

        if(result) {
            showAlert("Add Table", "Table Added Successfully");
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Add Table", "Table Addition Failed");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableId.setText(AddTableDAOImpl.generateNextID());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
