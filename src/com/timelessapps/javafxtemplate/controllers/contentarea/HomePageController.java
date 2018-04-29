/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

import static com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.LONG;
import static com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import static com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.SHORT;
import java.awt.AWTException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.timelessapps.javafxtemplate.helpers.services.RobotService;
import com.timelessapps.javafxtemplate.helpers.services.GlobalKeyListener;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.BotRoutine;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.GlobalKeyListenerThread;
import java.awt.event.InputEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class HomePageController implements Initializable
{
    RobotService bot;
    
    public HomePageController()
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    @FXML
    public void startApplication(MouseEvent event) 
    {

        BotRoutine botRoutine = new BotRoutine();
        botRoutine.setDaemon(true);
        botRoutine.start();
        
        GlobalKeyListenerThread globalKeyListenerThread = new GlobalKeyListenerThread(botRoutine);
        globalKeyListenerThread.setDaemon(true);
        globalKeyListenerThread.start();
    }
    
}
