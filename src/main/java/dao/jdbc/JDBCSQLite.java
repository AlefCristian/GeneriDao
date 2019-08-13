package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSQLite implements JDBC {
    
    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:db/clientegarcon.db");

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

}
