package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

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
 * Controller for the edit appointment screen.
 */
public class EditAppointmentScreenController {
    private AppointmentDao appointmentDao = new AppointmentDao();
    private ContactDao contactDao = new ContactDao();
    private CustomerDao customerDao = new CustomerDao();
    private UserDao userDao = new UserDao();
    private Appointment editAppointment;
    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ZoneId est = ZoneId.of("America/New_York");
    private ZoneId localZone = ZoneId.systemDefault();


    //public Appointment getEditAppointment() {        return editAppointment;    }

    /**
     * Sets the appointment to be edited from the appointment screen. Fills all of the combo boxes and fields with the appointment's data.
     * @param editAppointment The appointment that was selected in the appointment screen.
     */
    public void setEditAppointment(Appointment editAppointment) {
        this.editAppointment = editAppointment;
        titleTxt.setText(editAppointment.getTitle());
        descriptionTxt.setText(editAppointment.getDescription());
        locationTxt.setText(editAppointment.getLocation());
        //set contact combo box to selected contact
        int contactID = editAppointment.getContactID();
        for(Contact contact : contactComboBox.getItems()){
            if(contactID == contact.getContactID()){
                contactComboBox.setValue(contact);
                break;
            }
        }
        //set type combo box
        typeComboBox.setValue(editAppointment.getType());
        //set start date and time
        int startHour = editAppointment.getZdtStart().getHour();
        startPMRB.setSelected(startHour >= 12);
        startAMRB.setSelected(startHour < 12);
        if(startHour > 12)
            startHour = startHour - 12;
        startDatePicker.setValue(editAppointment.getStart().toLocalDate());
        startHourComboBox.setValue(startHour);
        startMinComboBox.setValue(editAppointment.getZdtStart().getMinute());
        //set end date and time
        int endHour = editAppointment.getZdtEnd().getHour();
        endPMRB.setSelected(endHour >= 12);
        endAMRB.setSelected(endHour < 12);
        if(endHour > 12)
            endHour = endHour -12;
        endDatePicker.setValue(editAppointment.getEnd().toLocalDate());
        endHourComboBox.setValue(endHour);
        endMinComboBox.setValue(editAppointment.getZdtEnd().getMinute());
        //set customer combo box
        int customerID = editAppointment.getCustomerID();
        for(Customer customer : customerComboBox.getItems()){
            if(customerID == customer.getCustomerID()){
                customerComboBox.setValue(customer);
                break;
            }
        }
        //set user combo box
        int userID = editAppointment.getUserID();
        for(User user : userComboBox.getItems()){
            if(userID == user.getUserID()){
                userComboBox.setValue(user);
                break;
            }
        }
        //set appointment id
        int appointmentID = editAppointment.getAppointmentID();
        IDText.setText(String.valueOf(appointmentID));
    }

    @FXML // fx:id="IDText"
    private TextField IDText; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="titleTxt"
    private TextField titleTxt; // Value injected by FXMLLoader
    //public void setTitleTxt(String string){        titleTxt.setText(string);    }

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

    @FXML // fx:id="startAMRB"
    private RadioButton startAMRB; // Value injected by FXMLLoader

    //@FXML // fx:id="startAmPM"
    //private ToggleGroup startAmPM; // Value injected by FXMLLoader

    @FXML // fx:id="startPMRB"
    private RadioButton startPMRB; // Value injected by FXMLLoader

    @FXML // fx:id="endDatePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="endHourComboBox"
    private ComboBox<Integer> endHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endMinComboBox"
    private ComboBox<Integer> endMinComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endAMRB"
    private RadioButton endAMRB; // Value injected by FXMLLoader

    @FXML // fx:id="endAmPm"
    private ToggleGroup endAmPm; // Value injected by FXMLLoader

    @FXML // fx:id="endPMRB"
    private RadioButton endPMRB; // Value injected by FXMLLoader

    /**
     * Navigates to the main screen.
     * @param event When the user clicks the cancel button.
     * @throws IOException Rethrows IOException when toMainScreen is called.
     */
    @FXML //Cancel button
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    /**
     * Updates the appointment that was to be edited to the database.
     * @param event When the user clicks the save button.
     * @throws IOException Rethrows IOException when loading the next screen.
     * @throws SQLException Rethrows IOException when executing the SQL statement.
     */
    @FXML //Save button
    void onSave(ActionEvent event) throws IOException, SQLException {
        int appointmentID = editAppointment.getAppointmentID();
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
        LocalDateTime start;
        int customerID = customerComboBox.getValue().getCustomerID();
        int userID = userComboBox.getValue().getUserID();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getCurrentUser().getUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = User.getCurrentUser().getUserName();
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
        start = LocalDateTime.of(startDate, startTime);
        startLocal = ZonedDateTime.of(start, localZone);//start = LocalDateTime.of(startDate, startTime); // = Timestamp.valueOf(LocalDateTime.of(startDate, startTime));
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
        System.out.println("End time: " + endEST);
        System.out.println("Local timezone: " + localZone);

        if(!Validation.overlap(customerID, appointmentID, startEST, endEST)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Overlapping customer appointment");
            alert.show();
        }

        if(Validation.hours(startEST, endEST) && Validation.overlap(customerID, appointmentID, startEST, endEST)){
            Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contact, startEST, endEST, customer, userObj);
            appointmentDao.update(appointment);
            Navigation.toMainScreen(event);
        }
    }

    /**
     * Sets the end date of the appointment to be the same as the start date.
     * @param event When the user chooses a start date.
     */
    @FXML
    void setDate(ActionEvent event) {
        endDatePicker.setValue(startDatePicker.getValue());
    }

    /**
     * Sets the combo boxes when the screen is loaded.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        int startHr = 1;
        int endHour = 12;
        int startMin = 0;
        int endMin = 60;

        //Populate hour selection combobox
        while(startHr <= endHour){
            startHourComboBox.getItems().add(startHr);
            endHourComboBox.getItems().add(startHr);
            startHr = startHr + 1;
        }
        //Populate minute selection combobox
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
        //put types into comboBox
        typeComboBox.setItems(typeList);
        //Select Customers and add to list
        customerList.addAll(customerDao.selectCustomers());
        customerComboBox.setItems(customerList);
        //Select Users and add to list
        userList.addAll(userDao.selectAllUsers());
        userComboBox.setItems(userList);


    }
}