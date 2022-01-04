package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import utils.DBConnection;
import utils.DBQuery;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.ZoneId;
import java.util.Locale;

/**
 * The controller for the login screen.
 */
public class LoginFormController extends Component {
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private String displayLanguage;
    private String inputUserName;
    private String inputPassword;

/*
  public String getInputUserName() {
        return inputUserName;
    }
    public String getInputPassword() {
        return inputPassword;
    }

 */

    /**
     * Sets the input username variable from the text input in the username text field.
     * @param inputUserName The text from the username text field.
     */
    public void setInputUserName(String inputUserName) {
        this.inputUserName = inputUserName;
    }

    /**
     * Sets the input password variable from the text input in the password field.
     * @param inputPassword The text from the password text field.
     */
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

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

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

        if(displayLanguage.equals("français")){
            passwordLbl.setText("le mot de passe");
            userIDLbl.setText("Nom d'utilisateur");
            titleLbl.setText("Planification de la connexion à l'application");
            loginButton.setText("connexion");
            exitButton.setText("sortir");
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
            //Alert alert;
            if(displayLanguage.equals("français")){
                //String frenchNoUser = "Le nom d'utilisateur n'existe pas";
                //alert = new Alert(Alert.AlertType.CONFIRMATION, frenchNoUser);

                Object[] choices = {"d'accord"};
                Object defaultChoice = choices[0];
                String frenchNoUser = "Le nom d'utilisateur n'existe pas";
                JOptionPane.showOptionDialog(this,
                        frenchNoUser,
                        "avertissement",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        choices,
                        defaultChoice);
            }else {
                LoginTracker("failure");
                //alert = new Alert(Alert.AlertType.CONFIRMATION, "Username does not exist");
                Object[] choices = {"OK"};
                Object defaultChoice = choices[0];
                JOptionPane.showOptionDialog(this,
                        "Username does not exist",
                        "Warning",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        choices,
                        defaultChoice);
            }
            //alert.showAndWait();
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
                User user = new User(userID, realUserName, realPassword, createDate, createdBy, lastUpdate, lastUpdatedBy);
                user.setCurrentUser(user);
                LoginTracker("successful");
                Navigation.toMainScreen(event);
            } else {
                LoginTracker("failure");
                //Alert alert;
                if(displayLanguage.equals("français")){
                    //String frenchWrongLogin = "ID utilisateur ou mot de passe incorrect";
                    //alert = new Alert(Alert.AlertType.CONFIRMATION, frenchWrongLogin);
                    Object[] choices = {"d'accord"};
                    Object defaultChoice = choices[0];
                    String frenchWrongLogin = "ID utilisateur ou mot de passe incorrect";
                    JOptionPane.showOptionDialog(this,
                            frenchWrongLogin,
                            "avertissement",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            choices,
                            defaultChoice);

                }else {
                    //alert = new Alert(Alert.AlertType.CONFIRMATION, "Incorrect userID or password");
                    Object[] choices = {"OK"};
                    Object defaultChoice = choices[0];
                    JOptionPane.showOptionDialog(this,
                            "Incorrect userID or password",
                            "Warning",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            choices,
                            defaultChoice);
                }
                //alert.showAndWait();
            }
        }


    }

    private void LoginTracker (String success) throws IOException {
        String fileName = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        outputFile.println(inputUserName + ", " + inputPassword + ", " + timestamp + ", " + success);
        outputFile.close();

    }

}

















