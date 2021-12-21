package dao;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface for the DAO classes to follow.
 */
public interface ScheduleAppDAO {
    /**
     * Retrieves all records.
     * @return List of all records as objects.
     */
    ObservableList<Object> getAllRecords();

    /**
     * Inserts a single record into the database.
     * @param o Object to be inserted into the database.
     * @throws SQLException Rethrows SQLException when the query is executed.
     */
    void insert(Object o) throws SQLException;

    /**
     * Updates a record in the database.
     * @param o Object of the record to be updated.
     */
    void update(Object o);

    /**
     * Deletes a record from the database.
     * @param ID Integer ID of the record to be deleted.
     * @throws SQLException Rethrows SQLException when the query is executed.
     */
    void delete(int ID) throws SQLException;

    /**
     * Retrieves a single record from the database.
     * @param ID Integer ID of the record to be retrieved.
     * @return Rethrows SQLException when the query is executed.
     */
    Object getRecord(int ID);

}
