package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.User;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.*;

/**
 * Dao for the appointments in the database.
 */
public class AppointmentDao implements ScheduleAppDAO{

    private static final Connection conn = DBConnection.getConnnection();
    private static final ObservableList<Appointment> appointments= FXCollections.observableArrayList();
    private static final ObservableList<Object> objects= FXCollections.observableArrayList();
    private Object object;
    private String title;
    private String description;
    private String location;
    private int contact;
    private String type;
    private int customer;
    private int userID;
    private String createdBy;
    private String lastUpdatedBy;
    private Timestamp createDate;
    private Timestamp lastUpdate;
    private String userName = "me";
    private int appointmentID;
    private User user;


    /**
     * Updates the appointment in the database.
     * @param o The object to be updated.
     */
    public void update(Object o){
        try {
            String updateStatement = "UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?, Last_Update = now(), Last_Updated_By = ? WHERE Appointment_ID = ?";
            Appointment appointment = (Appointment) o;
            title = appointment.getTitle();
            description = appointment.getDescription();
            location = appointment.getLocation();
            type = appointment.getType();
            contact = appointment.getContactID();
            createdBy = appointment.getCreatedBy();
            lastUpdatedBy = User.getCurrentUser().getUserName();
            LocalDateTime start;
            LocalDateTime end;
            ZonedDateTime startEST = appointment.getZdtStart();
            start = startEST.toLocalDateTime();
            ZonedDateTime endEST = appointment.getZdtEnd();
            end = endEST.toLocalDateTime();

            customer = appointment.getCustomerID();
            userID = appointment.getUserID();
            appointmentID = appointment.getAppointmentID();

            PreparedStatement ps;
            DBQuery.setPreparedStatement(conn, updateStatement); //Create prepared statement
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customer);
            ps.setInt(8, userID);
            ps.setInt(9, contact);
            ps.setString(10, lastUpdatedBy);
            ps.setInt(11, appointmentID);

            //Execute SQL Statement
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Returns an appointment record from the database.
     * @param i Integer ID of the record to be retrieved.
     * @return The object of the appointment to be retrieved.
     */
    public Object getRecord(int i){
        return object;
    }

    /**
     * Returns a list of all of the appointments in the database.
     * @return The list of appointments in the database.
     */
    public ObservableList<Object> getAllRecords() {
        appointments.clear();
        objects.clear();
        String selectStatement = "SELECT * FROM client_schedule.appointments";

        try {
            DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement ps = DBQuery.getPreparedStatement();

        //Execute SQL Statement
        try {
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = ps.getResultSet();

            while (rs.next()) { //run through appointments from database and add each to the observable list
                ZoneId zoneId = ZoneId.systemDefault();
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                ZonedDateTime zdtStart = start.atZone(zoneId);
                ZonedDateTime zdtEnd = end.atZone(zoneId);
                Customer customer = CustomerDao.selectSingleCustomer(customerID);
                User user = UserDao.selectSingleUser(userID);

                Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID, zdtStart, zdtEnd, customer, user);

                appointments.add(appointment); //add appointments in observable list
                objects.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return objects;
    }

    /**
     * Removes an appointment record from the database.
     * @param ID Integer ID of the appointment to be removed.
     * @throws SQLException Rethrows SQLException when the delete query is executed.
     */
    public void delete(int ID) throws SQLException {
        String deleteStatement = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(conn, deleteStatement); //Create prepared statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, ID);
        ps.execute();
    }

    /**
     * Inserts an appointment record into the database.
     * @param o The object of the appointment to be inserted into the database.
     * @throws SQLException Rethrows SQLException when the insert query is executed.
     */
    public void insert(Object o) throws SQLException { //String title, String description, String location, int contact, String type, Timestamp start, Timestamp end, int customer, int user) throws SQLException {
        Appointment appointment = (Appointment) o;
        title = appointment.getTitle();
        description = appointment.getDescription();
        location = appointment.getLocation();
        contact = appointment.getContactID();
        type = appointment.getType();
        LocalDateTime start;
        LocalDateTime end;
        ZonedDateTime startEST = appointment.getZdtStart();
        start = startEST.toLocalDateTime();
        ZonedDateTime endEST = appointment.getZdtEnd();
        end = endEST.toLocalDateTime();

        customer = appointment.getCustomerID();
        userID = appointment.getUserID();
        String insertStatement = "INSERT INTO client_schedule.appointments (Title, Description, Location, Contact_ID, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps;
        DBQuery.setPreparedStatement(conn, insertStatement); //Create prepared statement
            ps = DBQuery.getPreparedStatement();
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setInt(4, contact);
            ps.setString(5, type);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setTimestamp(8, createDate = new Timestamp(System.currentTimeMillis()));
            ps.setString(9, User.getCurrentUser().getUserName()); //createdBy
            ps.setTimestamp(10, lastUpdate = new Timestamp(System.currentTimeMillis()));//lastUpdate
            ps.setString(11, User.getCurrentUser().getUserName()); //lastUpdatedBy
            ps.setInt(12, customer);
            ps.setInt(13, userID);

            //Execute SQL Statement
            ps.execute();
    }

    /**
     * Returns the list of appointments for a particular contact.
     * @param contact Integer ID of the contact to get the appointments for.
     * @return List of appointments that belong to the contact.
     */
    public ObservableList<Appointment> selectContactSchedule(int contact){
        appointments.clear();
        String selectStatement = "SELECT * FROM client_schedule.appointments WHERE Contact_ID = ?";

        try {
            DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement ps = DBQuery.getPreparedStatement();

        try {
            ps.setInt(1, contact);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = ps.getResultSet();

            while (rs.next()) { //run through appointments from database and add each to the observable list
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                ZonedDateTime zdtStart = start.atZone(ZoneId.of("UTC"));
                ZonedDateTime zdtEnd = end.atZone(ZoneId.of("UTC"));
                Customer customer = CustomerDao.selectSingleCustomer(customerID);
                User user = UserDao.selectSingleUser(userID);

                Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID, zdtStart, zdtEnd, customer, user);

                appointments.add(appointment); //add appointments in observable list
                objects.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    /**
     * Returns the list of appointments for a particular customer.
     * @param customer Integer ID of the customer to get the appointments for.
     * @return List of appointments that belong to the customer.
     */
    public static ObservableList<Appointment> selectCustomerSchedule(int customer){
        appointments.clear();
        String selectStatement = "SELECT * FROM client_schedule.appointments WHERE Customer_ID = ?";

        try{
            DBQuery.setPreparedStatement(conn, selectStatement);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        PreparedStatement ps =DBQuery.getPreparedStatement();
        try{
            ps.setInt(1, customer);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try{
            ResultSet rs =ps.getResultSet();

            while (rs.next()) { //run through appointments from database and add each to the observable list
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                ZonedDateTime zdtStart = start.atZone(ZoneId.of("America/New_York"));
                ZonedDateTime zdtEnd = end.atZone(ZoneId.of("America/New_York"));
                Customer customerObj = CustomerDao.selectSingleCustomer(customerID);
                User user = UserDao.selectSingleUser(userID);

                Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID, zdtStart, zdtEnd, customerObj, user);

                appointments.add(appointment); //add appointments in observable list
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    /**
     * Returns the list of appointments for a particular user.
     * @param user Integer ID of the user to get the appointments for.
     * @return List of appointments that belong to the user.
     */
    public ObservableList<Appointment> selectUserSchedule(int user){
        appointments.clear();
        String selectStatement = "SELECT * FROM client_schedule.appointments WHERE User_ID = ?";

        try {
            DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PreparedStatement ps = DBQuery.getPreparedStatement();

        try {
            ps.setInt(1, user);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = ps.getResultSet();

            while (rs.next()) { //run through appointments from database and add each to the observable list
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                ZonedDateTime zdtStart = start.atZone(ZoneId.of("UTC"));
                ZonedDateTime zdtEnd = end.atZone(ZoneId.of("UTC"));
                Customer customer = CustomerDao.selectSingleCustomer(customerID);
                User userObj = UserDao.selectSingleUser(userID);

                Appointment appointment = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID, zdtStart, zdtEnd, customer, userObj);

                appointments.add(appointment); //add appointments in observable list
                objects.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }


}
