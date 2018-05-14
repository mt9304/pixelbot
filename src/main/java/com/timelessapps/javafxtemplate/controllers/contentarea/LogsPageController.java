package main.java.com.timelessapps.javafxtemplate.controllers.contentarea;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.FileHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;

public class LogsPageController implements Initializable
{
    CustomSceneHelper sceneHelper = new CustomSceneHelper();
    Runnable logTask;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyyMMdd");
    private String currentDate = currentDateFormat.format(timestamp);
		private LoggingService log = new LoggingService();
    
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
    @FXML
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
                        applicationLogsTabContentArea.setText(fileHelper.getTextFromFile(currentDate + "_" + "ApplicationLog.txt"));
                        eventLogsTabContentArea.setText(fileHelper.getTextFromFile(currentDate + "_" + "EventLog.txt"));
                    }   catch (IOException ex) {Logger.getLogger(CustomSceneHelper.class.getName()).log(Level.SEVERE, null, ex);}
                    
                    //For auto scrolling to bottom to see most recent events. 
                    applicationLogsTabContentArea.setScrollTop(Double.MAX_VALUE);
                    eventLogsTabContentArea.setScrollTop(Double.MAX_VALUE);
                    
                    try 
                    {
                        Thread.sleep(1000);
                    }   catch (InterruptedException ex) { Logger.getLogger(CustomSceneHelper.class.getName()).log(Level.SEVERE, null, ex);  }
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
