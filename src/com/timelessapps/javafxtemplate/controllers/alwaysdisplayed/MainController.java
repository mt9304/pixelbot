/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import com.timelessapps.javafxtemplate.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Max
 */
public class MainController implements Initializable
{
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane mainWindow;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void toggleBorderColor(Boolean focused)
    {
        if (focused)
        {
            mainWindow.getStyleClass().add("greyBorder");
        }
        else if (!focused)
        {
            mainWindow.getStyleClass().remove("greyBorder");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //For changing border color when focussing and unfocussing. Note that it starts with focused by default, even if program was not focussed when first opening. Functions starts working when first clicking into the program and then out. 
        //https://stackoverflow.com/questions/24038988/event-when-window-stage-lost-focus , https://docs.oracle.com/javafx/2/api/javafx/beans/value/ObservableBooleanValue.html#get()
        Stage stage = Main.getMainStage();
        stage.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
            {
                //ObersableValue ov will be true if window is focused, false if not focused (clicking another window). 
                Boolean focused = ov.getValue();
                toggleBorderColor(focused);
            }
        });
    }    
    
}
