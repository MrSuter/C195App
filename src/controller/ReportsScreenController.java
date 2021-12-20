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

public class ReportsScreenController {

    private int planningQuantity;
    private int deBriefingQuantity;
    private int lunchQuantity;

    public int getPlanningQuantity() {
        return planningQuantity;
    }

    public void setPlanningQuantity(int planningQuantity) {
        this.planningQuantity = planningQuantity;
    }

    public int getDeBriefingQuantity() {
        return deBriefingQuantity;
    }

    public void setDeBriefingQuantity(int deBriefingQuantity) {
        this.deBriefingQuantity = deBriefingQuantity;
    }

    public int getLunchQuantity() {
        return lunchQuantity;
    }

    public void setLunchQuantity(int lunchQuantity) {
        this.lunchQuantity = lunchQuantity;
    }
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


    @FXML // Back to main screen button
    void goToMainScreen(ActionEvent event) throws IOException {
        Navigation.toMainScreen(event);
    }

    @FXML
    void goToUserSchedule(ActionEvent event) throws IOException {
        Navigation.toUserScheduleReport(event);
    }

    @FXML //Total number of customer appointments by type and month
    void showTotalAppointments(ActionEvent event) throws IOException {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();

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

        //long current = currentMonthList.stream().count();

        System.out.println("Planning sessions: " + planningSessionAppointments.stream().count());
        System.out.println("De-Briefing sessions: " + deBriefingAppointments.stream().count());
        System.out.println("Lunch sessions: " + lunchAppointments.stream().count());
        //System.out.print(currentMonth + " appointments "); System.out.println(current);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TotalAppointmentReport.fxml"));
        loader.load();
        totalAppointmentReportController = loader.getController();
        totalAppointmentReportController.setDeBriefingQuantity((int) deBriefingAppointments.stream().count());
        totalAppointmentReportController.setLunchQuantity((int) lunchAppointments.stream().count());
        totalAppointmentReportController.setPlanningQuantity((int) planningSessionAppointments.stream().count());
/*
        totalAppointmentReportController.setMonth0(String.valueOf(currentMonth));
        totalAppointmentReportController.setMonth1(String.valueOf(currentMonth.plus(1)));
        totalAppointmentReportController.setMonth2(String.valueOf(currentMonth.plus(2)));
        totalAppointmentReportController.setMonth3(String.valueOf(currentMonth.plus(3)));
        totalAppointmentReportController.setMonth4(String.valueOf(currentMonth.plus(4)));
        totalAppointmentReportController.setMonth5(String.valueOf(currentMonth.plus(5)));
        totalAppointmentReportController.setMonth6(String.valueOf(currentMonth.plus(6)));
        totalAppointmentReportController.setMonth7(String.valueOf(currentMonth.plus(7)));
        totalAppointmentReportController.setMonth8(String.valueOf(currentMonth.plus(8)));
        totalAppointmentReportController.setMonth9(String.valueOf(currentMonth.plus(9)));
        totalAppointmentReportController.setMonth10(String.valueOf(currentMonth.plus(10)));
        totalAppointmentReportController.setMonth11(String.valueOf(currentMonth.plus(11)));
*/
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

    @FXML // Individual Contact schedule report. include appointment ID, title, type, description, start date and time, end date and time, and customer ID
    void showContactSchedule(ActionEvent event) throws IOException {
        Navigation.toContactScheduleReportScreen(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}