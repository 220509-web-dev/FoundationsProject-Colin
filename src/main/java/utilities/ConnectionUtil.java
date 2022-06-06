package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static ConnectionUtil instance;

    private ConnectionUtil() {}

    public static ConnectionUtil getInstance() {
        if (instance == null) {
            instance = new ConnectionUtil();
        }

        return instance;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e); // fail fast
        }
    }

    public static Connection getConnection(){
        String dbInfo = "jdbc:postgresql://localhost/postgres?user=postgres&password=password&currentSchema=foundation_project";
        try {
            Connection connection = DriverManager.getConnection(dbInfo);
            return connection;
        } catch (SQLException e) {
            //e.printStackTrace();
            String message = e.getMessage();
            Logger.Log(LogLevel.ERROR,message);
            return null;
        }
    }
}
