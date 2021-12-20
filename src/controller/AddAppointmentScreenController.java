package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Optional;
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

import javax.security.auth.callback.Callback;

public class AddAppointmentScreenController {
    private AppointmentDao appointmentDao = new AppointmentDao();
    private UserDao userDao = new UserDao();
    private ContactDao contactDao = new ContactDao();
    private CustomerDao customerDao = new CustomerDao();
    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ZoneId est = ZoneId.of("America/New_York");
    private ZoneId localZone = ZoneId.systemDefault();










        @FXML private RadioButton startAMRB;
    @FXML private RadioButton startPMRB;
    @FXML private RadioButton endAMRB;
    @FXML private RadioButton endPMRB;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML // fx:id="startAmPM"
    private ToggleGroup startAmPM; // Value injected by FXMLLoader

    @FXML // fx:id="endDatePicker"
    private DatePicker endDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="endHourComboBox"
    private ComboBox<Integer> endHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endMinComboBox"
    private ComboBox<Integer> endMinComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="endAmPm"
    private ToggleGroup endAmPm; // Value injected by FXMLLoader

    @FXML //Cancel button
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    @FXML
    void onAM(ActionEvent event) {

    }

    @FXML
    void onPM(ActionEvent event) throws IOException {
    }

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
            Customer customer = customerDao.selectSingleCustomer(customerID);
            User userObj = userDao.selectSingleUser(userID);

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

            /*  Time validation
            if(endEST.isBefore(startEST)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("End time must be after start time");
                alert.show();
            }else if(((startEST.getHour() >= 8 && startEST.getHour() <= 22) && (endEST.getHour() >= 8 && endEST.getHour() <=22) )){
                Appointment appointment = new Appointment(appointmentID, title, description, location, type, LocalDateTime.of(startDate, startTime), end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, user, contact, startLocal, end.atZone(ZoneId.systemDefault()), customer);
                appointmentDao.insert(appointment);
                Navigation.toMainScreen(event);

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Time not within operating hours.");
                alert.show();
            }
            */

            /*
            if((startTime.isBefore(closedZ) && startTime.isAfter(openZ)) && (endTime.isAfter(openZ) && endTime.isBefore(closedZ))) {
                Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, user, contact, start.atZone(ZoneId.systemDefault()), end.atZone(ZoneId.systemDefault()), customer);
                appointmentDao.insert(appointment);
                Navigation.toMainScreen(event);

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Time not within operating hours.");
                alert.show();
            }
             */



        } catch (NullPointerException nullPointerException){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "All fields are required");
            Optional<ButtonType> result = alert.showAndWait();
        }


        /*
                Connection conn = DBConnection.startConnection(); //Connect to database

        String insertStatement = "INSERT INTO appointments (Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        DBQuery.setPreparedStatement(conn, insertStatement); //Create prepared statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setInt(4, contact);
        ps.setString(5, type);
        ps.setTimestamp(6, start);
        ps.setTimestamp(7, end);
        ps.setInt(8, customer);
        ps.setInt(9, user);

        //Execute SQL Statement
        ps.execute();

         */

    }

    @FXML
    void setDate(ActionEvent event) {
        endDatePicker.setValue(startDatePicker.getValue());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        localZone = ZoneId.systemDefault();
        System.out.println(User.getCurrentUser().getUserName());
        //System.out.println(localZone);


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





        //cmbFruit.getItems().add(apple);


    }
}