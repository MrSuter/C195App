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
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Controller for the screen that adds customers to the database.
 */
public class AddCustomerScreenController {
    private ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();
    private final ObservableList<Country> countryObservableList = FXCollections.observableArrayList();
    private final CountryDao countryDao = new CountryDao();
    private final DivisionDao divisionDao = new DivisionDao();
    private final CustomerDao customerDao = new CustomerDao();
    private final ObservableList<Division> statesList = FXCollections.observableArrayList();
    private final ObservableList<Division> canadaList = FXCollections.observableArrayList();
    private final ObservableList<Division> britainList = FXCollections.observableArrayList();

    @FXML // fx:id="nameTxt"
    private TextField nameTxt; // Value injected by FXMLLoader

    @FXML // fx:id="streetTxt"
    private TextField streetTxt; // Value injected by FXMLLoader

    @FXML // fx:id="cityTxt"
    private TextField cityTxt; // Value injected by FXMLLoader

    @FXML // fx:id="countryComboBox"
    private ComboBox<Country> countryComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="stateComboBox"
    private ComboBox<Division> stateComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="postalCodeTxt"
    private TextField postalCodeTxt; // Value injected by FXMLLoader

    @FXML // fx:id="phoneTxt"
    private TextField phoneTxt; // Value injected by FXMLLoader

    /**
     * Navigates to the customers screen.
     * @param event The user clicks the cancel button.
     * @throws IOException Rethrows IOException toCustomersScreen.
     */
    @FXML
    void goToCustomersScreen(ActionEvent event) throws IOException {
        Navigation.toCustomersScreen(event);
    }

    /**
     * Saves the data in the text fields as a customer to the database.
     * @param event When the user clicks the save button.
     * @throws IOException Rethrows IOException toCustomersScreen.
     */
    @FXML
    void saveCustomer(ActionEvent event) throws IOException {
        int customerID = -1;
        String name = nameTxt.getText();
        String streetAddress = streetTxt.getText();
        String city = cityTxt.getText();
        String address = (streetAddress + " " + city);
        Division division = stateComboBox.getSelectionModel().getSelectedItem();
        String postalCode = postalCodeTxt.getText();
        String phoneNumber= phoneTxt.getText();
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        String createdBy = "User";
        Timestamp lastUpdateTime = new Timestamp(System.currentTimeMillis());
        String lastUpdatedBy = "User";
        int divisionID = division.getDivisionID();
        String divisionName = division.getDivision();

        Customer customer = new Customer(customerID, name, address, postalCode, phoneNumber, createDate, createdBy, lastUpdateTime, lastUpdatedBy, divisionID, divisionName);
        try{
            customerDao.insertCustomer(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Navigation.toCustomersScreen(event);
    }

    /**
     * Sets the division observable list for the division combo box when a country is chosen.
     */
    @FXML
    void chooseCountry() {
        setDivisionObservableList();

    }

    /**
     * Sets the combo boxes for the country and division.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        statesList.addAll(divisionDao.selectDivisionsInCountry(1));
        britainList.addAll(divisionDao.selectDivisionsInCountry(2));
        canadaList.addAll(divisionDao.selectDivisionsInCountry(3));

        countryObservableList.addAll(countryDao.selectCountries());
        countryComboBox.setItems(countryObservableList);

        divisionObservableList.addAll(divisionDao.selectAllDivisions());
        stateComboBox.setItems(divisionObservableList);

    }

    /**
     * Sets the division observable list for the division combo box. Fills the division combo box.
     */
    public void setDivisionObservableList(){
        Country selCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selCountry.getCountryID();
        divisionObservableList = divisionDao.selectDivisionsInCountry(countryID); //divisionDao.selectDivisions(1);
        stateComboBox.setItems(divisionObservableList);
    }
}
