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
import java.awt.event.InputEvent;

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
        Runnable startTask = new Runnable()
        {
            public void run() 
            {
                try {bot = new RobotService();} catch (AWTException ex) {Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);}
                
                for(int i =0; i < 10; i++)
                {
                    bot.delay(MEDIUM);
                    bot.mouseCurve(400, 400);
                    bot.mouseClick(200);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseCurve(800, 800);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(800, 800);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON3_MASK);
                    bot.mouseCurve(400, 400);
                    bot.mouseRelease(InputEvent.BUTTON3_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(800, 400);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseCurve(400, 800);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(400, 800);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON3_MASK);
                    bot.mouseCurve(800, 400);
                    bot.mouseRelease(InputEvent.BUTTON3_MASK);
                    bot.delay(SHORT);

                    bot.delay(MEDIUM);
                    bot.mouseCurve(400, 600);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseCurve(800, 600);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(800, 600);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON3_MASK);
                    bot.mouseCurve(400, 600);
                    bot.mouseRelease(InputEvent.BUTTON3_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(600, 400);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON1_MASK);
                    bot.mouseCurve(600, 800);
                    bot.mouseRelease(InputEvent.BUTTON1_MASK);
                    bot.delay(SHORT);

                    bot.mouseCurve(600, 800);
                    bot.delay(SHORT);
                    bot.mousePress(InputEvent.BUTTON3_MASK);
                    bot.mouseCurve(600, 400);
                    bot.mouseRelease(InputEvent.BUTTON3_MASK);
                    bot.delay(SHORT);

                    System.out.println("Switch");
                    bot.delay(LONG);
                }
                
            }
        };
        
        Thread t1 = new Thread(startTask);
        t1.setDaemon(true);
        t1.start();

    }
    
}
