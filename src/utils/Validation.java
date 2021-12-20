package utils;

import controller.Navigation;
import dao.AppointmentDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Validation {
    private static ObservableList<Appointment> appointments= FXCollections.observableArrayList();

    public static Boolean hours(ZonedDateTime startEST, ZonedDateTime endEST){

        if(endEST.isBefore(startEST)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("End time must be after start time");
            alert.show();
        }else if(((startEST.getHour() >= 8 && startEST.getHour() <= 22) && (endEST.getHour() >= 8 && endEST.getHour() <=22) )){
            return true;
            //Appointment appointment = new Appointment(appointmentID, title, description, location, type, LocalDateTime.of(startDate, startTime), end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, user, contact, startLocal, end.atZone(ZoneId.systemDefault()), customer);
            //appointmentDao.insert(appointment);
            //Navigation.toMainScreen(event);

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Time not within operating hours.");
            alert.show();
        }

        return false;
    }

    public static boolean overlap(int customerID, int appointmentID, ZonedDateTime startEST, ZonedDateTime endEST){
        appointments = AppointmentDao.selectCustomerSchedule(customerID);
        appointments.forEach( (n) -> { System.out.println(n.getTitle()); } );
        for(Appointment a : appointments){
            //LocalDateTime oldStart = a.getStart();
            //ZonedDateTime oldStartZ = ZonedDateTime.of(oldStart, ZoneId.of("America/New_York")); //a.getZdtStart().withZoneSameInstant(ZoneId.of("America/New_York"));
            //ZonedDateTime oldEndZ = a.getZdtEnd().withZoneSameInstant(ZoneId.of("America/New_York"));
            if(a.getAppointmentID() == appointmentID){
                System.out.println("Found a match!");
            } else if(startEST.isEqual(a.getZdtStart()) || endEST.isEqual(a.getZdtEnd())){
                System.out.println("Condition 1 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
                return false;
            } else if (startEST.isBefore(a.getZdtStart()) && endEST.isAfter(a.getZdtEnd())) {
                System.out.println("Condition 2 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
                return false;
            } else if (startEST.isAfter(a.getZdtStart()) && startEST.isBefore(a.getZdtEnd())){
                System.out.println("Condition 3 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());                return false;
            } else if (endEST.isAfter(a.getZdtStart()) && endEST.isBefore(a.getZdtEnd())){
                System.out.println("Condition 4 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());                return false;
            } else if(startEST.isAfter(a.getZdtStart()) && endEST.isBefore(a.getZdtEnd())){
                System.out.println("Condition 5 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());                return false;
            } else {
                System.out.println("All conditions pass. No overlap");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());            }
        }
        return true;
    }
}
