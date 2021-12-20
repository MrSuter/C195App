import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        primaryStage.setTitle("Schedule App");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {

        //GeneralInterface square = n -> n * n ;
        //System.out.println(square.calculateSquare(5));
        //GeneralInterface message = s -> "Hello " + s;
        //System.out.println(message.getMessage("Jason"));

        //void lambda expression
        //GeneralInterface message = s -> System.out.println("Hello again " + s);
        //message.displayMessage("Jason");

        // multiple parameter lambda
        //GeneralInterface sum = (n1, n2) -> n1 + n2;
        //System.out.println(sum.calculation(5, 10));

        // no parameter lambda
        //GeneralInterface message = ()-> System.out.println("Hello Jason");
        //message.displayMessage();

        // multiple statement lambda
        //GeneralInterface square = n -> {
        //    int result = n * n;
        //    return result;
        //};
        //System.out.println(square.calculateSquare(6));

        // using local variable in lambda
        //final int num = 50;

        //GeneralInterface square = n -> n * n;
        //System.out.println(square.calculateSquare(num));

/*
        Connection conn = DBConnection.startConnection(); //Connect to database
        //String insertStatement = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Update) VALUES (?,?,?,?)";
        //String updateStatement = "UPDATE countries SET Country = ?, Created_By = ? WHERE Country= ?";
        //String deleteStatement = "DELETE FROM countries WHERE Country = ?";
        String selectStatement = "SELECT * FROM users";
        DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
 */


/*
        String countryName, newCountry, createdBy;
        String createDate = "2021-7-20 00:00:00";
        //String createdBy = "THE Man";
        String lastUpdate = "2021-7-20";

        //Get Keyboard input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a country to delete: ");
        countryName = keyboard.nextLine();
*/
/*
        System.out.print("Enter new country: ");
        newCountry = keyboard.nextLine();

        System.out.print("Enter user: ");
        createdBy = keyboard.nextLine();
*/
/*
        //Key-value mapping
        ps.setString(1, countryName);
        //ps.setString(1, newCountry);
        //ps.setString(2, createdBy);
        //ps.setString(4, lastUpdate);
 */

        /*
 //Execute SQL Statement
       ps.execute();

        //Check rows effected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " rows effected.");
        else
            System.out.println("No change");


        ResultSet rs = ps.getResultSet();

        // Forward Scroll ResultSet
        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            LocalDate date = rs.getDate("Create_Date").toLocalDate();
            LocalTime time = rs.getTime("Create_Date").toLocalTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();

            //Display Record
            System.out.println(userID + " | " + userName +password + " | " + date + " " + time + " | " + createdBy + " | " + lastUpdate);
        }
         */


        launch(args);
        DBConnection.closeConnection();

    }

}
