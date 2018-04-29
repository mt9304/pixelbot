package com.timelessapps.javafxtemplate.controllers.contentarea;

import java.net.URL;
import java.util.ResourceBundle;

import com.timelessapps.javafxtemplate.helpers.services.RobotService;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.BotRoutine;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.GlobalKeyListenerThread;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

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
