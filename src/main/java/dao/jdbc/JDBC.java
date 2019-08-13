package dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBC {
    Connection getConnection() throws SQLException;
}
