package com.timelessapps.javafxtemplate.services;

import com.timelessapps.javafxtemplate.Main;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * This class helps get FXML components from the main scene and can call functions from different controllers to change then. 
 * Used to prevent controllers from calling each other directly. 
 * For example, changing label text on the top menu pane when performing actions on a scene. 
 */
public  class SceneHelper
{
    private static Scene scene;
    
    public SceneHelper()
    {
        
    }
    
    public void setMainScene()
    {
        scene = Main.getMainScene();
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
        //Output of source is Button[id=logsButton, styleClass=button leftPaneButton]'Logs' so splitting by single quote gets the name of the object. 
        return source.toString().split("'")[1];
    }
    
    //Changes the name displayed in the top menu bar (first label on the left). 
    public void changeLabelName(String labelName, String newText)
    {
        setMainScene();
        Label label = getLabelById(labelName);
        label.setText(newText);
    }
    
    public void bringNodeToFront(String nodeName, String appendingText)
    {
        //Lowers first letter of word and removes special characters. Page Ids should always be the button's name lowered plus "Page" in camel case. So the "Home" button would return "home" and the page Id would be homePage. 
       //Appending text in the above example would be "Page", while a tab's content area would be "TextArea". 
        char c[] = nodeName.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        nodeName = (new String(c) + appendingText).replaceAll("[^a-zA-Z0-9]", "");

        setMainScene();
        getNodeById(nodeName).toFront();
    }
    
    public void activateTab(MouseEvent event)
    {
        //For tab part at the top. 
        String tabNodeID = getSourceID(event.getSource());
        Node tabEventNode = getNodeById(tabNodeID);
        tabEventNode.setStyle("-fx-border-width: 0 0 -1 0; "
                + "-fx-border-color: white white white white;"
                + "-fx-background-color: white; "
                + "-fx-background-insets: 0 0 -1 0, 0, 1, 2;"
                + "-fx-border-radius: 0px;"
                + "-fx-background-radius: 0 0 0 0;");
        
        //For tab content part. tabNodeID would be eventLogsButton or applicationLogsTabButton, the tab content area's ID would be eventLogsTabContentArea. 
        String textAreaID = tabNodeID.split("TabButton")[0];
        bringNodeToFront(textAreaID, "TabContentArea");
        System.out.println("Activating " + tabNodeID);
    }
    
    public void deactivateTabs(ArrayList<String> tabNodeIDs)
    {
        for (String tabNodeID : tabNodeIDs)
        {
            Node tabEventNode = getNodeById(tabNodeID);
            tabEventNode.setStyle("-fx-border-width: 0 0 1 0; "
            + "-fx-border-color: white white black white;"
            + " -fx-background-color: white; "
            + "-fx-background-insets: 0 0 -1 0, 0, 1, 2;"
            + " -fx-border-radius: 0px;"
            + "-fx-background-radius: 0 0 0 0;");
            
            System.out.println("Deactivating tabs: " + tabNodeID);
        }
    }
    
}
