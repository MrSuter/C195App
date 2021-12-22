package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the total appointment report screen.
 */
public class TotalAppointmentReportController {

    private int planningQuantity;
    private int deBriefingQuantity;
    private int lunchQuantity;
    private int month0Qty;
    private int month1Qty;
    private int month2Qty;
    private int month3Qty;
    private int month4Qty;
    private int month5Qty;
    private int month6Qty;
    private int month7Qty;
    private int month8Qty;
    private int month9Qty;
    private int month10Qty;
    private int month11Qty;
    private String month0 = "January";
    private String month1 = "February";
    private String month2 = "March";
    private String month3 = "April";
    private String month4 = "May";
    private String month5 = "June";
    private String month6 = "July";
    private String month7 = "August";
    private String month8 = "September";
    private String month9 = "October";
    private String month10 = "November";
    private String month11 = "December";

    //public int getMonth0Qty() {        return month0Qty;    }

    /**
     * Set the number of appointments in January.
     * @param month0Qty Integer of the number of appointments in January.
     */
    public void setMonth0Qty(int month0Qty) {
        this.month0Qty = month0Qty;
        month0QtyLbl.setText(String.valueOf(month0Qty));
    }

    //public int getMonth1Qty() {        return month1Qty;    }

    /**
     * Set the number of appointments in February.
     * @param month1Qty Integer of the number of appointments in February.
     */
    public void setMonth1Qty(int month1Qty) {
        this.month1Qty = month1Qty;
        month1QtyLbl.setText(String.valueOf(month1Qty));
    }

    //public int getMonth2Qty() {        return month2Qty;    }

    /**
     * Set the number of appointments in March.
     * @param month2Qty Integer of the number of appointments in March.
     */
    public void setMonth2Qty(int month2Qty) {
        this.month2Qty = month2Qty;
        month2QtyLbl.setText(String.valueOf(month2Qty));
    }

    //public int getMonth3Qty() {        return month3Qty;    }

    /**
     * Set the number of appointments in April.
     * @param month3Qty Integer of the number of appointments in April.
     */
    public void setMonth3Qty(int month3Qty) {
        this.month3Qty = month3Qty;
        month3QtyLbl.setText(String.valueOf(month3Qty));
    }

    //public int getMonth4Qty() {        return month4Qty;    }

    /**
     * Set the number of appointments in May.
     * @param month4Qty Integer of the number of appointments in May.
     */
    public void setMonth4Qty(int month4Qty) {
        this.month4Qty = month4Qty;
        month4QtyLbl.setText(String.valueOf(month4Qty));
    }

    //public int getMonth5Qty() {        return month5Qty;    }

    /**
     * Set the number of appointments in June.
     * @param month5Qty Integer of the number of appointments in June.
     */
    public void setMonth5Qty(int month5Qty) {
        this.month5Qty = month5Qty;
        month5QtyLbl.setText(String.valueOf(month5Qty));
    }

    //public int getMonth6Qty() {        return month6Qty;    }

    /**
     * Set the number of appointments in July.
     * @param month6Qty Integer of the number of appointments in July.
     */
    public void setMonth6Qty(int month6Qty) {
        this.month6Qty = month6Qty;
        month6QtyLbl.setText(String.valueOf(month6Qty));
    }

    //public int getMonth7Qty() {return month7Qty;}

    /**
     * Set the number of appointments in August.
     * @param month7Qty Integer of the number of appointments in August.
     */
    public void setMonth7Qty(int month7Qty) {
        this.month7Qty = month7Qty;
        month7QtyLbl.setText(String.valueOf(month7Qty));
    }

    //public int getMonth8Qty() {   return month8Qty;  }

    /**
     * Set the number of appointments in September.
     * @param month8Qty Integer of the number of appointments in September.
     */
    public void setMonth8Qty(int month8Qty) {
        this.month8Qty = month8Qty;
        month8QtyLbl.setText(String.valueOf(month8Qty));
    }

    //public int getMonth9Qty() {        return month9Qty;    }

