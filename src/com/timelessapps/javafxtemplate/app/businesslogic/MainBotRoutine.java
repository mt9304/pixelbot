package com.timelessapps.javafxtemplate.app.businesslogic;

import com.timelessapps.javafxtemplate.controllers.contentarea.HomePageController;
import static com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import com.timelessapps.javafxtemplate.helpers.services.RobotService;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainBotRoutine extends Routine
{
   
    public void run()
    {
            synchronized (this)
            {
                try 
                {
                    RobotService bot = new RobotService();
                    
                    while(running)
                    {
                        waitIfNotRunning();
                        bot.delay(MEDIUM);
                        System.out.println("Running");
                        waitIfNotRunning();
                    }
                } 
                catch (AWTException ex) {Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);} catch (InterruptedException ex) {Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    

