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
    
    @FXML
    private Label pageNameLabel;
    
    @FXML
    public void changePageNameLabel(String pageName)
    {
        pageNameLabel.setText(pageName);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
}