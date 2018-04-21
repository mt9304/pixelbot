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

            }
        };
        
        Thread t1 = new Thread(startTask);
        t1.start();

    }
    
}
