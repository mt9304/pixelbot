/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

import com.timelessapps.javafxtemplate.services.FileHelper;
import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class LogsPageController implements Initializable
{
    SceneHelper sceneHelper = new SceneHelper();
    Runnable logTask;
    
    @FXML
    private TextArea eventLogsTabContentArea, applicationLogsTabContentArea;
    
    @FXML
    private CheckBox autoRefreshCheckBox;
        
    @FXML
    private void setActivateTab(MouseEvent event)
    {
        sceneHelper.activateTab(event);
        sceneHelper.deactivateTabs(getAllTabsExceptActive(event));
    }
    
    //For how to deactivate all tabs remember to look into: https://stackoverflow.com/questions/24986776/how-do-i-get-all-nodes-in-a-scene-in-javafx/24986845 
    @FXML
    private ArrayList<String> getAllTabsExceptActive(MouseEvent event)
    {
        ArrayList<String> tabNodeIDs = new ArrayList<>();
        String[] tabNodeIDsToFilter = {"applicationLogsTabButton","eventLogsTabButton"};
        String activeTab = sceneHelper.getSourceID(event.getSource());
        
        for (String tabNodeID : tabNodeIDsToFilter)
        {
            if (!activeTab.equals(tabNodeID))
            {
                tabNodeIDs.add(tabNodeID);
            }
        }
        return tabNodeIDs;
    }
    
    //This keeps updating the logs area in the application with text from the logs file every second. Runs in a background thread to keep updating if checkbox is selected. Remember to consider another braces formatting style since it looks really messy below with the try catches. 
    @FXML //TO DO: Add update for event log area as well. 
    public void keepUpdatingLogsInApplication()
    {
         logTask = new Runnable()
        {
            public void run()
            {
                FileHelper fileHelper = new FileHelper();
                while (autoRefreshCheckBox.isSelected())
                {
                    try 
                    { 
                        applicationLogsTabContentArea.setText(fileHelper.getTextFromFile("applicationLog.txt"));
                    }   catch (IOException ex) {Logger.getLogger(SceneHelper.class.getName()).log(Level.SEVERE, null, ex);}
                    
                    //For auto scrolling to bottom to see most recent events. 
                    applicationLogsTabContentArea.setScrollTop(Double.MAX_VALUE);
                    
                    try 
                    {
                        Thread.sleep(1000);
                    }   catch (InterruptedException ex) { Logger.getLogger(SceneHelper.class.getName()).log(Level.SEVERE, null, ex);  }
                }
            }
        };
        //Run the task in a background thread
        Thread backgroundThread = new Thread(logTask);
        //Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        //Start the thread
        backgroundThread.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }
    
}
