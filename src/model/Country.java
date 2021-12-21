package model;

import java.sql.Timestamp;

/**
 * Model for the country objects.
 */
public class Country {
    private int countryID;
    private String countryName;
    private Timestamp CreateDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    /**
     * Returns the name of the country when the toString method is called.
     * @return String name of the country.
     */
    @Override
    public String toString(){
        return(countryName);
    }

    /**
     * Constructor for the country objects.
     * @param countryID Integer of the ID of the country.
     * @param countryName String of the name of the country.
     * @param createDate Timestamp of the creation of the country.
     * @param createdBy String of the creator of the country.
     * @param lastUpdate Timestamp of the last update of the country.
     * @param lastUpdatedBy String of the last user to update the country.
     */
    public Country(int countryID, String countryName, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        CreateDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the ID of the country.
     * @return Integer of the ID of the country.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the ID of the country.
     * @param countryID Integer of the ID of the country.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Returns the name of the country.
     * @return String of the name of the country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the name of the country.
     * @param countryName String of the name of the country.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Returns the date and time of the creation of the country.
     * @return Timestamp of the date and time of the creation of the country.
     */
    public Timestamp getCreateDate() {
        return CreateDate;
    }

    /**
     * Sets the date and time of the creation of the country.
     * @param createDate Timestamp of the date and time of the creation of the country.
     */
    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }

    /**
     * Returns the name of the creator of the country.
     * @return String of the name of the creator of the country.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the creator of the country.
     * @param createdBy String of the name of the creator of the country.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the time and date of the last update of the country.
     * @return Timestamp of the time and date of the last update of the country.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the time and date of the last update of the country.
     * @param lastUpdate Timestamp of the time and date of the last update of the country.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the name of the user who last updated the country.
     * @return String of the name of the user who last updated the country.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the name of the user who last updated the country.
     * @param lastUpdatedBy String of the name of the user who last updated the country.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
