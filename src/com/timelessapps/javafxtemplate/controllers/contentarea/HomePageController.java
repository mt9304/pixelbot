package com.timelessapps.javafxtemplate.controllers.contentarea;

import java.net.URL;
import java.util.ResourceBundle;

import com.timelessapps.javafxtemplate.app.businesslogic.MainBotRoutine;
import com.timelessapps.javafxtemplate.app.supportingthreads.GlobalKeyListener;
import com.timelessapps.javafxtemplate.helpers.services.RobotService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
		MainBotRoutine mainBotRoutine = new MainBotRoutine();
		mainBotRoutine.setDaemon(true);
		mainBotRoutine.start();
		
		GlobalKeyListener globalKeyListenerThread = new GlobalKeyListener(mainBotRoutine);
		globalKeyListenerThread.setDaemon(true);
		globalKeyListenerThread.start();
		
		//botRoutine.join();
		//System.out.println("Remember to re-active button. ");
    }
    
}
