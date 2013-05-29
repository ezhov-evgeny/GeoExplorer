package ru.ezhoff.geolocation.geoexplorer;

import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author e.ezhov
 * @version 1.0 29.05.13
 */
public class FileLogger
{
    private static final String INFO    = "INFO";
    private static final String ERROR   = "ERROR";
    private static final String DEBUG   = "DEBUG";
    private static final String WARN    = "WARN";

    private static FileLogger instance;
    private static TextView outputView;

    //private static String logFilePath = Environment.getExternalStorageDirectory().toString() + "/explorer.log";
    private static String logFilePath = "/explorer.log";

    private File logFile;

    private BufferedWriter writer;

    public static void setLogFilePath(String logFilePath) {
        FileLogger.logFilePath = logFilePath;
    }

    public static void setOutputView(TextView outputView) {
        FileLogger.outputView = outputView;
    }

    public static FileLogger getInstance() {
        if (logFilePath == null) {
            throw new IllegalStateException("Log file is not initialized.");
        }
        if (instance == null) {
            try {
                instance = new FileLogger(logFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private FileLogger(String logFilePath) throws IOException {
        logFile = new File(logFilePath);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        writer = new BufferedWriter(new FileWriter(logFile, true));
    }

    public void info(String message) {
        append(INFO, message);
    }

    public void error(String message) {
        append(ERROR, message);
    }

    public void debug(String message) {
        append(DEBUG, message);
    }

    public void warn(String message) {
        append(WARN, message);
    }

    private void append(String level, String message) {
        if (outputView != null) {
            outputView.append(message + "\n");
        }
        try {
            writer.append(Calendar.getInstance().toString()).append("\t")
                  .append(level).append("\t")
                  .append(message).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (instance != null) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            instance = null;
        }
    }
}
