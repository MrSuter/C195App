package dao;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ScheduleAppDAO {

    ObservableList<Object> getAllRecords();

    void insert(Object o) throws SQLException;

    void update(Object o);

    void delete(int ID) throws SQLException;

    Object getRecord(int ID);

}
