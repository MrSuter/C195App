package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility for the database queries. Sets the prepared statements.
 */
public class DBQuery {

    private  static PreparedStatement statement; //Statement reference

    /**
     * Creates the statement object.
     * @param conn Connection to the database.
     * @param sqlStatement String of the sql statement to be queried.
     * @throws SQLException Rethrows SQLException when the statement is prepared.
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /**
     * Returns the prepared statement.
     * @return Prepared statement object.
     */
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }
}
