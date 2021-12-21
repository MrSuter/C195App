package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Customer;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Dao for the contacts SQL operations.
 */
public class ContactDao {
    private Connection conn = DBConnection.getConnnection(); //Connect to database
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    /**
     * Returns the list contact records from the database.
     * @return List of the contact objects from the database.
     */
    public ObservableList<Contact> selectContacts() {
        String selectContactStatement = "SELECT * FROM contacts";
        try {
            DBQuery.setPreparedStatement(conn, selectContactStatement);
        } catch (SQLException e){
            e.printStackTrace();
        }
        PreparedStatement ps = DBQuery.getPreparedStatement();
        try{
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try{
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact contact = new Contact(contactID, contactName, email);
                this.contacts.add(contact);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contacts;
    }

    /**
     * Returns a single contact record from the database.
     * @param ID Integer ID of the contact to be retrieved.
     * @return The contact object from the database.
     * @throws SQLException Rethrows SQLException when the query is executed.
     */
    public Contact selectSingleContact(int ID) throws SQLException {
        Contact contact = new Contact(-1,"Judah", "judah@theman.com");
        String select = "SELECT * FROM contacts WHERE Contact_ID = ?";
        DBQuery.setPreparedStatement(conn, select);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, ID);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Contact_Email");

            contact = new Contact(contactID, contactName, contactEmail);
        }
        return contact;
    }



}
