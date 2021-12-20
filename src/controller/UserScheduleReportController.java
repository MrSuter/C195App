package controller;

import controller.Navigation;
import dao.AppointmentDao;
import dao.UserDao;
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
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UserScheduleReportController {
    private ObservableList<User> allUserList = FXCollections.observableArrayList();
    private ObservableList<Appointment> userScheduleList = FXCollections.observableArrayList();
    private UserDao userDao = new UserDao();
    private AppointmentDao appointmentDao = new AppointmentDao();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a zzz");


    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TableView<Appointment> contactScheduleTableview;

    @FXML
    private TableColumn<Appointment, Integer> colAppointmentID;

    @FXML
    private TableColumn<Appointment, String> colTitle;

    @FXML
    private TableColumn<Appointment, String> colType;

    @FXML
    private TableColumn<Appointment, String> colDescription;

    @FXML
    private TableColumn<Appointment, String> colStart;

    @FXML
    private TableColumn<Appointment, String> colEnd;

    @FXML
    private TableColumn<Appointment, String> colCustomerID;

    @FXML
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);

    }

    @FXML
    void selectUser(ActionEvent event) {
        contactScheduleTableview.getItems().clear();
        userScheduleList.clear();

        int selUser = userComboBox.getValue().getUserID();
        userScheduleList.addAll(appointmentDao.selectUserSchedule(selUser));

        this.colAppointmentID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getAppointmentID()));
        this.colTitle.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getTitle()));
        this.colType.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getType()));
        this.colDescription.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getDescription()));
        this.colStart.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtStart().withZoneSameInstant(ZoneId.systemDefault()).format(formatter)));
        this.colEnd.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getZdtEnd().withZoneSameInstant(ZoneId.systemDefault()).format(formatter)));
        this.colCustomerID.setCellValueFactory((cellData) -> new ReadOnlyObjectWrapper(cellData.getValue().getCustomerID()));
        contactScheduleTableview.getItems().addAll(userScheduleList);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {

        allUserList.addAll(userDao.selectAllUsers());
        userComboBox.setItems(allUserList);






    }

}
