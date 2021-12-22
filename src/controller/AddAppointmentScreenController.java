package controller;

import java.io.IOException;
import java.sql.*;
import java.time.*;
import dao.AppointmentDao;
import dao.ContactDao;
import dao.CustomerDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import utils.Validation;

/**
 * Controller for the screen that adds appointments to the database.
 */
public class AddAppointmentScreenController {
    private final AppointmentDao appointmentDao = new AppointmentDao();
    private final UserDao userDao = new UserDao();
    private final ContactDao contactDao = new ContactDao();
    private final CustomerDao customerDao = new CustomerDao();
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private final ObservableList<String> typeList = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ObservableList<User> userList = FXCollections.observableArrayList();
    private final ZoneId est = ZoneId.of("America/New_York");
    private ZoneId localZone = ZoneId.systemDefault();


    @FXML private RadioButton startAMRB;
    @FXML private RadioButton startPMRB;
    @FXML private RadioButton endAMRB;
    @FXML private RadioButton endPMRB;

    @FXML // fx:id="titleTxt"
    private TextField titleTxt; // Value injected by FXMLLoader

    @FXML // fx:id="customerComboBox"
    private ComboBox<Customer> customerComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="userComboBox"
    private ComboBox<User> userComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTxt"
    private TextField descriptionTxt; // Value injected by FXMLLoader

    @FXML // fx:id="locationTxt"
    private TextField locationTxt; // Value injected by FXMLLoader

    @FXML // fx:id="contactComboBox"
    private ComboBox<Contact> contactComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="typeComboBox"
    private ComboBox<String> typeComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="startDatePicker"
    private DatePicker startDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="startHourComboBox"
    private ComboBox<Integer> startHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="startMinComboBox"
    private ComboBox<Integer> startMinComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endDatePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="endHourComboBox"
    private ComboBox<Integer> endHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endMinComboBox"
    private ComboBox<Integer> endMinComboBox; // Value injected by FXMLLoader

    @FXML //Cancel button
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    /**
     * Saves the data in the text fields as an appointment in the database. Calls the validation methods to ensure the appointment is acceptable.
     * @param event User clicks the save button.
     * @throws IOException Rethrows IOException when switching screens Navigation.toMainScreen(event);
     * @throws SQLException Rethrows SQLException selectSingleCustomer, selectSingleUser, insert.
     */
    @FXML //Save appointment button
    void onSave(ActionEvent event) throws IOException, SQLException {
        try {
            int appointmentID = -1;
            String title = titleTxt.getText();
            String description = descriptionTxt.getText();
            String location = locationTxt.getText();
            int contact = contactComboBox.getValue().getContactID();
            String type = typeComboBox.getValue();
            LocalTime startTime;// = LocalTime.of(startHourComboBox.getValue(), startMinComboBox.getValue());
            LocalDate startDate = startDatePicker.getValue();
            ZonedDateTime startLocal;
            ZonedDateTime startEST;
            ZonedDateTime endEST;
            ZonedDateTime endLocal;
            LocalTime endTime;// = LocalTime.of(endHourComboBox.getValue(), endMinComboBox.getValue());
            LocalDate endDate = endDatePicker.getValue();
            LocalDateTime end; // = Timestamp.valueOf(LocalDateTime.of(endDate, endTime));
            int customerID = customerComboBox.getValue().getCustomerID();
            int userID = userComboBox.getValue().getUserID();
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = User.getCurrentUser().getUserName();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = User.getCurrentUser().getUserName();

            //SELECT single customer from customerID
            Customer customer = CustomerDao.selectSingleCustomer(customerID);
            User userObj = UserDao.selectSingleUser(userID);

            int selStartHr;
            int selEndHr;
            if(startPMRB.isSelected()){
                selStartHr = startHourComboBox.getValue() + 12;
                if(selStartHr == 24)
                    selStartHr = 12;
                startTime = LocalTime.of(selStartHr, startMinComboBox.getValue());
            } else if(startAMRB.isSelected() && (startHourComboBox.getValue() == 12)){
                selStartHr = 0;
                startTime = LocalTime.of(selStartHr, startMinComboBox.getValue());
            }
            else{
                startTime = LocalTime.of(startHourComboBox.getValue(), startMinComboBox.getValue());
            }
            startLocal = ZonedDateTime.of(startDate, startTime, localZone);//start = LocalDateTime.of(startDate, startTime); // = Timestamp.valueOf(LocalDateTime.of(startDate, startTime));
            startEST = startLocal.withZoneSameInstant(est);

            if(endPMRB.isSelected()){
                selEndHr = endHourComboBox.getValue() + 12;
                if(selEndHr == 24)
                    selEndHr = 12;
                endTime = LocalTime.of(selEndHr, endMinComboBox.getValue());
            } else if(endAMRB.isSelected() && (endHourComboBox.getValue() == 12)){
                selEndHr = 0;
                endTime = LocalTime.of(selEndHr, endMinComboBox.getValue());
            }
            else{
                endTime = LocalTime.of(endHourComboBox.getValue(), endMinComboBox.getValue());
            }
            end = LocalDateTime.of(endDate, endTime);
            endLocal = ZonedDateTime.of(end, localZone);
            endEST = endLocal.withZoneSameInstant(est);

            //if(check customer schedule)
            if(!Validation.overlap(customerID, appointmentID, startEST, endEST)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Overlapping customer appointment");
                alert.show();
            }

            if(Validation.hours(startEST, endEST) && Validation.overlap(customerID, appointmentID, startEST, endEST)){
                Appointment appointment = new Appointment(appointmentID, title, description, location, type, LocalDateTime.of(startDate, startTime), end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contact, startEST, endEST, customer, userObj);
                appointmentDao.insert(appointment);
                Navigation.toMainScreen(event);
            }

        } catch (NullPointerException nullPointerException){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "All fields are required");
            alert.showAndWait();
        }
    }

    /**
     * Sets the end date to be the same as the start date.
     */
    @FXML
    void setDate() {
        endDatePicker.setValue(startDatePicker.getValue());
    }

    /**
     * Sets the local time zone, sets the combo boxes when the screen is opened.
     * @throws SQLException Rethrows SQLException selectContacts, selectAllUsers.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        localZone = ZoneId.systemDefault();

        int startHour = 1;
        int endHour = 12;
        int startMin = 0;
        int endMin = 60;

        while(startHour <= endHour){
            startHourComboBox.getItems().add(startHour);
            endHourComboBox.getItems().add(startHour);
            startHour = startHour + 1;
        }
        while(startMin < endMin){
            startMinComboBox.getItems().add(startMin);
            endMinComboBox.getItems().add(startMin);
            startMin = startMin + 1;
        }
        //Select Contacts and add to list
        contactList.addAll(contactDao.selectContacts());
        contactComboBox.setItems(contactList);
        //Add types to list
        if(typeList.size() == 0) {
            typeList.add("Planning Session");
            typeList.add("De-Briefing");
            typeList.add("Lunch");
        }
        typeComboBox.setItems(typeList);
        //Select Customers and add to list
        customerList.addAll(customerDao.selectCustomers());
        customerComboBox.setItems(customerList);
        //Select Users and add to list
        userList.addAll(userDao.selectAllUsers());
        userComboBox.setItems(userList);

    }
}