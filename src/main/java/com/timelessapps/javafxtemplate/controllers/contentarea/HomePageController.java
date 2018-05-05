package main.java.com.timelessapps.javafxtemplate.controllers.contentarea;

import java.awt.AWTException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MainBotRoutine;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.GlobalKeyListener;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

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
    public void startApplication(MouseEvent event) throws InterruptedException, AWTException 
    {
	MainBotRoutine mainBotRoutine = new MainBotRoutine();
	mainBotRoutine.setDaemon(true);
	mainBotRoutine.start();

	GlobalKeyListener globalKeyListener = new GlobalKeyListener(mainBotRoutine);
	globalKeyListener.setDaemon(true);
	globalKeyListener.start();

	//botRoutine.join();
	//System.out.println("Remember to re-active button. ");
    }
    
}
