package dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customer;
import model.User;
import utils.DBConnection;
import utils.DBQuery;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CustomerDao {

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private static Connection conn = DBConnection.getConnnection();
    private DivisionDao divisionDao= new DivisionDao();

    public ObservableList<Customer> selectCustomers(){
        String customerSelectStatement= "SELECT first_level_divisions.Division, customers.* FROM first_level_divisions RIGHT JOIN customers ON first_level_divisions.Division_ID = customers.Division_ID;";
        try {
            DBQuery.setPreparedStatement(conn, customerSelectStatement);
            PreparedStatement psCustomer = DBQuery.getPreparedStatement();
            psCustomer.execute();
            ResultSet rsCustomer = psCustomer.getResultSet();
            while(rsCustomer.next()){
                int customerID = rsCustomer.getInt("Customer_ID");
                String name = rsCustomer.getString("Customer_Name");
                String address = rsCustomer.getString("Address");
                String postalCode = rsCustomer.getString("Postal_Code");
                String phone = rsCustomer.getString("Phone");
                Timestamp createDate = rsCustomer.getTimestamp("Create_Date");
                String createdBy = rsCustomer.getString("Created_By");
                Timestamp lastUpdateTime = rsCustomer.getTimestamp("Last_Update");
                String lastUpdatedBy = rsCustomer.getString("Last_Updated_By");
                int divisionID = rsCustomer.getInt("Division_ID");
                String divisionName =rsCustomer.getString("Division");

                Customer customer = new Customer(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdateTime, lastUpdatedBy, divisionID, divisionName);
                this.customerList.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customerList;
    }
    public void insertCustomer(Object o) throws SQLException {
        Customer customer = (Customer) o;
        //, name, address, postalCode, phone, createDate, createdBy, lastUpdateTime, lastUpdatedBy, divisionID
        int customerID = customer.getCustomerID();
        String name = customer.getName();
        String address = customer.getAddress();
        String postalCode = customer.getPostalCode();
        String phone = customer.getPhone();
        Timestamp createDate = customer.getCreateDate();
        String createdBy = customer.getCreatedBy();
        Timestamp lastUpdateTime = customer.getLastUpdateTime();
        String lastUpdatedBy = customer.getLastUpdatedBy();
        int divisionID = customer.getDivisionID();
        String insertStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setTimestamp(5, createDate);
        ps.setString(6, createdBy);
        ps.setString(7, lastUpdatedBy);
        ps.setInt(8, divisionID);
        ps.execute();
    }

    public void deleteCustomer(int customerID) {
        try {
            String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?;";
            DBQuery.setPreparedStatement(conn, deleteStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setInt(1, customerID);
            ps.execute();
        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You must delete the appointments with this customer before deleting the customer.");
            Optional<ButtonType> result = alert.showAndWait();
            throwables.printStackTrace();
        }
    }

    public void updateCustomer(Object o){
        Customer customer = (Customer) o;
        int customerID = customer.getCustomerID();
        String name = customer.getName();
        String address = customer.getAddress();
        String postalCode = customer.getPostalCode();
        String phone = customer.getPhone();
        //Timestamp createDate = customer.getCreateDate();
        //String createdBy = customer.getCreatedBy();
        Timestamp lastUpdateTime = customer.getLastUpdateTime();
        String lastUpdatedBy = customer.getLastUpdatedBy();
        int divisionID = customer.getDivisionID();
        //String divisionName = customer.getDivisionName();

        String updateString = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ?;";
        try {
            DBQuery.setPreparedStatement(conn, updateString);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, lastUpdateTime);
            ps.setString(6, lastUpdatedBy);
            ps.setInt(7, divisionID);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(Object o){
        String update = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?;";
        Customer customer = (Customer) o;
        int ID = customer.getCustomerID();
        String name = customer.getName();
        String address = customer.getAddress();
        String postalCode = customer.getPostalCode();
        String phone = customer.getPhone();
        Timestamp lastUpdate = customer.getLastUpdateTime();
        String lastUpdatedBy = customer.getLastUpdatedBy();
        int divisionID = customer.getDivisionID();
        try {
            DBQuery.setPreparedStatement(conn, update);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, lastUpdate);
            ps.setString(6, lastUpdatedBy);
            ps.setInt(7, divisionID);
            ps.setInt(8, ID);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
/*

 */
    public static Customer selectSingleCustomer(int ID) throws SQLException {
        Customer customer = new Customer(-1, "Anita", "home", "45877", "", Timestamp.valueOf(LocalDateTime.now()), "The Creater", Timestamp.valueOf(LocalDateTime.now()), "Daddy", -1, "Suter");
        String select = "SELECT * FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, select);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, ID);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            String divisionName = DivisionDao.selectSingleDivision(divisionID).getDivision();

            customer = new Customer(customerId, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionID, divisionName);
        }
        return customer;
    }




}
