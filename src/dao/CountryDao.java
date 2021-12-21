package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import model.Country;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Dao for the country database operations.
 */
public class CountryDao {
    private Connection conn = DBConnection.getConnnection();

    /**
     * Returns the list of country records from the database.
     * @return List of counrty objects from the database.
     */
    public ObservableList<Country> selectCountries(){
        String selectStatement = "SELECT * FROM countries";
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList();

        try{
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement psCountry = DBQuery.getPreparedStatement();
            psCountry.execute();
            ResultSet rsCountry = psCountry.getResultSet();
            while(rsCountry.next()){
                int countryID = rsCountry.getInt("Country_ID");
                String countryName = rsCountry.getString("Country");
                Timestamp createDate = rsCountry.getTimestamp("Create_Date");
                String createdBy = rsCountry.getString("Created_By");
                Timestamp lastUpdate = rsCountry.getTimestamp("Last_Update");
                String lastUpdatedBy = rsCountry.getString("Last_Updated_By");

                Country country = new Country(countryID, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);
                countryObservableList.add(country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

        return countryObservableList;
    }

    /**
     * Returns a single country record from the database.
     * @param ID Integer ID of the country to be retrieved.
     * @return Country object from the database.
     */
    public Country selectSingleCountry(int ID){
        Country country = new Country(-1, "Amelia", Timestamp.valueOf(LocalDateTime.now()), "Josephine", Timestamp.valueOf(LocalDateTime.now()), "Anita");
        String select = "SELECT * FROM countries WHERE Country_ID = ?;";
        try {
            DBQuery.setPreparedStatement(conn, select);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1, ID);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                country = new Country(countryID, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return country;
    }

}
