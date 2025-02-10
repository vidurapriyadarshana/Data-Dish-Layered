package edu.ijse.datadish.controller;

import edu.ijse.datadish.bo.BOFactory;
import edu.ijse.datadish.bo.custom.impl.AddEmployeeSalaryBOImpl;
import edu.ijse.datadish.dto.EmployeeDto;
import edu.ijse.datadish.dto.SalaryDto;
import edu.ijse.datadish.dao.custom.impl.AddEmployeeSalaryDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmployeeSalaryController implements Initializable {

    @FXML
    private Button btnAddSalary;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblSalaryID;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private ChoiceBox<String> chooseEmployee;

    @Setter
    @Getter
    private SalaryDto salaryDto = new SalaryDto();
    private final AddEmployeeSalaryBOImpl addSalary = (AddEmployeeSalaryBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_EMPLOYEE_SALARY);

    @FXML
    void addSalaryAction(ActionEvent event) {
        try {
            String salaryID = lblSalaryID.getText();
            String employeeName = chooseEmployee.getValue();
            String paymentDate = lblDate.getText();
            String salaryText = txtSalary.getText();

            if (employeeName.isEmpty() || salaryText.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            double amount = Double.parseDouble(salaryText);
            String employeeId = addSalary.generateNewId(employeeName);

            if (employeeId == null) {
                showAlert("Error", "Employee not found. Please select a valid employee.");
                return;
            }

            SalaryDto salaryDto = new SalaryDto();
            salaryDto.setSalaryId(salaryID);
            salaryDto.setEmployeeId(employeeId);
            salaryDto.setDate(paymentDate);
            salaryDto.setAmount(amount);

            boolean isAdded = addSalary.save(salaryDto);

            if (isAdded) {
                showAlert("Add Salary", "Salary added successfully!");
                Stage stage = (Stage) mainAnchor.getScene().getWindow();
                stage.close();
            } else {
                showAlert("Add Salary", "Failed to add salary.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid salary amount. Please enter a valid number.");
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        }
    }


//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            lblSalaryID.setText(addSalary.generateNewId());
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
////        try {
////            List<EmployeeDto> employeeList = addSalary.getAll();
////            ObservableList<EmployeeDto> employees = FXCollections.observableArrayList(employeeList);
////
////            chooseEmployee.getItems().addAll(String.valueOf(employees));
////        } catch (SQLException | ClassNotFoundException e) {
////            throw new RuntimeException(e);
////        }
//
//        try {
//            List<EmployeeDto> employeeList = addSalary.getAll();
//            ObservableList<EmployeeDto> employees = FXCollections.observableArrayList(employeeList);
//
//            chooseEmployee.setItems(employees);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            showAlert("Database Error", "Failed to fetch employee data: " + e.getMessage());
//        }
//
//
//
//    }


@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
        lblSalaryID.setText(addSalary.generateNewId());
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

    lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    try {
//        List<EmployeeDto> employeeList = addSalary.getAll();
//
//        // Convert List<EmployeeDto> to List<String> using toString()
//        ObservableList<String> employees = FXCollections.observableArrayList(employeeList.toString());
//
//        chooseEmployee.setItems(employees);
        chooseEmployee.getItems().addAll(addSalary.getEmployeeNames());
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        showAlert("Database Error", "Failed to fetch employee data: " + e.getMessage());
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
