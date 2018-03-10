/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class LogsPageController implements Initializable
{
    SceneHelper sceneHelper = new SceneHelper();
        
    @FXML
    private void activateTab(MouseEvent event)
    {
        String nodeID = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeID);
        eventNode.setStyle("-fx-border-width: 0 0 -1 0; "
                + "-fx-border-color: white white white white;"
                + " -fx-background-color: white; "
                + "-fx-background-insets: 0 0 -1 0, 0, 1, 2;"
                + " -fx-border-radius: 0px;"
                + "-fx-background-radius: 0 0 0 0;");
    }
    
    //For how to deactivate all tabs remember to look into: https://stackoverflow.com/questions/24986776/how-do-i-get-all-nodes-in-a-scene-in-javafx/24986845 
    @FXML
    private void deactivateTabs(MouseEvent event)
    {
        String nodeID = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeID);
        eventNode.setStyle("-fx-border-width: 0 0 1 0; "
                + "-fx-border-color: white white black white;"
                + " -fx-background-color: white; "
                + "-fx-background-insets: 0 0 -1 0, 0, 1, 2;"
                + " -fx-border-radius: 0px;"
                + "-fx-background-radius: 0 0 0 0;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
    
}
