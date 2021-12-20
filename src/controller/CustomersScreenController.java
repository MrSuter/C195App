package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.CustomerDao;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;

public class CustomersScreenController {
    //Navigation navigation = new Navigation();
    CustomerDao customerDao = new CustomerDao();
    EditCustomerScreenController editCustomerScreenController = new EditCustomerScreenController();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private TableColumn<Customer, Integer> colCustomerID;

    @FXML
    private TableColumn<Customer, String> colName;

    @FXML
    private TableColumn<Customer, String> colAddress;
    @FXML
    private TableColumn<Customer, Integer> colDivision;

    @FXML
    private TableColumn<Customer, String> colPostalCode;

    @FXML
    private TableColumn<Customer, String> colPhoneNumber;

    @FXML // Back button, goes to Main screen
    void backToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    @FXML //Add customer button, goes to add customer screen
    void goToAddCustomerScreen(ActionEvent event) throws IOException {
        Navigation.toAddCustomerScreen(event);
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        Customer deleteCustomer = customersTableView.getSelectionModel().getSelectedItem();
        if(deleteCustomer != null) {
            int customerID = deleteCustomer.getCustomerID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete? Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {

                customerDao.deleteCustomer(customerID);
                //System.out.println("Customer ID: " + customerID);

                customersTableView.getItems().clear();
                customersTableView.setItems(customerDao.selectCustomers());
            }

        }
    }

    @FXML
    void goToEditCustomerScreen(ActionEvent event) throws IOException {
        if(customersTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/EditCustomerScreen.fxml"));
            loader.load();
            editCustomerScreenController = loader.getController();

            Customer editCustomer = customersTableView.getSelectionModel().getSelectedItem();
            editCustomerScreenController.setEditCustomer(editCustomer);
            //Navigation.toEditCustomerScreen(event);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        this.colCustomerID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCustomerID()));
        this.colName.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        this.colAddress.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getAddress()));
        this.colDivision.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getDivisionName()));
        this.colPostalCode.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getPostalCode()));
        this.colPhoneNumber.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getPhone()));

        customersTableView.setItems(customerDao.selectCustomers());

    }
}
