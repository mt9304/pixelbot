/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

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
                
                bot.delay(1000);
                bot.mouseMove(500,700);
                bot.mouseClick(200);
                
                bot.delay(1000);
                bot.mouseMove(500, 700);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(500, 700, 100, 300, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(100,300);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(100, 300, 500, 700, 1000, 100);
                bot.delay(1000);
               // bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(500,700);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(500, 700, 100, 300, 1000, 100);
                bot.delay(1000);
               // bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                bot.delay(1000);
                bot.mouseMove(100,300);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(100, 300, 500, 700, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                //
                
                System.out.println("Part 1");
                bot.delay(1000);
                bot.mouseMove(500,300);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(500, 300, 100, 700, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(100,700);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(100, 700, 500, 300, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                System.out.println("Part 2");
                bot.delay(1000);
                bot.mouseMove(500,300);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(500, 300, 100, 700, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                bot.delay(1000);
                bot.mouseMove(100,700);
                bot.delay(1000);
                //bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(100, 700, 500, 300, 1000, 100);
                bot.delay(1000);
                //bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                
                // ** // ** //
                
                bot.delay(1000);
                bot.mouseMove(100, 350);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(100, 350, 700, 350, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(700,350);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(700, 350, 100, 350, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(100,350);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(100, 350, 700, 350, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                bot.delay(1000);
                bot.mouseMove(700,350);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(700, 350, 100, 350, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                //
                
                System.out.println("Part 1");
                bot.delay(1000);
                bot.mouseMove(400,400);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(400, 400, 400, 800, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                bot.delay(1000);
                bot.mouseMove(400,800);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(1000);
                bot.mouseCurveX(400, 800, 400, 400, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                
                System.out.println("Part 2");
                bot.delay(1000);
                bot.mouseMove(400,400);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(400, 400, 400, 800, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                bot.delay(1000);
                bot.mouseMove(400,800);
                bot.delay(1000);
                bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(1000);
                bot.mouseCurveY(400, 800, 400, 400, 1000, 100);
                bot.delay(1000);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                
                
            }
        };
        
        Thread t1 = new Thread(startTask);
        t1.start();

    }
    
}
