package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Includes the navigation methods to load new screens.
 */
public class Navigation {
    /**
     * Navigates to the main screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/MainScreen.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the add appointment screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toAddAppointmentScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/AddAppointmentScreen.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the customers screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toCustomersScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/CustomersScreen.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the reports screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toReportsScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ReportsScreen.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the add customer screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toAddCustomerScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/AddCustomerScreen.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the login screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toLoginScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/LoginForm.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the contact schedule report screen.
     * @param event  When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toContactScheduleReportScreen(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/ContactScheduleReport.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * Navigates to the user schedule report screen.
     * @param event When the user clicks the appropriate button.
     * @throws IOException Rethrows IOException when the next screen is loaded.
     */
    public static void toUserScheduleReport(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/UserScheduleReport.fxml")));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

}
