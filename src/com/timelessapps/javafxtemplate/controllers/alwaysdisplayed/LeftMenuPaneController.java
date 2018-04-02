/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;
import com.timelessapps.javafxtemplate.controllers.contentarea.LogsPageController;
import com.timelessapps.javafxtemplate.services.LoggingService;
import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LeftMenuPaneController implements Initializable
{
    SceneHelper sceneHelper = new SceneHelper();
    LoggingService loggingService = new LoggingService();
    
    private Boolean logThreadStarted = false;
    
    @FXML
    private Button homeButton, applicationButton, apiDatabaseButton, _generalButton, logsButton;

    @FXML
    private void highlightButton(MouseEvent event)
    {
        String nodeID = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeID);
        eventNode.setStyle("-fx-background-color: #555764;");
    }
    
    @FXML
    private void unHighlightButton(MouseEvent event)
    {
        String nodeId = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeId);
        eventNode.setStyle("-fx-background-color: #3A3C43;");
    }
    
    @FXML
    private void unHighlightButtonIfNotActive(MouseEvent event)
    {
        Node activePage = sceneHelper.getNodeById("pageNameLabel");
        String activePageName = sceneHelper.getSourceName(activePage);
        String hoveredButton = sceneHelper.getSourceName(event.getSource());
        if (!activePageName.equals(hoveredButton))
        {
            unHighlightButton(event);
        }
     }
    
    @FXML
    private void unHighlightByID(String nodeId)
    {
        Node previousPageButton = sceneHelper.getNodeById(nodeId);
        previousPageButton.setStyle("-fx-background-color: #3A3C43;");
    }
    
    @FXML
    private void menuButtonClicked(MouseEvent event) throws FileNotFoundException, InterruptedException
    {
        //Unhighlights the previous button that was clicked. 
        Node pageNameLabel = sceneHelper.getNodeById("pageNameLabel");
        String previousPageName = sceneHelper.getSourceName(pageNameLabel);
        String previousPageNameID = sceneHelper.convertStringToID(previousPageName, "Button");
        unHighlightByID(previousPageNameID);
        
        //Changes name of the label in top left of top menu bar (id of pageNameLabel) to the name of the button clicked. 
        String buttonName = sceneHelper.getSourceName(event.getSource());
        sceneHelper.changeLabelName("pageNameLabel", buttonName);

        //Brings the page clicked to the front. 
        sceneHelper.bringNodeToFront(buttonName, "Page");
        
        //For Logging. TO DO remember  to delete this
        loggingService.appendToApplicationLogsFile(buttonName);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }    
    
}
