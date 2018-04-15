/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.contentarea;

import com.timelessapps.javafxtemplate.services.RobotService;
import java.awt.AWTException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        // TODO
    }    
    
    @FXML
    public void startApplication(MouseEvent event) throws AWTException
    {
        bot = new RobotService();
        bot.delay(2000);
        bot.type("Test test TEST 123 !@#", 0);
    }
    
}
