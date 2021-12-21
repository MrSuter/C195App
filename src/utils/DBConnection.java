package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

/**
 * Utility for the database connection.
 */
public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcURL = protocol + vendorName + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    //Driver and connection interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    /**
     * Returns the connection to the database.
     * @return The connection object for the database.
     */
    public static Connection getConnnection(){
        if(conn == null){
            startConnection();
        }
            return conn;
    }

    /**
     * Tries to star the connection to the database.
     */
    public static void startConnection(){
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database.
     */
    public static void closeConnection(){
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException throwables) {
            throwables.getMessage();
            throwables.printStackTrace();
        }
    }

}
