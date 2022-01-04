package controller;

import dao.AppointmentDao;
import dao.ContactDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the contact schedule report.
 */
public class ContactScheduleReportController {
    private final ObservableList<Contact> allContactsList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> contactScheduleList = FXCollections.observableArrayList();
    private final ContactDao contactDao = new ContactDao();
    private final AppointmentDao appointmentDao = new AppointmentDao();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a zzz");


    @FXML
    private TableView<Appointment> contactScheduleTableview;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TableColumn<Appointment, Integer> colAppointmentID;

    @FXML
    private TableColumn<Appointment, String> colTitle;

    @FXML
    private TableColumn<Appointment, String> colType;

    @FXML
    private TableColumn<Appointment, String> colDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colStart;

    @FXML
    private TableColumn<Appointment, LocalDateTime> colEnd;

    @FXML
    private TableColumn<Appointment, Integer> colCustomerID;

    /**
     * Navigates to the main screen.
     * @param event When the user clicks the back button.
     * @throws IOException Rethrows IOException toMainScreen.
     */
    @FXML
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    /**
     * Sets the table after the contact is selected from the combo box.
     * LAMBDA EXPRESSION: The lambda expression is easy to write and read and utilizes the multi-core cpu.
     */
    @FXML
    void selectContact() {
        contactScheduleTableview.getItems().clear();
        contactScheduleList.clear();

        int selContactID = contactComboBox.getValue().getContactID();

        contactScheduleList.addAll(appointmentDao.selectContactSchedule(selContactID));

        colAppointmentID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getAppointmentID()));
        colTitle.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getTitle()));
        colType.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getType()));
        colDescription.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getDescription()));
        colStart.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtStart().withZoneSameInstant(ZoneId.systemDefault()).format(formatter)));
        colEnd.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtEnd().withZoneSameInstant(ZoneId.systemDefault()).format(formatter)));
        colCustomerID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getCustomerID()));
        contactScheduleTableview.getItems().addAll(contactScheduleList);
    }

    /**
     * Sets the contact combo box when the screen is loaded.
     * @throws SQLException Rethrows SQLException selectContacts
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {

        allContactsList.addAll(contactDao.selectContacts());
        contactComboBox.setItems(allContactsList);
    }


}
