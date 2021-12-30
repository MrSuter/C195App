package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import utils.DBConnection;
import utils.DBQuery;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

/**
 * The controller for the login screen.
 */
public class LoginFormController {
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private User user = new User(0,"username", "password", timestamp, "me", timestamp, "me");
    private String frenchNoUser = "Le nom d'utilisateur n'existe pas";
    private String frenchWrongLogin = "ID utilisateur ou mot de passe incorrect";
    private String displayLanguage;
    private String inputUserName;
    private String inputPassword;


    public String getInputUserName() {
        return inputUserName;
    }

    public void setInputUserName(String inputUserName) {
        this.inputUserName = inputUserName;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

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

    /**
     * Sets the current location and time zone. The location determines what language to display
     */
    @FXML
    void initialize() {
        Locale currentLocale = Locale.getDefault();
        ZoneId localZone = ZoneId.systemDefault();
        displayLanguage = currentLocale.getDisplayLanguage();
        System.out.println("locale: " + currentLocale);
        System.out.println("Locale.getLanguage: " + currentLocale.getLanguage());
        System.out.println("Locale.getDisplayLanguage: " + currentLocale.getDisplayLanguage());

        locationLbl.setText(localZone.toString());

        if(displayLanguage.equals("French")){
            passwordLbl.setText("le mot de passe");
            userIDLbl.setText("Nom d'utilisateur");
            titleLbl.setText("Planification de la connexion à l'application");
        }

    }

    /**
     * Closes the program.
     */
    @FXML //Exit program button
    public void closeProgram() {
        System.exit(0);
    }

    /**
     * Logs the user into the program and opens the main screen. Displays error message if incorrect username or password.
     * @param event The user clicking the login button.
     * @throws IOException Rethrows IOException when loading the next screen.
     * @throws SQLException Rethrows SQLException when executing the SQL query.
     */
    @FXML //Login button
    public void goToMainScreen(ActionEvent event) throws IOException, SQLException {
        Connection conn = DBConnection.getConnnection(); //Connect to database
        setInputUserName(userIDTxt.getText());
        setInputPassword(passwordPass.getText());

        String selectStatement = "SELECT * FROM client_schedule.users WHERE User_Name = ?";

        DBQuery.setPreparedStatement(conn, selectStatement); //Create prepared statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, inputUserName);

        //Execute SQL Statement
        ps.execute();

        //Create result set
        ResultSet rs = ps.getResultSet();

        //Check if username is in database
        if (!rs.next()) {
            Alert alert;
            if(displayLanguage.equals("French")){
                alert = new Alert(Alert.AlertType.CONFIRMATION, frenchNoUser);
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "Username does not exist");
            }
            alert.showAndWait();
        } else {
            //Get username and password from database
            int userID = rs.getInt("User_ID");
            String realUserName = rs.getString("User_Name");
            String realPassword = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String createdBy = rs.getString("Created_By");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            //String inputPassword = passwordPass.getText();

            //Check if input password matches user's password
            if (inputPassword.equals(realPassword)) {
                user = new User(userID, realUserName, realPassword, createDate, createdBy, lastUpdate, lastUpdatedBy);
                user.setCurrentUser(user);
                LoginTracker("successful");
                Navigation.toMainScreen(event);
            } else {
                LoginTracker("failure");
                Alert alert;
                if(displayLanguage.equals("French")){
                    alert = new Alert(Alert.AlertType.CONFIRMATION, frenchWrongLogin);
                }else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Incorrect userID or password");
                }
                alert.showAndWait();
            }
        }


    }

    private void LoginTracker (String success) throws IOException {
        String fileName = "login_activity.txt", loginAttempt;
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        outputFile.println(inputUserName + ", " + inputPassword + ", " + timestamp + ", " + success);
        outputFile.close();

        /*
        //filename and item variables
        String fileName = "groceries.txt", item;

        //Create filewriter object
        FileWriter fileWriter = new FileWriter(fileName, true);

        //Create and open file
        PrintWriter outputFile = new PrintWriter(fileWriter);

        //Get items and write to file
        for(int i = 0; i < numItems; i++){
            System.out.print("Enter item " + (i+1));
            item = keyboard.nextLine();
            outputFile.println(item);
        }

        //Close file
        outputFile.close();
        System.out.println("File written. ");
         */
    }

}

















