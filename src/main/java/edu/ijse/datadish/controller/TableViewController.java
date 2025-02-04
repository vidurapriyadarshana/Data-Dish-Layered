package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.TableViewBOImpl;
import edu.ijse.datadish.dto.TableDto;
import edu.ijse.datadish.dao.custom.impl.TableViewDAOImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private Button btAddTable;

    @FXML
    private TableColumn<TableDto, Integer> colCapacity;

    @FXML
    private TableColumn<TableDto, String> colId;

    @FXML
    private TableColumn<TableDto, String> colStatus;

    @FXML
    private TableView<TableDto> loadTable;

    @FXML
    private TableColumn<TableDto, Void> colActions;

    //private final TableViewDAOImpl tableViewDAOImpl = new TableViewDAOImpl();
    private final TableViewBOImpl tableViewBO = (TableViewBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TABLE_VIEW);

    @FXML
    void addTableAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddTableView.fxml"));
        Parent load = loader.load();

        Stage addItemStage = new Stage();
        addItemStage.setTitle("Add Table");
        addItemStage.setScene(new Scene(load));
        addItemStage.initModality(Modality.NONE);
        addItemStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        colCapacity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());

        addActionsColumn();

        try {
            loadTableData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        List<TableDto> tableList = tableViewBO.getAll();
        ObservableList<TableDto> tables = FXCollections.observableArrayList(tableList);

        loadTable.setItems(tables);
    }


    private void addActionsColumn() {
        colActions.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final CheckBox statusCheckBox = new CheckBox();

            {
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                deleteButton.setOnAction(event -> {
                    TableDto tableDto = getTableView().getItems().get(getIndex());
                    try {
                        handleDeleteAction(tableDto);
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                statusCheckBox.setOnAction(event -> {
                    TableDto tableDto = getTableView().getItems().get(getIndex());
                    try {
                        handleStatusToggle(tableDto, statusCheckBox.isSelected());
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void unused, boolean empty) {
                super.updateItem(unused, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox actionBox = new HBox(10, statusCheckBox, deleteButton);
                    actionBox.setStyle("-fx-alignment: center;");

                    setGraphic(actionBox);

                    TableDto tableDto = getTableView().getItems().get(getIndex());
                    statusCheckBox.setSelected("Reserved".equals(tableDto.getStatus()));
                }
            }
        });
    }


    private void handleDeleteAction(TableDto tableDto) throws SQLException, ClassNotFoundException {
        tableViewBO.delete(tableDto.getId());
    }

    private void handleStatusToggle(TableDto tableDto, boolean isReserved) throws SQLException, ClassNotFoundException {
        String newStatus = isReserved ? "Reserved" : "Available";
        tableViewBO.update(new TableDto(tableDto.getId(), newStatus));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
