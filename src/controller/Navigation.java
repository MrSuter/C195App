package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Navigation {

    public static void toMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toAddAppointmentScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/AddAppointmentScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toCustomersScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/CustomersScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toReportsScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/ReportsScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toAddCustomerScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/AddCustomerScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toEditAppointmentScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/EditAppointmentScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toEditCustomerScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/EditCustomerScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toLoginScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/LoginForm.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toContactScheduleReportScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/ContactScheduleReport.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static void toUserScheduleReport(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/UserScheduleReport.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    public static LocalDateTime utcTimeToLocal(LocalDateTime time){
        //LocalDateTime start = a.getStart();
        ZonedDateTime timeUTC = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime timeSysDef = timeUTC.withZoneSameInstant(ZoneId.systemDefault());
        time = timeSysDef.toLocalDateTime();

        return time;
    }

    public static LocalDateTime localTimeToUTC(LocalDateTime time){
        ZonedDateTime timeSysDef = time.atZone(ZoneId.systemDefault());
        ZonedDateTime timeUTC = timeSysDef.withZoneSameInstant(ZoneId.of("UTC"));
        time = timeUTC.toLocalDateTime();

        return time;
    }

    public static void toTotalAppointmentReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Navigation.class.getResource("/view/TotalAppointmentReport.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }


}
