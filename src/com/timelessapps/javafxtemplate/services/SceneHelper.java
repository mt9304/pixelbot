package com.timelessapps.javafxtemplate.services;

import com.timelessapps.javafxtemplate.Main;
import javafx.scene.Node;
import javafx.scene.Scene;

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
    
    public Node getNodeById(String nodeID)
    {
        scene = Main.getMainScene();
        return scene.lookup("#"+nodeID);
    }
}
