package com.timelessapps.javafxtemplate.controllers.contentarea;

import java.net.URL;
import java.util.ResourceBundle;

import com.timelessapps.javafxtemplate.helpers.services.RobotService;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.BotRoutine;
import com.timelessapps.javafxtemplate.helpers.threadsandroutines.GlobalKeyListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class HomePageController implements Initializable
{
    RobotService bot;
    private boolean started = false;
    
    public HomePageController()
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    @FXML
    public void startApplication(MouseEvent event) throws InterruptedException 
    {
            BotRoutine botRoutine = new BotRoutine();
            botRoutine.setDaemon(true);
            botRoutine.start();
            
            if (!started)
            {
                started = true; 
                
                GlobalKeyListener globalKeyListenerThread = new GlobalKeyListener(botRoutine);
                globalKeyListenerThread.setDaemon(true);
                globalKeyListenerThread.start();
            }
            
            //botRoutine.join();
            //System.out.println("Remember to re-active button. ");
    }
    
}
