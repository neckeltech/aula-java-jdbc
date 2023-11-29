package tech.neckel.jdbc.database;

import java.sql.SQLException;

public class DbException extends RuntimeException {
    public DbException(SQLException reason) {
        super(reason);
    }

}
