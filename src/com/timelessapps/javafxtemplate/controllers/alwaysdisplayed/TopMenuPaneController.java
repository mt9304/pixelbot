package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TopMenuPaneController implements Initializable
{
    SceneHelper sceneHelper = new SceneHelper();
    
    @FXML
    private Label pageNameLabel;
    
    @FXML
    public void changePageNameLabel(String pageName)
    {
        pageNameLabel.setText(pageName);
    }
    
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