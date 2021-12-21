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

/**
 * Utility for validating the appointments when they are added to the database.
 */
public class Validation {
    private static ObservableList<Appointment> appointments= FXCollections.observableArrayList();

    /**
     * Validates that the hours of the appointment are within operating hours.
     * @param startEST ZonedDateTime of the start of the appointment.
     * @param endEST ZonedDateTime of the end of the appointment.
     * @return Boolean true if the appointment is within the operating hours, otherwise false.
     */
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

    /**
     * Validates that there are no overlaps for the customer in the appointment to be added.
     * @param customerID Integer of the customer ID.
     * @param appointmentID Integer of the appointment ID.
     * @param startEST ZonedDateTime of the start of the appointment.
     * @param endEST ZonedDateTime of the end of the appointment.
     * @return Boolean false if any of the overlap checks are true, otherwise true if none of the overlaps are true.
     */
    public static boolean overlap(int customerID, int appointmentID, ZonedDateTime startEST, ZonedDateTime endEST){
        appointments = AppointmentDao.selectCustomerSchedule(customerID);
        appointments.forEach( (n) -> { System.out.println(n.getTitle()); } );
        for(Appointment a : appointments){
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
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
                return false;
            } else if (endEST.isAfter(a.getZdtStart()) && endEST.isBefore(a.getZdtEnd())){
                System.out.println("Condition 4 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
                return false;
            } else if(startEST.isAfter(a.getZdtStart()) && endEST.isBefore(a.getZdtEnd())){
                System.out.println("Condition 5 Fail");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
                return false;
            } else {
                System.out.println("All conditions pass. No overlap");
                System.out.println("New start: " + startEST + " end: " + endEST + " Appt ID: " + appointmentID);
                System.out.println("Old start: " + a.getZdtStart() + " end: " + a.getZdtEnd() + " Appt ID: " + a.getAppointmentID());
            }
        }
        return true;
    }
}
