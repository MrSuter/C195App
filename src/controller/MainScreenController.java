package controller;

import java.io.IOException;
import java.sql.*;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import dao.AppointmentDao;
import dao.CustomerDao;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

/**
 * Controller for the main screen. Shows the appointment schedule and allows navigation to the rest of the application.
 */
public class MainScreenController {
    private LocalDateTime today = LocalDateTime.now();
    private ObservableList<Appointment> monthAppointmentsList = FXCollections.observableArrayList();
    private ObservableList<Appointment> weekAppointmentsList = FXCollections.observableArrayList();
    private ObservableList<Appointment> allAppointmentsList = FXCollections.observableArrayList();
    private ObservableList<Appointment> upcomingAppointmentsList = FXCollections.observableArrayList();
    private AppointmentDao appointmentDao = new AppointmentDao();
    private EditAppointmentScreenController editAppointmentScreenController;
    private ObservableList<Object> objects = appointmentDao.getAllRecords();
    private CustomerDao customerDao = new CustomerDao();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a zzz");
    private ZonedDateTime nowZdt = ZonedDateTime.now().withZoneSameInstant(ZoneId.systemDefault());


    @FXML
    private RadioButton allAppointments;

    @FXML
    private RadioButton monthAppointments;

    @FXML
    private RadioButton weekAppointments;

    @FXML // fx:id="sortAppointments"
    private ToggleGroup sortAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentsTableView"
    private TableView<Appointment> appointmentsTableView; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Appointment, Integer> colAppointmentID;

    @FXML
    private TableColumn<Appointment, String> colTitle;

    @FXML
    private TableColumn<Appointment, String> colDescription;

    @FXML
    private TableColumn<Appointment, String> colLocation;

    @FXML
    private TableColumn<Appointment, String> colContact;

    @FXML
    private TableColumn<Appointment, String> colType;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colStart;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colEnd;

    @FXML
    private TableColumn<Appointment, Integer> colCustomerID;

    @FXML
    private TableColumn<Appointment, Integer> colUserID;

    /**
     * Deletes the selected appointment from the database.
     * @param event User clicks the delete button.
     * @throws SQLException Rethrows SQLException when executing the delete query.
     */
    @FXML
    void deleteAppointment(ActionEvent event) throws SQLException {
        Appointment selAppt = appointmentsTableView.getSelectionModel().getSelectedItem();
        if(selAppt != null){
            int selApptID = selAppt.getAppointmentID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete? Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();


            if(result.isPresent() && result.get() == ButtonType.OK){
                allAppointmentsList.remove(selAppt);
                appointmentsTableView.getItems().remove(selAppt);
                appointmentDao.delete(selApptID);
                String type = selAppt.getType();

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setContentText("Appointment ID canceled: " + selApptID + " | type: " + type);
                alert1.show();
            }
        }
    }

    /**
     * Updates the selected appointment from the database.
     * @param event User clicks the edit button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML
    void editAppointment(ActionEvent event) throws IOException {
        if(appointmentsTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/EditAppointmentScreen.fxml"));
            loader.load();

            editAppointmentScreenController = loader.getController();

            Appointment selAppt = appointmentsTableView.getSelectionModel().getSelectedItem();
            editAppointmentScreenController.setEditAppointment(selAppt);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Exits the program.
     * @param event When the user clicks the exit button.
     */
    @FXML
    void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Navigates to the add appointment screen.
     * @param event When the user clicks the add button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML
    void goToAddAppointment(ActionEvent event) throws IOException {
        Navigation.toAddAppointmentScreen(event);
    }

    /**
     * Navigates to the customers screen.
     * @param event When the user clicks the customers button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML
    void goToCustomers(ActionEvent event) throws IOException {
        Navigation.toCustomersScreen(event);
    }

    /**
     * Navigates to the reports screen.
     * @param event When the user clicks the reports button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML // Reports button
    void goToReports(ActionEvent event) throws IOException {
        Navigation.toReportsScreen(event);
    }

    /**
     * Navigates to the login screen. Effectively logging out the current user.
     * @param event When the user clicks the logout button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML //Logout button
    void goToLoginScreen(ActionEvent event) throws IOException {
        Navigation.toLoginScreen(event);
    }

    /**
     * Displays all the appointments from the database.
     */
    @FXML
    void showAllAppointments() {
        appointmentsTableView.getItems().clear();
        appointmentsTableView.getItems().addAll(allAppointmentsList);
    }

