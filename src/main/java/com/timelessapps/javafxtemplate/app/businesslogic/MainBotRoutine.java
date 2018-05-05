package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class MainBotRoutine extends Routine 
{
   RobotService bot = new RobotService();
   
   public MainBotRoutine() throws AWTException
   {
       
   }
    
    public void run()
    {
	    synchronized (this)
	    {
	        try 
	        {
	            disableStartButton();
	            while(running)
	            {
		checkIfPausedOrStopped();
		System.out.println("Running");
		bot.delay(MEDIUM);
		checkIfPausedOrStopped();
	            }
	            
	        }  catch (InterruptedException ex) {Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);}
	    }
    }
    
    @Override
    public void checkIfPausedOrStopped() throws InterruptedException
    {
    	waitIfPaused();
    	if (!running)
    	{
    		enableStartButton();
    	}
    }

    private void disableStartButton() 
    {
	    CustomSceneHelper sceneHelper = new CustomSceneHelper();
	    sceneHelper.getNodeById("startButton").setDisable(true);
    }

    private void enableStartButton() 
    {
	    CustomSceneHelper sceneHelper = new CustomSceneHelper();
	    sceneHelper.getNodeById("startButton").setDisable(false);
    }
    
    /**This section below is for checking in-game statuses. **/
    
    public void moveToPrayButton()
    {
	
    }
    
    public void flickPray()
    {
	bot.mouseClick(100);
    }
    
    public void checkHealth()
    {
	
    }
    
    public void drinkAbsorbPotion()
    {
	
    }
    
    public void drinkOverloadPotion()
    {
	
    }
    
    public void checkIfAbsorbNeeded()
    {
	
    }
    
}
    