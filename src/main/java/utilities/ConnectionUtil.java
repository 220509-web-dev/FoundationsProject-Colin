package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
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
