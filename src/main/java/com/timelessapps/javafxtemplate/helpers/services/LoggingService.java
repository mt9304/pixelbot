package main.java.com.timelessapps.javafxtemplate.helpers.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LoggingService 
{
    private CustomSceneHelper sceneHelper;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat currentTimeFormat = new SimpleDateFormat("HH:mm:ss");
    private String currentDate;
    private String currentTime;

    public void writeLineToFile(String fileName, String text) throws FileNotFoundException
    {
        File file = new File(fileName);
        PrintWriter printWriter;
        if ( file.exists() && !file.isDirectory() )
        {
            printWriter = new PrintWriter(new FileOutputStream(new File(fileName), true));
        }
        else 
        {
            printWriter = new PrintWriter(fileName);
        }
        printWriter.println(text);
        printWriter.close();
    }
    
    public void appendToEventLogsFile(String logText) throws FileNotFoundException
    {
        timestamp = new Timestamp(System.currentTimeMillis());
        currentDate = currentDateFormat.format(timestamp);
        currentTime = currentTimeFormat.format(timestamp);
	
        String fileName = currentDate + "_" + "EventLog.txt";
        String formattedLogText = "[" + currentTime + "]: " + logText;
        writeLineToFile(fileName, formattedLogText);
    }
    
    public void appendToApplicationLogsFile(String logText) throws FileNotFoundException
    {
        timestamp = new Timestamp(System.currentTimeMillis());
        currentDate = currentDateFormat.format(timestamp);
        currentTime = currentTimeFormat.format(timestamp);
	
        String fileName = currentDate + "_" + "ApplicationLog.txt";
        String formattedLogText = "[" + currentTime + "]: " + logText;
        writeLineToFile(fileName, formattedLogText);
    }
}
