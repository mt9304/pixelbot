package com.timelessapps.javafxtemplate.helpers.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class LoggingService 
{
    private SceneHelper sceneHelper;

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
        String fileName = "eventLog.txt";
        writeLineToFile(fileName, logText);
    }
    
    public void appendToApplicationLogsFile(String logText) throws FileNotFoundException
    {
        String fileName = "applicationLog.txt";
        writeLineToFile(fileName, logText);
    }
}
