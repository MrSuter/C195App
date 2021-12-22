package controller;

import dao.CountryDao;
import dao.CustomerDao;
import dao.DivisionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.Division;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Controller for the edit customer screen.
 */
public class EditCustomerScreenController {
    ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<Division> divisionList = FXCollections.observableArrayList();
    Division selDivision;
    Country selCountry;
    Customer editCustomer;
    DivisionDao divisionDao = new DivisionDao();
    CountryDao countryDao = new CountryDao();
    CustomerDao customerDao = new CustomerDao();


    //public Customer getEditCustomer() {        return editCustomer;    }

    /**
     * Sets the customer to be edited from the customer screen. Sets all of the text fields and combo boxes with the selected appointment's data.
     * @param editCustomer The selected customer from the customer screen.
     */
    public void setEditCustomer(Customer editCustomer) {
        this.editCustomer = editCustomer;
        int customerID = editCustomer.getCustomerID();
        customerIDTxt.setText(String.valueOf(customerID));
        nameTxt.setText(editCustomer.getName());
        streetTxt.setText(editCustomer.getAddress());
        postalCodeTxt.setText(editCustomer.getPostalCode());
        phoneTxt.setText(editCustomer.getPhone());

        int divisionID = editCustomer.getDivisionID();
        selDivision = DivisionDao.selectSingleDivision(divisionID);

        int countryID = selDivision.getCountryID();
        selCountry = countryDao.selectSingleCountry(countryID);

        countryComboBox.setValue(selCountry);
        divisionList = divisionDao.selectDivisionsInCountry(countryID);
        stateComboBox.setItems(divisionList);
        stateComboBox.setValue(selDivision);
    }

    @FXML // fx:id="customerIDTxt"
    private TextField customerIDTxt; // Value injected by FXMLLoader

    @FXML // fx:id="nameTxt"
    private TextField nameTxt; // Value injected by FXMLLoader

    @FXML // fx:id="streetTxt"
    private TextField streetTxt; // Value injected by FXMLLoader

    @FXML // fx:id="cityTxt"
    private TextField cityTxt; // Value injected by FXMLLoader

    @FXML // fx:id="countryComboBox"
    private ComboBox<Country> countryComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="stateComboBox"
    private ComboBox<Division > stateComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="postalCodeTxt"
    private TextField postalCodeTxt; // Value injected by FXMLLoader

    @FXML // fx:id="phoneTxt"
    private TextField phoneTxt; // Value injected by FXMLLoader

    /**
     * Sets the division combo box after a country is selected.
     */
    @FXML
    void chooseCountry() {
        Country selCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selCountry.getCountryID();
        divisionList = divisionDao.selectDivisionsInCountry(countryID);
        stateComboBox.setItems(divisionList);

    }

    /**
     * Navigates to the customer screen.
     * @param event When the user clicks the cancel button.
     * @throws IOException Rethrows IOException when loading the screen.
     */
    @FXML
    void goToCustomersScreen(ActionEvent event) throws IOException {
        Navigation.toCustomersScreen(event);
    }

    /**
     * Updates the customer in the database that was selected in the customer screen.
     * @param event When the user clicks the save button.
     * @throws IOException Rethrows IOException when the screen is loaded.
     */
    @FXML
    void saveCustomer(ActionEvent event) throws IOException {
        int customerID = editCustomer.getCustomerID();
        String customerName = nameTxt.getText();
        String address = streetTxt.getText();
        String postalCode = postalCodeTxt.getText();
        String phone = phoneTxt.getText();
        Timestamp createDate = editCustomer.getCreateDate();
        String createdBy = editCustomer.getCreatedBy();
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "User";
        int divisionID = stateComboBox.getSelectionModel().getSelectedItem().getDivisionID();
        String divisionName = DivisionDao.selectSingleDivision(divisionID).getDivision();

        Customer customer = new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID, divisionName);
        customerDao.update(customer);

        Navigation.toCustomersScreen(event);
    }

    /**
     * Sets the combo boxes when the screen is loaded.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        countryList = countryDao.selectCountries();
        divisionList = divisionDao.selectAllDivisions();
        countryComboBox.setItems(countryList);

    }
}
