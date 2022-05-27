package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private Logger() {}

    public static void Log(LogLevel level, String message) {

        String logMessage = "\n" + level + ": " + message + " Date: " + new Date();
        try (FileWriter fileWriter = new FileWriter("appslog.txt", true)) {
            fileWriter.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Log(LogLevel level, String message, String method) {

        String logMessage = "\n" + level + " Method: " + method + " Date: " + new Date() + "\n\t" + message;
        try (FileWriter fileWriter = new FileWriter("appslog.txt", true)) {
            fileWriter.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