    /**
     * Set the number of appointments in October.
     * @param month9Qty Integer of the number of appointments in October.
     */
    public void setMonth9Qty(int month9Qty) {
        this.month9Qty = month9Qty;
        month9QtyLbl.setText(String.valueOf(month9Qty));
    }

    //public int getMonth10Qty() {        return month10Qty;    }

    /**
     * Set the number of appointments in November.
     * @param month10Qty Integer of the number of appointments in November.
     */
    public void setMonth10Qty(int month10Qty) {
        this.month10Qty = month10Qty;
        month10QtyLbl.setText(String.valueOf(month10Qty));
    }

    //public int getMonth11Qty() {        return month11Qty;    }

    /**
     * Set the number of appointments in December.
     * @param month11Qty Integer of the number of appointments in December.
     */
    public void setMonth11Qty(int month11Qty) {
        this.month11Qty = month11Qty;
        month11QtyLbl.setText(String.valueOf(month11Qty));
    }

    //public String getMonth0() {        return month0;    }

    /**
     * Sets the number of appointments that are planning appointments.
     * @param planningQuantity The number of appointments of type planning.
     */
    public void setPlanningQuantity(int planningQuantity) {
        this.planningQuantity = planningQuantity;
        planningLbl.setText(String.valueOf(planningQuantity));
    }

    //public int getDeBriefingQuantity() {        return deBriefingQuantity;    }

    /**
     * Sets the number of appointments that are debriefing appointments.
     * @param deBriefingQuantity The number of appointments of type debriefing.
     */
    public void setDeBriefingQuantity(int deBriefingQuantity) {
        this.deBriefingQuantity = deBriefingQuantity;
        deBriefingLbl.setText(String.valueOf(deBriefingQuantity));
    }

    //public int getLunchQuantity() {        return lunchQuantity;    }

    /**
     * Sets the number of appointments that are lunch appointments.
     * @param lunchQuantity The number of appointments of type lunch.
     */
    public void setLunchQuantity(int lunchQuantity) {
        this.lunchQuantity = lunchQuantity;
        lunchLbl.setText(String.valueOf(lunchQuantity));
    }

    @FXML
    private Label month1Lbl;

    @FXML
    private Label month0Lbl;

    @FXML
    private Label month0QtyLbl;

    @FXML
    private Label month2Lbl;

    @FXML
    private Label month2QtyLbl;

    @FXML
    private Label month1QtyLbl;

    @FXML
    private Label month3Lbl;

    @FXML
    private Label month3QtyLbl;

    @FXML
    private Label month4Lbl;

    @FXML
    private Label month4QtyLbl;

    @FXML
    private Label month5Lbl;

    @FXML
    private Label month5QtyLbl;

    @FXML
    private Label month6Lbl;

    @FXML
    private Label month6QtyLbl;

    @FXML
    private Label month7Lbl;

    @FXML
    private Label month7QtyLbl;

    @FXML
    private Label month8Lbl;

    @FXML
    private Label month8QtyLbl;

    @FXML
    private Label month9Lbl;

    @FXML
    private Label month9QtyLbl;

    @FXML
    private Label month10Lbl;

    @FXML
    private Label month10QtyLbl;

    @FXML
    private Label month11Lbl;

    @FXML
    private Label month11QtyLbl;

    @FXML
    private Label planningLbl;

    @FXML
    private Label deBriefingLbl;

    @FXML
    private Label lunchLbl;

    /**
     * Navigates to the reports screen by calling the navigation method.
     * @param event When the user clicks the back button.
     * @throws IOException Rethrows IOException when loading the next screen.
     */
    @FXML
    void toReports(ActionEvent event) throws IOException {
        Navigation.toReportsScreen(event);
    }

    /**
     * Sets the month labels when the screen is opened.
     */
    @FXML
    void initialize() {

        month0Lbl.setText(month0);
        month1Lbl.setText(month1);
        month2Lbl.setText(month2);
        month3Lbl.setText(month3);
        month4Lbl.setText(month4);
        month5Lbl.setText(month5);
        month6Lbl.setText(month6);
        month7Lbl.setText(month7);
        month8Lbl.setText(month8);
        month9Lbl.setText(month9);
        month10Lbl.setText(month10);
        month11Lbl.setText(month11);
    }
}
