package edu.ijse.datadish.controller;

import edu.ijse.datadish.dto.*;
import edu.ijse.datadish.dao.custom.impl.HomePageDAOImpl;
import edu.ijse.datadish.dao.custom.impl.TableViewDAOImpl;
import edu.ijse.datadish.util.Refarance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane itemAnchor, mainAnchor, menuAnchor;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane foodContainer;

    @FXML
    private VBox cartContainer;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button checkoutButton;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblEmpId;

    @FXML
    private ChoiceBox<String> selectTable;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerName;

    public String empId;
    private String customerId;

    private Map<FoodDto, Integer> cartItems = new HashMap<>();
    private double totalPrice = 0.0;


    private void loadMenuItems() {
        List<FoodDto> foodItems = HomePageDAOImpl.getAllMenuItems();
        int row = 0;
        int col = 0;

        try {
            for (FoodDto foodItem : foodItems) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Food.fxml"));
                AnchorPane foodPane = loader.load();

                FoodController controller = loader.getController();
                controller.setData(foodItem);
                controller.setAddToCartAction(() -> addToCart(foodItem));

                foodContainer.add(foodPane, col, row);

                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToCart(FoodDto foodItem) {
        cartItems.put(foodItem, cartItems.getOrDefault(foodItem, 0) + 1);
        updateCartDisplay();
    }

    private void updateCartDisplay() {
        cartContainer.getChildren().clear();
        totalPrice = 0.0;

        for (Map.Entry<FoodDto, Integer> entry : cartItems.entrySet()) {
            FoodDto food = entry.getKey();
            int qty = entry.getValue();
            double itemTotal = food.getFoodPrice() * qty;
            totalPrice += itemTotal;

            HBox cartItem = new HBox(10);
            cartItem.setStyle("-fx-padding: 5; -fx-border-color: #FF971D; -fx-border-width: 1;");

            Label nameLabel = new Label(food.getFoodName());
            nameLabel.setPrefWidth(60);
            Label qtyLabel = new Label("Qty: " + qty);
            qtyLabel.setPrefWidth(40);
            Label priceLabel = new Label("LKR" + String.format("%.2f", itemTotal));
            priceLabel.setPrefWidth(70);

            Button removeButton = new Button("-");
            removeButton.setOnAction(e -> removeFromCart(food));
            removeButton.setPrefWidth(30);

            HBox buttonContainer = new HBox(5, removeButton);
            buttonContainer.setAlignment(Pos.CENTER_RIGHT);

            removeButton.setStyle("-fx-background-color: transparent; -fx-border-color: #F95454; -fx-text-fill: black;");

            cartItem.getChildren().addAll(nameLabel, qtyLabel, priceLabel, buttonContainer);
            cartContainer.getChildren().add(cartItem);
        }

        totalPriceLabel.setText("Total: LKR" + String.format("%.2f", totalPrice));
    }

    private void removeFromCart(FoodDto foodItem) {
        int currentQty = cartItems.getOrDefault(foodItem, 0);
        if (currentQty > 1) {
            cartItems.put(foodItem, currentQty - 1);
        } else {
            cartItems.remove(foodItem);
        }
        updateCartDisplay();
    }

    @FXML
    void checkoutAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String orderId = lblOrderId.getText();
        String empId = lblEmpId.getText();
        String selectedTable = selectTable.getValue();
        String date = getCurrentDate();

        String customerName = txtCustomerName.getText();
        String customerContact = txtCustomerContact.getText();

        if (customerName.isEmpty() || customerContact.isEmpty()) {
            showAlert("Error", "Please enter customer name and contact.");
            return;
        } else if (selectedTable == null) {
            showAlert("Error", "Please select a table.");
            return;
        } else if (cartItems.isEmpty()) {
            showAlert("Error", "Cart is empty.");
            return;
        }

        List<OrderItemDto> orderItems = cartItems.entrySet().stream()
                .map(entry -> {
                    FoodDto food = entry.getKey();
                    int qty = entry.getValue();
                    OrderItemDto item = new OrderItemDto();
                    item.setFoodId(food.getFoodId());
                    item.setFoodName(food.getFoodName());
                    item.setQuantity(qty);
                    item.setPrice(food.getFoodPrice());
                    return item;
                })
                .toList();

        OrderDto order = new OrderDto();
        order.setCustomerId(customerId);
        order.setOrderId(orderId);
        order.setEmployeeId(empId);
        order.setTableId(selectedTable);
        order.setOrderDate(date);
        order.setTotalAmount(String.valueOf(totalPrice));
        order.setCustomerName(customerName);
        order.setCustomerContact(customerContact);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customerId);
        customerDTO.setName(customerName);
        customerDTO.setContact(customerContact);

        TableDto table = new TableDto();
        table.setId(selectedTable);

        boolean isOrderSaved = HomePageDAOImpl.save(orderItems, order, customerDTO,table);
        if (isOrderSaved) {
            System.out.println("Order saved successfully!");
            resetForm();
            System.out.println("Failed to save the order.");
        }
    }

    private void resetForm() {
        cartItems.clear();
        updateCartDisplay();

        txtCustomerName.clear();
        txtCustomerContact.clear();

        selectTable.setValue(null);

        lblOrderId.setText(HomePageDAOImpl.generateNextOrderID());
        customerId = HomePageDAOImpl.generateNextCustomerID();

        totalPriceLabel.setText("Total: LKR0.00");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.empId = Refarance.employeeID;
        loadMenuItems();
        lblOrderId.setText(HomePageDAOImpl.generateNextOrderID());
        lblEmpId.setText(empId);
        customerId = HomePageDAOImpl.generateNextCustomerID();
        loadTableIds();
    }

    private void loadTableIds() {
        TableViewDAOImpl tableViewDAOImpl = new TableViewDAOImpl();
        ObservableList<TableDto> tableList = tableViewDAOImpl.getAvailableTables();

        ObservableList<String> tableIds = FXCollections.observableArrayList();
        for (TableDto table : tableList) {
            tableIds.add(table.getId());
        }

        selectTable.setItems(tableIds);
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}