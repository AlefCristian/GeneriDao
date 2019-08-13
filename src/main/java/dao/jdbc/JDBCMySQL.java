package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCMySQL implements JDBC {
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String HOST ="127.0.0.1";
    private static final String DB = "generic_dao";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(CLASS_NAME);
            return DriverManager.getConnection(
                    "jdbc:mysql://" + HOST + "/" + DB + "?autoReconnect=false&useSSL=false",
                    USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            
            throw new SQLException(e.getMessage());
        }
    }

}
