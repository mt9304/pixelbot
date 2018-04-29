package com.timelessapps.javafxtemplate.helpers.threadsandroutines;

import com.timelessapps.javafxtemplate.controllers.contentarea.HomePageController;
import static com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import com.timelessapps.javafxtemplate.helpers.services.RobotService;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotRoutine extends Thread
{
    public Object routineLock = new Object();
    
    public void run()
    {
            synchronized (this)
            {
                try 
                {
                    RobotService bot = new RobotService();
                    boolean keepGoing = true;

                    while(keepGoing)
                    {
                        bot.delay(MEDIUM);
                        System.out.println("Logging");
                    }
                } 
                catch (AWTException ex) {Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);}
            }
        }
    }
    

