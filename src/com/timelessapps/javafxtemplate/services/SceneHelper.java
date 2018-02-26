package com.timelessapps.javafxtemplate.services;

import com.timelessapps.javafxtemplate.Main;
import com.timelessapps.javafxtemplate.controllers.alwaysdisplayed.TopMenuPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SceneHelper
{
    private static Scene scene;
    
    public SceneHelper()
    {
        
    }
    
    //The source argument uses event.getSource(), which returns in the following format: Button[id=homeButton, styleClass=button leftPaneButton]'Home'
    //This method splits the string twice to get the id value. Can look into using regex instead, but might not be as readable. 
    public String getSourceID(Object source)
    {
        return source.toString().split(",")[0].split("=")[1];
    }
    
    public Node getNodeById(String nodeId)
    {
        scene = Main.getMainScene();
        return scene.lookup("#"+nodeId);
    }
    
    public Label getLabelById(String labelName)
    {
        scene = Main.getMainScene();
        return (Label) scene.lookup("#"+labelName);
    }
    
    public String getSourceName(Object source)
    {
        return source.toString().split("'")[1];
    }
    
    
    //Changes the name displayed in the top menu bar (First label on the left). 
    public void changeLabelName(String labelName, String newText)
    {
        scene = Main.getMainScene();
        Label label = getLabelById(labelName);
        label.setText(newText);
    }
    

    
}
