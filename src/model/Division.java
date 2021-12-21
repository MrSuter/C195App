package model;

import javafx.scene.control.SingleSelectionModel;

import java.sql.Timestamp;

/**
 * Model of the division objects.
 */
public class Division { //extends SingleSelectionModel<Division> {
    private int divisionID;
    private String division;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * Returns the name of the division when the toString method is called.
     * @return String of the name of the division.
     */
    @Override
    public String toString(){
        return(division);
    }

    /**
     * Constructor for the division objects.
     * @param divisionID Integer of the division ID
     * @param division String of the division name.
     * @param createDate Timestamp of the creation of the division.
     * @param createdBy String of the user who created the division.
     * @param lastUpdate Timestamp of the last update of the division.
     * @param lastUpdatedBy String of the name of the user who last updated the division.
     * @param countryID Integer of the ID of the country where the division resides.
     */
    public Division(int divisionID, String division, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * Returns the ID of the division.
     * @return Integer of the ID of the division.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the ID of the division.
     * @param divisionID Integer of the ID of the division.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Returns the name of the division.
     * @return String of the name of the division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the name of the division.
     * @param division String of the name of the division.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Returns the date and time of the creation of the division.
     * @return Timestamp of the date and time of the creation of the division.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date and time of the creation of the division.
     * @param createDate Timestamp of the date and time of the creation of the division.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Returns the name of the user who created the division.
     * @return String of the name of the user who created the division.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the user who created the division.
     * @param createdBy String of the name of the user who created the division.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the time and date of the last update of the division.
     * @return Timestamp of the time and date of the last update of the division.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the time and date of the last update of the division.
     * @param lastUpdate Timestamp of the time and date of the last update of the division.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the name of the user who last updated the division.
     * @return String of the name of the user who last updated the division.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the name of the user who last updated the division.
     * @param lastUpdatedBy String of the name of the user who last updated the division.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the ID of the country where the division resides.
     * @return Integer of the ID of the country where the division resides.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the ID of the country where the division resides.
     * @param countryID Integer of the ID of the country where the division resides.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
