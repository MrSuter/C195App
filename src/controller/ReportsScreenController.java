package controller;

import dao.AppointmentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Appointment;
import java.io.IOException;
import java.time.Month;

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
     * LAMDBA EXPRESSION: the forEach lambda is easy to read, write, and utilizes multi-core CPUs.
     * @param event When the user clicks the total report button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML //Total number of customer appointments by type and month
    void showTotalAppointments(ActionEvent event) throws IOException {
        allAppointmentsList.forEach(o -> {
            Appointment a = (Appointment) o;
            String type = a.getType();
            switch (type) {
                case "Planning Session":
                    planningSessionAppointments.add(a);
                    break;
                case "De-Briefing":
                    deBriefingAppointments.add(a);
                    break;
                case "Lunch":
                    lunchAppointments.add(a);
                    break;
                default:
                    System.out.println("Type does not exist: " + type);
                    break;
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
        });

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TotalAppointmentReport.fxml"));
        loader.load();
        totalAppointmentReportController = loader.getController();
        totalAppointmentReportController.setDeBriefingQuantity(deBriefingAppointments.size());
        totalAppointmentReportController.setLunchQuantity(lunchAppointments.size());
        totalAppointmentReportController.setPlanningQuantity(planningSessionAppointments.size());

        totalAppointmentReportController.setMonth0Qty(janMonthList.size());
        totalAppointmentReportController.setMonth1Qty(febMonthList.size());
        totalAppointmentReportController.setMonth2Qty(marMonthList.size());
        totalAppointmentReportController.setMonth3Qty(aprMonthList.size());
        totalAppointmentReportController.setMonth4Qty(mayMonthList.size());
        totalAppointmentReportController.setMonth5Qty(junMonthList.size());
        totalAppointmentReportController.setMonth6Qty(julMonthList.size());
        totalAppointmentReportController.setMonth7Qty(augMonthList.size());
        totalAppointmentReportController.setMonth8Qty(sepMonthList.size());
        totalAppointmentReportController.setMonth9Qty(octMonthList.size());
        totalAppointmentReportController.setMonth10Qty(novMonthList.size());
        totalAppointmentReportController.setMonth11Qty(decMonthList.size());

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