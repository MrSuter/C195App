package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AppointmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import org.w3c.dom.ls.LSOutput;
import utils.DBConnection;
import utils.DBQuery;

public class LoginFormController {
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private User user = new User(0,"username", "password", timestamp, "me", timestamp, "me");
    private Navigation navigation = new Navigation();
    private String frenchNoUser = "Le nom d'utilisateur n'existe pas";
    private String frenchWrongLogin = "ID utilisateur ou mot de passe incorrect";
    private String displayLanguage;


    @FXML
    private Label userIDLbl;

    @FXML
    private Label passwordLbl;

    @FXML
    private Label titleLbl;

    @FXML // fx:id="userIDTxt"
    private TextField userIDTxt; // Value injected by FXMLLoader

    @FXML // fx:id="passwordPass"
    private PasswordField passwordPass; // Value injected by FXMLLoader

    @FXML
    private Label locationLbl;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        Locale currentLocale = Locale.getDefault();
        ZoneId localZone = ZoneId.systemDefault();
        displayLanguage = currentLocale.getDisplayLanguage();

        locationLbl.setText(localZone.toString());

        if(displayLanguage == "French"){
            passwordLbl.setText("le mot de passe");
            userIDLbl.setText("Nom d'utilisateur");
            titleLbl.setText("Planification de la connexion à l'application");
        }

        //System.out.println(currentLocale.getCountry() + " | " + localZone + " | " + displayLanguage);


    }

    @FXML //Exit program button
    public void closeProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML //Login button
    public void goToMainScreen(ActionEvent event) throws IOException, SQLException {
        Connection conn = DBConnection.getConnnection(); //Connect to database

        String selectStatement = "SELECT * FROM client_schedule.users WHERE User_Name = ?";

        DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, userIDTxt.getText());

        //Execute SQL Statement
        ps.execute();

        //Create result set
        ResultSet rs = ps.getResultSet();

        //Check if username is in database
        if (rs.next() == false) {
            if(displayLanguage == "French"){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, frenchNoUser);
                Optional<ButtonType> result = alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Username does not exist");
                Optional<ButtonType> result = alert.showAndWait();
            }
        } else {
            //Get username and password from database
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String realPassword = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            String inputPassword = passwordPass.getText();

            //Check if input password matches user's password
            if (inputPassword.equals(realPassword)) {
                user = new User(userID, userName, realPassword, createDate, createdBy, lastUpdate, lastUpdatedBy);
                user.setCurrentUser(user);
                navigation.toMainScreen(event);
            } else {
                if(displayLanguage == "French"){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, frenchWrongLogin);
                    Optional<ButtonType> result = alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Incorrect userID or password");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
        }


    }

}

















