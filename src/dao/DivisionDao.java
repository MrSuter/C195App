package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Division;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

public class DivisionDao {
    private static Connection conn = DBConnection.getConnnection();

    public  ObservableList<Division> selectAllDivisions(){
        ObservableList<Division> allDivisionsList = FXCollections.observableArrayList();
        String selectDivisions = "SELECT * FROM first_level_divisions;";
        try {
            DBQuery.setPreparedStatement(conn, selectDivisions);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rsDivision = ps.getResultSet();
            while(rsDivision.next()){
                int divisionID = rsDivision.getInt("Division_ID");
                String divisionName = rsDivision.getString("Division");
                Timestamp createDate = rsDivision.getTimestamp("Create_Date");
                String createdBy = rsDivision.getString("Created_By");
                Timestamp lastUpdate = rsDivision.getTimestamp("Last_Update");
                String lastUpdatedBy = rsDivision.getString("Last_Updated_By");
                int countryID = rsDivision.getInt("COUNTRY_ID");

                Division divisionObject = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);

                allDivisionsList.add(divisionObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allDivisionsList;
    }

    public ObservableList<Division> selectDivisionsInCountry(int countryIDInput){
        ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();
        String selectDivision = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
        try {
            DBQuery.setPreparedStatement(conn, selectDivision);
            PreparedStatement psDivision = DBQuery.getPreparedStatement();
            psDivision.setInt(1, countryIDInput);
            psDivision.execute();
            ResultSet rsDivision = psDivision.getResultSet();
            while(rsDivision.next()){
                int divisionID = rsDivision.getInt("Division_ID");
                String divisionName = rsDivision.getString("Division");
                Timestamp createDate = rsDivision.getTimestamp("Create_Date");
                String createdBy = rsDivision.getString("Created_By");
                Timestamp lastUpdate = rsDivision.getTimestamp("Last_Update");
                String lastUpdatedBy = rsDivision.getString("Last_Updated_By");
                int countryID = rsDivision.getInt("COUNTRY_ID");

                Division divisionObject = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);

                divisionObservableList.add(divisionObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  divisionObservableList;
    }

    public static Division selectSingleDivision(int ID){
        Division division = new Division(1, "null", Timestamp.valueOf(LocalDateTime.now()), "null", Timestamp.valueOf(LocalDateTime.now()), "Null", -1);
        try {
            String select = "Select * From first_level_divisions WHERE Division_ID = ?";
            DBQuery.setPreparedStatement(conn, select);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1, ID);
            ps.execute();

            ResultSet rs = ps.getResultSet();
//int divisionID, String division, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID)
            while(rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("Country_ID");

                division = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return division;
    }

    public void update(Customer customer){

    }

}
