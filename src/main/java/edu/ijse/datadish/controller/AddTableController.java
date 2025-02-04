package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.AddTableBOImpl;
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

    private final AddTableBOImpl addNewTableBo = (AddTableBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_TABLE);

    @FXML
    void addNewTableAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean result = addNewTableBo.save(new TableDto(setTableId.getText(), Integer.parseInt(txtCapacity.getText())));

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
        try {
            setTableId.setText(addNewTableBo.generateNewId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
