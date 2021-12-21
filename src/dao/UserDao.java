package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Dao for the user database operations.
 */
public class UserDao {
    private static Connection conn = DBConnection.getConnnection();
    private User user;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    /**
     * Retrieves all user records from the database.
     * @return List of user objecets from the databse.
     */
    public ObservableList<User> selectAllUsers(){
        String selectAll = "SELECT * FROM users";
        try{
            DBQuery.setPreparedStatement(conn, selectAll);
        } catch (SQLException e){
            e.printStackTrace();
        }
        PreparedStatement psAllUsers = DBQuery.getPreparedStatement();
        try{
            psAllUsers.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        try{
            ResultSet rs = psAllUsers.getResultSet();
            while(rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                user = new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                userList.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Retrieves a single user from the database.
     * @param ID Integer ID of the user to be retrieved.
     * @return User object from the database.
     * @throws SQLException Rethrows SQLException when the query is executed.
     */
    public static User selectSingleUser(int ID) throws SQLException {
        User user = new User(-1, "Hello", "Password", Timestamp.valueOf(LocalDateTime.now()), "The Creator", Timestamp.valueOf(LocalDateTime.now()), "Updater");
        String select = "SELECT * FROM users WHERE User_ID = ?";
        DBQuery.setPreparedStatement(conn, select);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, ID);
        ps.execute();
        ResultSet rsUser = ps.getResultSet();
        while(rsUser.next()){
            int userID = rsUser.getInt("User_ID");
            String userName = rsUser.getString("User_Name");
            String password = rsUser.getString("Password");
            Timestamp createDate = rsUser.getTimestamp("Create_Date");
            String createdBy = rsUser.getString("Created_By");
            Timestamp lastUpdate = rsUser.getTimestamp("Last_Update");
            String lastUpdatedBy = rsUser.getString("Last_Updated_By");

            user = new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);

        }
        return user;
    }

}
