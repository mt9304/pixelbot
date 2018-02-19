/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import com.timelessapps.javafxtemplate.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Max
 */
public class TitleBarController implements Initializable
{
    @FXML
    private AnchorPane titleBar;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private void titleBarClicked(MouseEvent event)
    {
        System.out.println("Clicked");
    }
    
    @FXML
    private void titleBarPressed(MouseEvent event)
    {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    
    @FXML
    private void titleBarDragged(MouseEvent event) throws IOException
    {
        Stage stage = Main.getMainStage();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //xOffset = 0;
        //yOffset = 0;
    }   
}
