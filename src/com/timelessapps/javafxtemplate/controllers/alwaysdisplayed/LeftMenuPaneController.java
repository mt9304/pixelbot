/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import com.timelessapps.javafxtemplate.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JScrollPane;

/**
 * FXML Controller class
 *
 * @author Max
 */
public class LeftMenuPaneController implements Initializable
{
    Stage stage = Main.getMainStage();
    
    @FXML
    private Button homeButton, applicationButton, apiDatabaseButton, _generalButton, logsButton;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void highlightButton(MouseEvent event)
    {
        System.out.println(event.getButton().name().toString());
        System.out.println(event.getSource());
        Scene scene = Main.getMainScene();
        scene.lookup("#"+event.getSource().toString().split(",")[0].split("=")[1]).setStyle("-fx-background-color: white;");
        //.setStyle("-fx-background-color: #4E5361;");
    }
    
    @FXML
    private void unHighlightButton(MouseEvent event)
    {
        Scene scene = Main.getMainScene();
        scene.lookup("#"+event.getSource().toString().split(",")[0].split("=")[1]).setStyle("-fx-background-color: #4E5361;");
    }
    
    @FXML
    private void menuButtonClicked(MouseEvent event)
    {
        System.out.println("Clicked");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
}
