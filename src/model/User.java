package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Model for the user objects.
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private static User currentUser;

    /**
     * Returns the ID of the user and the username.
     * @return String of the user ID and username concatenated together.
     */
    @Override
    public String toString(){
        return("ID: " + userID + " " + userName);
    }

    /**
     * Returns the user object who logged into the application.
     * @return User object of the user object who logged into the application.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the user object who logged into the application.
     * @param currentUser User object of the user object who logged into the application.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Constructor for the user objects.
     * @param userID Integer of the ID of the user.
     * @param userName String of the name of the user.
     * @param password String of the password of the user.
     * @param createDate Timestamp of the creation of the user.
     * @param createdBy String of the name of the creator of the user.
     * @param lastUpdate Timestamp of the last update of the user.
     * @param lastUpdatedBy String of the name of the one to last update the user.
     */
    public User(int userID, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the ID of the user.
     * @return Integer of the ID of the user.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the ID of the user.
     * @param userID Integer of the ID of the user.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Returns the name of the user.
     * @return String of the name of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user.
     * @param userName String of the name of the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the user's password.
     * @return String of the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     * @param password String of the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the time and date of the creation of the user.
     * @return Timestamp of the time and date of the creation of the user.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Sets the time and date of the creation of the user.
     * @param createDate Timestamp of the time and date of the creation of the user.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Returns the name of the creator of the user.
     * @return String of the name of the creator of the user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the creator of the user.
     * @param createdBy String of the name of the creator of the user.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the time and date of the last update of the user.
     * @return Timestamp of the time and date of the last update of the user.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the time and date of the last update of the user.
     * @param lastUpdate Timestamp of the time and date of the last update of the user.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the name of the one who last updated the user.
     * @return String of the name of the one who last updated the user.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the name of the one who last updated the user.
     * @param lastUpdatedBy String of the name of the one who last updated the user.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


}
