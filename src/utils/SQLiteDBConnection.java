package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteDBConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "database/database_sqlite.db";

    public static Connection getConnection() {
        Connection c = null;
        try {
            File f = new File(DB_URL);
            if (f.exists()) {
                Class.forName(JDBC_DRIVER);
                c = DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

}
