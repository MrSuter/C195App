package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

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

public class AddCustomerScreenController {
    private ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();
    private ObservableList<Country> countryObservableList = FXCollections.observableArrayList();
    private CountryDao countryDao = new CountryDao();
    private DivisionDao divisionDao = new DivisionDao();
    private CustomerDao customerDao = new CustomerDao();
    private int countryID;
    private ObservableList<Division> statesList = FXCollections.observableArrayList();
    private ObservableList<Division> canadaList = FXCollections.observableArrayList();
    private ObservableList<Division> britainList = FXCollections.observableArrayList();

    Country selCountry; //= countryComboBox.getSelectionModel().getSelectedItem();
    Division selDivision;// = stateComboBox.getSelectionModel().getSelectedItem();






    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML
    void goToCustomersScreen(ActionEvent event) throws IOException {
        Navigation.toCustomersScreen(event);
    }

    @FXML
    void saveCustomer(ActionEvent event) throws IOException {
        int customerID = -1;
        String name = nameTxt.getText();
        String streetAddress = streetTxt.getText();
        String city = cityTxt.getText();
        String address = (streetAddress + " " + city);
        Country country = countryComboBox.getSelectionModel().getSelectedItem();
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

    @FXML
    void chooseCountry(ActionEvent event) {
        setDivisionObservableList(event);
        //countryID = countryComboBox.getSelectionModel().getSelectedItem().getCountryID();
        //divisionObservableList.addAll(divisionDao.selectDivisions(countryID));
        //stateComboBox.setItems(divisionObservableList);
        //selCountry = countryComboBox.getSelectionModel().getSelectedItem();
        //selDivision = stateComboBox.getSelectionModel().getSelectedItem();
        //int selCountryID = selCountry.getCountryID();
        //divisionObservableList = divisionDao.selectDivisionsInCountry(selCountryID);

       // if(selDivision != null) {
        //    if(divisionObservableList.contains(selDivision)){
        //        stateComboBox.setItems(divisionObservableList);
        //        stateComboBox.setValue(selDivision);
        //    }else{
        //        stateComboBox.setItems(divisionObservableList);
        //    }
       // }else {
       //     if (divisionObservableList.contains()) {
       //         setDivisionObservableList(event);
       //     }
       // }

    }

    @FXML
    void chooseDivision(ActionEvent event) {
        selDivision = stateComboBox.getSelectionModel().getSelectedItem();
        //selCountry = countryComboBox.getSelectionModel().getSelectedItem();
        /*
        if(countryComboBox.getSelectionModel().getSelectedItem() == null) {

            if (stateComboBox.getSelectionModel().getSelectedItem().getCountryID() == 1) {
                selCountry = countryDao.selectSingleCounty(1);
                countryComboBox.setValue(selCountry);
                stateComboBox.setValue(selDivision);
            }
        }
         */

    }

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

    public void setDivisionObservableList(ActionEvent event){
        //ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();
        Country selCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryID = selCountry.getCountryID();
        divisionObservableList = divisionDao.selectDivisionsInCountry(countryID); //divisionDao.selectDivisions(1);
        stateComboBox.setItems(divisionObservableList);
        //System.out.println("Country ID: " + selCountry.getCountryID() + " | " + selCountry.getCountryName() + " | " + "Number of divisions: " + divisionObservableList.stream().count());

    }
}
