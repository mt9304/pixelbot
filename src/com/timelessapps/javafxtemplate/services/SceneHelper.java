package com.timelessapps.javafxtemplate.services;

import com.timelessapps.javafxtemplate.Main;
import com.timelessapps.javafxtemplate.controllers.alwaysdisplayed.TopMenuPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * This class helps get FXML components from the main scene and can call functions from different controllers to change then. 
 * Used to prevent controllers from calling each other directly. 
 * For example, changing label text on the top menu pane when performing actions on a scene. 
 */
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
        setMainScene();
        return scene.lookup("#"+nodeId);
    }
    
    public Label getLabelById(String labelName)
    {
        setMainScene();
        return (Label) scene.lookup("#"+labelName);
    }
    
    public String getSourceName(Object source)
    {
        return source.toString().split("'")[1];
    }
    
    //Changes the name displayed in the top menu bar (first label on the left). 
    public void changeLabelName(String labelName, String newText)
    {
        setMainScene();
        Label label = getLabelById(labelName);
        label.setText(newText);
    }
    
    public void bringNodeToFront(String pageName)
    {
        //Page Ids should always be the button's name lowered plus "Page". So the "Home" button would return "home" and the page Id would be homePage. 
        pageName = pageName.toLowerCase() + "Page";
        setMainScene();
        getNodeById(pageName).toFront();
    }
    
    public void setMainScene()
    {
        scene = Main.getMainScene();
    }
    
    

    
}
