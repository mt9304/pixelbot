/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class LoggingService 
{
    public LoggingService()
    {
        
    }

    public void writeToFile(String fileName, String text) throws FileNotFoundException
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
        writeToFile(fileName, logText);
    }
    
    public void appendToApplicationLogsFile(String logText) throws FileNotFoundException
    {
        String fileName = "applicationLog.txt";
        writeToFile(fileName, logText);
    }
    
    public void appendToEventLogsInApplication(String text)
    {
        //logsPageController.appendToEventLogTabContentArea(text);
    }
    
    public void appendToApplicationLogsInApplication(String text)
    {
        //logsPageController.appendToApplicationLogTabContentArea(text);
    }
    
}
