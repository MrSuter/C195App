package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ResourceBundle;

import dao.AppointmentDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import model.Appointment;

/**
 * Controller for the reports screen.
 */
public class ReportsScreenController {

    TotalAppointmentReportController totalAppointmentReportController = new TotalAppointmentReportController();
    AppointmentDao appointmentDao = new AppointmentDao();
    ObservableList<Object> allAppointmentsList = appointmentDao.getAllRecords();


    ObservableList<Appointment> planningSessionAppointments = FXCollections.observableArrayList();
    ObservableList<Appointment> deBriefingAppointments = FXCollections.observableArrayList();
    ObservableList<Appointment> lunchAppointments = FXCollections.observableArrayList();
    ObservableList<Appointment> janMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> febMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> marMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> aprMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> mayMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> junMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> julMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> augMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> sepMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> octMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> novMonthList = FXCollections.observableArrayList();
    ObservableList<Appointment> decMonthList = FXCollections.observableArrayList();

    /**
     * Navigates to the main screen. Calls the navigation method.
     * @param event When the user clicks the back button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML // Back to main screen button
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    /**
     * Navigates to the user schedule report.
     * @param event When the user clicks the user report button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML
    void goToUserSchedule(ActionEvent event) throws IOException {
        Navigation.toUserScheduleReport(event);
    }

    /**
     * Navigates to the total appointments screen and sorts the appointments into the appropriate lists to give a count for each list.
     * @param event When the user clicks the total report button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML //Total number of customer appointments by type and month
    void showTotalAppointments(ActionEvent event) throws IOException {

        for(Object o : allAppointmentsList){
            Appointment a = (Appointment) o;
            String type = a.getType();
            if(type.equals("Planning Session")){
                planningSessionAppointments.add(a);
            } else if(type.equals("De-Briefing")){
                deBriefingAppointments.add(a);
            } else if(type.equals("Lunch")){
                lunchAppointments.add(a);
            } else {
                System.out.println("Type does not exist: " + type);
            }
            Month month = a.getZdtStart().getMonth();

                if(month.equals(Month.JANUARY)){
                    janMonthList.add(a);
                } else if(month.equals(Month.FEBRUARY)){
                    febMonthList.add(a);
                } else if(month.equals(Month.APRIL)){
                    aprMonthList.add(a);
                } else if(month.equals(Month.MAY)){
                    mayMonthList.add(a);
                } else if(month.equals(Month.JUNE)){
                    junMonthList.add(a);
                } else if(month.equals(Month.JULY)){
                    julMonthList.add(a);
                } else if(month.equals(Month.AUGUST)){
                    augMonthList.add(a);
                } else if(month.equals(Month.SEPTEMBER)){
                    sepMonthList.add(a);
                } else if(month.equals(Month.OCTOBER)){
                    octMonthList.add(a);
                } else if(month.equals(Month.NOVEMBER)){
                    novMonthList.add(a);
                } else if(month.equals(Month.DECEMBER)){
                    decMonthList.add(a);
                } else if(month.equals(Month.MARCH)){
                    marMonthList.add(a);
                }

        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TotalAppointmentReport.fxml"));
        loader.load();
        totalAppointmentReportController = loader.getController();
        totalAppointmentReportController.setDeBriefingQuantity((int) deBriefingAppointments.stream().count());
        totalAppointmentReportController.setLunchQuantity((int) lunchAppointments.stream().count());
        totalAppointmentReportController.setPlanningQuantity((int) planningSessionAppointments.stream().count());

        totalAppointmentReportController.setMonth0Qty((int) janMonthList.stream().count());
        totalAppointmentReportController.setMonth1Qty((int) febMonthList.stream().count());
        totalAppointmentReportController.setMonth2Qty((int) marMonthList.stream().count());
        totalAppointmentReportController.setMonth3Qty((int) aprMonthList.stream().count());
        totalAppointmentReportController.setMonth4Qty((int) mayMonthList.stream().count());
        totalAppointmentReportController.setMonth5Qty((int) junMonthList.stream().count());
        totalAppointmentReportController.setMonth6Qty((int) julMonthList.stream().count());
        totalAppointmentReportController.setMonth7Qty((int) augMonthList.stream().count());
        totalAppointmentReportController.setMonth8Qty((int) sepMonthList.stream().count());
        totalAppointmentReportController.setMonth9Qty((int) octMonthList.stream().count());
        totalAppointmentReportController.setMonth10Qty((int) novMonthList.stream().count());
        totalAppointmentReportController.setMonth11Qty((int) decMonthList.stream().count());

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Navigates to the contact schedule screen by calling the navigation method.
     * @param event When the user clicks the contact schedule button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML // Individual Contact schedule report. include appointment ID, title, type, description, start date and time, end date and time, and customer ID
    void showContactSchedule(ActionEvent event) throws IOException {
        Navigation.toContactScheduleReportScreen(event);
    }

}