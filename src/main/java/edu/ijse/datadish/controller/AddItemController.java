package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.AddItemBOImpl;
import edu.ijse.datadish.dto.FoodDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    @FXML
    private AnchorPane addItemAnchor;

    @FXML
    private TableColumn<FoodDto, String> colCategory;

    @FXML
    private TableColumn<FoodDto, String> colDesc;

    @FXML
    private TableColumn<FoodDto, String> colId;

    @FXML
    private TableColumn<FoodDto, String> colName;

    @FXML
    private TableColumn<FoodDto, String> colPrice;

    @FXML
    private TableColumn<FoodDto, String> colStatus;

    @FXML
    private TableView<FoodDto> itemMenuTable;

    private final AddItemBOImpl addItemBO = (AddItemBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_ITEM);


    @FXML
    void addItemAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/AddFoodItem.fxml"));
        Stage addItemStage = new Stage();
        addItemStage.setTitle("Add Item");
        addItemStage.setScene(new Scene(load));
        addItemStage.initModality(Modality.NONE);
        addItemStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<FoodDto> foodItems = addItemBO.loadTable();
        itemMenuTable.setItems(foodItems);

        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodId()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodName()));
        colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFoodPrice())));
        colCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodCategory()));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodAvailability()));

        colDesc.setCellFactory(new Callback<>() {
            @Override
            public TableCell<FoodDto, String> call(TableColumn<FoodDto, String> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");

                    {
                        editButton.setId("btnEdit");
                        deleteButton.setId("btnDelete");

                        editButton.setStyle("-fx-background-color: #00FF9C; -fx-text-fill: white;");
                        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                        editButton.setOnAction(event -> {
                            FoodDto food = getTableView().getItems().get(getIndex());
                            try {
                                editFoodItem(food);
                            } catch (IOException | SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        deleteButton.setOnAction(event -> {
                            FoodDto food = getTableView().getItems().get(getIndex());
                            try {
                                deleteFoodItem(food);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });

                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hbox = new HBox(10, editButton, deleteButton);
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });
    }

    private void editFoodItem(FoodDto food) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditFoodItem.fxml"));
        Parent load = loader.load();
        EditFoodItemController controller = loader.getController();
        controller.setFoodDto(food);

        Stage addItemStage = new Stage();
        addItemStage.setTitle("Edit Item");
        addItemStage.setScene(new Scene(load));
        addItemStage.initModality(Modality.NONE);
        addItemStage.show();
        System.out.println("Editing item: " + food.getFoodName());
    }

    private void deleteFoodItem(FoodDto food) throws SQLException, ClassNotFoundException {
        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDelete.setTitle("Delete Confirmation");
        confirmDelete.setHeaderText("Are you sure you want to delete this item?");
        confirmDelete.setContentText("Item: " + food.getFoodName());

        Optional<ButtonType> result = confirmDelete.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleteResult = addItemBO.deleteFromTable(food.getFoodId());

            if (deleteResult) {
                showAlert("Delete Item", "Item Deleted Successfully");
                itemMenuTable.setItems(addItemBO.loadTable());
            } else {
                showAlert("Delete Item", "Item Not Deleted");
            }
            System.out.println("Deleting item: " + food.getFoodName());
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}