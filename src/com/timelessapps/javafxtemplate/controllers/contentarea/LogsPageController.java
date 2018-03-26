/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class LogsPageController implements Initializable
{
    SceneHelper sceneHelper = new SceneHelper();
    
    @FXML
    private TextArea eventLogsTabContentArea, applicationLogsTabContentArea;
        
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
    
    @FXML
    public void appendToEventLogTabContentArea(String text)
    {
        sceneHelper.setTextArea("eventLogsTabContentArea", text);
    }
            
    @FXML
    public  void appendToApplicationLogTabContentArea(String text)
    {
        sceneHelper.setTextArea("applicationLogsTabContentArea", text);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }
    
}
