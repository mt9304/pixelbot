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
        Runnable startTask = new Runnable()
        {
            public void run() 
            {
                try {
                    RobotService bot = new RobotService();
                    boolean keepGoing = true;
                    
                    while(keepGoing)
                    {
                        bot.delay(SHORT);
                        System.out.println("Logging");
                    }
                    
                } catch (AWTException ex) {
                    Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread t1 = new Thread(startTask);
        t1.setDaemon(true);
        t1.start();
        
        Runnable keyListener = new Runnable()
        {
            public void run()
            {
                		try {
                                                    GlobalScreen.registerNativeHook();
                                                    // Get the logger for "org.jnativehook" and set the level to warning.
                                                    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                                                    logger.setLevel(Level.WARNING);

                                                    // Don't forget to disable the parent handlers.
                                                    logger.setUseParentHandlers(false);
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
            }
        };
        
        Thread keyListenerThread = new Thread(keyListener);
        keyListenerThread.setDaemon(true);
        keyListenerThread.start();

    }
    
}