    /**
     * Displays the appointments for the current month in the table.
     * @param event When the user clicks the month radio button.
     */
    @FXML //Month radio
    void sortByMonth(ActionEvent event) {
        appointmentsTableView.getItems().clear();
        monthAppointmentsList.clear();
        for(Object o : objects){
            Appointment a = (Appointment) o;
            Month startMonth = a.getStart().getMonth();
            Month todayMonth = today.getMonth();
            if(startMonth == todayMonth) {
                monthAppointmentsList.add(a);
            }
        }
        appointmentsTableView.getItems().addAll(monthAppointmentsList);
    }

    /**
     * Displays the current week's appointments from Sunday to Saturday.
     * @param event When the user clicks the week radio button.
     */
    @FXML //Week radio
    void sortByWeek(ActionEvent event) {
        appointmentsTableView.getItems().clear();
        weekAppointmentsList.clear();

        LocalDateTime endOfWeek = LocalDateTime.of(1989, 10, 16, 0, 0);
        LocalDateTime startOfWeek = LocalDateTime.of(1988, 3, 13, 0, 0);
        DayOfWeek dayOfWeek = today.getDayOfWeek();



        if (dayOfWeek == DayOfWeek.SUNDAY) {
            endOfWeek = today.withHour(23).plusDays(6);
            startOfWeek = today.withHour(0);
        }
        if (dayOfWeek == DayOfWeek.MONDAY) {
            endOfWeek = today.withHour(23).plusDays(5);
            startOfWeek = today.withHour(0).minusDays(1);
        }
        if (dayOfWeek == DayOfWeek.TUESDAY) {
            endOfWeek = today.withHour(23).plusDays(4);
            startOfWeek = today.withHour(0).minusDays(2);
        }
        if (dayOfWeek == DayOfWeek.WEDNESDAY) {
            endOfWeek = today.withHour(23).plusDays(3);
            startOfWeek = today.withHour(0).minusDays(3);
        }
        if (dayOfWeek == DayOfWeek.THURSDAY) {
            endOfWeek = today.withHour(23).plusDays(2);
            startOfWeek = today.withHour(0).minusDays(4);
        }
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            endOfWeek = today.withHour(23).plusDays(1);
            startOfWeek = today.withHour(0).minusDays(5);
        }
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            endOfWeek = today.withHour(23).plusDays(0);
            startOfWeek = today.withHour(0).minusDays(6);
        }

        for (Object o : objects) {
            Appointment a = (Appointment) o;
            LocalDateTime start = a.getStart();
            if (start.isAfter(ChronoLocalDateTime.from(startOfWeek)) && start.isBefore(ChronoLocalDateTime.from(endOfWeek))) {
                weekAppointmentsList.add(a);
            }
        }
        appointmentsTableView.getItems().addAll(weekAppointmentsList);
    }


    /**
     * Sets the appointments table when the screen is loaded. Alerts the user if there is an upcoming appointment.
     * LAMBDA: This lambda sets the table columns and makes the code easy to read. It also utilizes the multi-core cpus.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        colDescription.setCellValueFactory((cellData) -> new ReadOnlyStringWrapper(cellData.getValue().getDescription()));
        colAppointmentID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getAppointmentID()));
        colContact.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getContactID()));
        colType.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getType()));
        colCustomerID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getCustomer()));
        colEnd.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtEnd().format(formatter)));
        colStart.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtStart().format(formatter)));
        colLocation.setCellValueFactory((cellData) -> new ReadOnlyStringWrapper(cellData.getValue().getLocation()));
        colTitle.setCellValueFactory((cellData) -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
        colUserID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getUser()));


        if(allAppointments.isSelected()){
            setTableView(setAllAppointmentsList(objects));
        } else if (weekAppointments.isSelected()){
            setTableView(setWeeksAppointments(weekAppointmentsList));
        }

        //15 minute alert feature
        for (Appointment a : allAppointmentsList){
            if(a.getZdtStart().isBefore(nowZdt.plusMinutes(15)) && a.getZdtStart().isAfter(nowZdt)) {
                upcomingAppointmentsList.add(a);
            }
        }
        if(!upcomingAppointmentsList.isEmpty()){
            for(Appointment a : upcomingAppointmentsList) {
                    //System.out.println("Upcoming appointment: " + a.getTitle());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setContentText("Upcoming appointment ID: " + a.getAppointmentID() + " | time: " + a.getZdtStart().withZoneSameInstant(ZoneId.systemDefault()).format(formatter));
                    alert1.show();
            }
        } else {
            //System.out.println("No upcoming appointment in the next 15 minutes.");
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("No upcoming appointment in the next 15 minutes.");
            alert1.show();
        }




    }

    /**
     * Sets all appointments from the database to the allAppointmentsList.
     * @param objects The appointment objects to be added to the allAppointmentsList.
     * @return The allAppointmentsList of appointment objects.
     */
    private ObservableList<Appointment> setAllAppointmentsList(ObservableList<Object> objects){
        allAppointmentsList.clear();
        for (Object o : objects) {
            Appointment a = (Appointment) o;

            ZonedDateTime startSysDef = a.getZdtStart().withZoneSameInstant(ZoneId.systemDefault());
            a.setZdtStart(startSysDef);
            ZonedDateTime endSysDef = a.getZdtEnd().withZoneSameInstant(ZoneId.systemDefault());
            a.setZdtEnd(endSysDef);
            allAppointmentsList.add(a);
        }
        return allAppointmentsList;
    }

    /**
     * Sets the tableview with the list in the parameter.
     * @param appointments List of appointments to be added to the tableview.
     */
    private void setTableView(ObservableList<Appointment> appointments){
        appointmentsTableView.getItems().addAll(appointments);

    }

    /**
     * Sorts the list of all appointments and returns only the appointments in the current week. Prepares the list to be added to the tableview.
     * @param objects The appointment objects to be sorted and put into the current week list.
     * @return The list of appointments in the current week.
     */
    private ObservableList<Appointment> setWeeksAppointments(ObservableList<Appointment> objects){
        weekAppointmentsList.clear();
        LocalDateTime endOfWeek = LocalDateTime.of(1989, 10, 16, 0, 0);
        LocalDateTime startOfWeek = LocalDateTime.of(1988, 3, 13, 0, 0);

        for(Appointment o : objects) {
            Appointment a = o;
            LocalDateTime start = a.getStart();
            ZonedDateTime startUTC = start.atZone(ZoneId.of("UTC"));
            ZonedDateTime startSysDef = startUTC.withZoneSameInstant(ZoneId.systemDefault());
            start = startSysDef.toLocalDateTime();
            a.setStart(start);


            DayOfWeek dayOfWeek = today.getDayOfWeek();
            System.out.println(dayOfWeek);
            if (dayOfWeek == DayOfWeek.SUNDAY) {
                endOfWeek = today.plusDays(6);
                startOfWeek = today;
            }
            if (dayOfWeek == DayOfWeek.MONDAY) {
                endOfWeek = today.plusDays(5);
                startOfWeek = today.minusDays(1);
            }
            if (dayOfWeek == DayOfWeek.TUESDAY) {
                endOfWeek = today.plusDays(4);
                startOfWeek = today.minusDays(2);
            }
            if (dayOfWeek == DayOfWeek.WEDNESDAY) {
                endOfWeek = today.plusDays(3);
                startOfWeek = today.minusDays(3);
            }
            if (dayOfWeek == DayOfWeek.THURSDAY) {
                endOfWeek = today.plusDays(2);
                startOfWeek = today.minusDays(4);
            }
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                endOfWeek = today.plusDays(1);
                startOfWeek = today.minusDays(5);
            }
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                endOfWeek = today.plusDays(0);
                startOfWeek = today.minusDays(6);
            }
            if (start.isAfter(ChronoLocalDateTime.from(startOfWeek)) && start.isBefore(ChronoLocalDateTime.from(endOfWeek))) {
                allAppointmentsList.add(a);//weekAppointmentList.add(a);
                System.out.println("In week");
            }


            weekAppointmentsList.add(a);
        }
        return weekAppointmentsList;
    }


}