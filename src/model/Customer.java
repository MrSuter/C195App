package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Model for the customer objects.
 */
public class Customer {
    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdateTime;
    private String lastUpdatedBy;
    private int divisionID;
    private String divisionName;

    /**
     * Constructor for the customer objects.
     * @param customerID Integer for the ID of the customer.
     * @param name String of the name of the customer.
     * @param address String of the street address of the customer.
     * @param postalCode String of the postal code of the customer.
     * @param phone String of the phone number of the customer.
     * @param createDate Timestamp of the creation of the customer.
     * @param createdBy String of the name of the user who created the customer.
     * @param lastUpdateTime Timestamp of the last update of the customer.
     * @param lastUpdatedBy String of the name of the user who last updated the customer.
     * @param divisionID Integer of the ID of the division the customer lives in.
     * @param divisionName String of the name of the Division the customer lives in.
     */
    public Customer(int customerID, String name, String address, String postalCode, String phone, Timestamp createDate, String createdBy, Timestamp lastUpdateTime, String lastUpdatedBy, int divisionID, String divisionName) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateTime = lastUpdateTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * Returnst the customer ID and name of the customer when the toString method is called.
     * @return String of the customer ID concatenated with the name of the customer.
     */
    @Override
    public String toString(){
        return("ID: " + customerID + " " + name);
    }

    /**
     * Returns the ID of the customer.
     * @return Integer of the ID of the customer.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets the ID of the customer.
     * @param customerID Integer of the ID of the customer.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Returns the name of the customer.
     * @return String of the name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     * @param name String of the name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the street address of the customer.
     * @return String of the street address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the street address of the customer.
     * @param address String of the street address of the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the postal code of the customer.
     * @return String of the postal code of the customer.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the customer.
     * @param postalCode String of the postal code of the customer.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the phone number of the customer.
     * @return String of the phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     * @param phone String of the phone number of the customer.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the date and time of the creation of the customer.
     * @return Timestamp of the date and time of the creation of the customer.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date and time of the creation of the customer.
     * @param createDate Timestamp of the date and time of the creation of the customer.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Returns the name of the creator of the customer.
     * @return String of the name of the creator of the customer.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the creator of the customer.
     * @param createdBy String of the name of the creator of the customer.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the date and time of the last update of the customer.
     * @return Timestamp of the date and time of the last update of the customer.
     */
    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Sets the date and time of the last update of the customer.
     * @param lastUpdateTime Timestamp of the date and time of the last update of the customer.
     */
    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Returns the name of the user who last updated the customer.
     * @return String of the name of the user who last updated the customer.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the name of the user who last updated the customer.
     * @param lastUpdatedBy String of the name of the user who last updated the customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the division ID where the customer lives.
     * @return Integer of the division ID where the customer lives.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the division ID where the customer lives.
     * @param divisionID Integer of the division ID where the customer lives.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Returns the name of the division where the customer lives.
     * @return String of the name of the division where the customer lives.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets the name of the division where the customer lives.
     * @param divisionName String of the name of the division where the customer lives.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}

