package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://172.17.0.2:3306/perguntados";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(
                    DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
