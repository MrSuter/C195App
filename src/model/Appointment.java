package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Model for the appointment objects.
 */
public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private ZonedDateTime zdtStart;
    private ZonedDateTime zdtEnd;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private ZoneId localZone = ZoneId.systemDefault();
    private Customer customer;
    private User user;

    /**
     * Constructor for the appointment objects.
     * @param appointmentID Integer of the appointment ID.
     * @param title String of the appointment title.
     * @param description String of the appointment description.
     * @param location String of the appointment location.
     * @param type String of the appointment type.
     * @param start LocalDateTime of the appointment start.
     * @param end LocalDateTime of the appointment end.
     * @param createDate LocalDateTime of when the appointment was created.
     * @param createdBy String of the appointment creator.
     * @param lastUpdate LocalDateTime of when the appointment was last updated.
     * @param lastUpdatedBy String of the user who last updated the appointment.
     * @param customerID Integer of the customer ID for the appointment.
     * @param userID Integer of the user ID for the appointment.
     * @param contactID Integer of the contact ID for the appointment.
     * @param zdtStart ZonedDateTime of the start of the appointment.
     * @param zdtEnd ZonedDateTime of the end of the appointment.
     * @param customer Customer object of the appointment.
     * @param user User object of the appointment.
     */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID, ZonedDateTime zdtStart, ZonedDateTime zdtEnd, Customer customer, User user) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.zdtStart = zdtStart;
        this.zdtEnd = zdtEnd;
        this.customer = customer;
        this.user = user;
    }

    /**
     * Returns the title of the appointment as a string when the toString method is called.
     * @return String title of the appointment.
     */
    @Override
    public String toString(){
        return(title);
    }

    /**
     * Returns the user object of the appointment.
     * @return The user object of the appointment.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user object for the appointment.
     * @param user The user object for the appointment.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the customer object for the appointment.
     * @return Customer object for the appointment.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer object for the appointment.
     * @param customer Customer object for the appointment.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the appointment ID.
     * @return Integer of the appointment ID.
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets the appointment ID.
     * @param appointmentID Integer for the appointment ID.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Returns the title of the appointment.
     * @return String of the title of the appointment.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the appointment.
     * @param title String of the title of the appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the appointment.
     * @return String of the description of the appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     * @param description String of the description of the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the location of the appointment.
     * @return String of the location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment.
     * @param location String of the location of the appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the type of the appointment.
     * @return String of the type of the appointment.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     * @param type String of the type of the appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the start of the appointment.
     * @return LocalDateTime of the start of the appointment.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start time and date of the appointment.
     * @param start ZonedDateTime of the start of the appointment.
     */
    public void setZdtStart(ZonedDateTime start) {
        this.zdtStart = start;
    }

    /**
     * Sets the end time and date and zone.
     * @param end ZonedDateTime of the end of the appointment.
     */
    public void setZdtEnd(ZonedDateTime end) {
        this.zdtEnd = end;
    }

    /**
     * Returns the start time, date, and zone.
     * @return ZonedDateTime of the start of the appointment.
     */
    public ZonedDateTime getZdtStart() {
        return zdtStart;
    }

    /**
     * Returns the time, date, and zone of the end of the appointment.
     * @return ZonedDateTime of the end of the appointment.
     */
    public ZonedDateTime getZdtEnd() {
        return zdtEnd;
    }

    /**
     * Sets the start time and date of the appointment.
     * @param start LocalDateTime of the start of the appointment.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Returns the end time and date of the appointment.
     * @return LocalDateTime of the end of the appointment.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end date and time of the appointment.
     * @param end LocalDateTime of the end of the appointment.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Returns the time and date when the appointment was created.
     * @return LocalDateTime of the creation date of the appointment.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date and time when the appointment was created.
     * @param createDate LocalDateTime of the creation date of the appointment.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Returns the name of the creator of the appointment.
     * @return String of the name of the creator of the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the creator of the appointment.
     * @param createdBy String of the name of the creator of the appointment.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the time and date when the appointment was last updated.
     * @return Time and date when the appointment was last updated.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the time and date when the appointment was last updated.
     * @param lastUpdate Time and date when the appointment was last updated.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the name of the user who last updated the appointment.
     * @return String of the name of the user who last updated the appointment.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Returns the name of the user who last updated the appointment.
     * @param lastUpdatedBy String of the name of the user who last updated the appointment.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the ID of the customer for the appointment.
     * @return Integer of the ID of the customer for the appointment.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets the ID of the customer for the appointment.
     * @param customerID Integer of the ID of the customer for the appointment.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Returns the ID of the user of the appointment.
     * @return Integer ID of the user of the appointment.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the ID of the user of the appointment.
     * @param userID Integer of the ID of the user of the appointment.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Returns the ID of the contact of the appointment.
     * @return Integer of the ID of the contact of the appointment.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Sets the ID of the contact of the appointment.
     * @param contactID Integer of the ID of the contact of the appointment.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
