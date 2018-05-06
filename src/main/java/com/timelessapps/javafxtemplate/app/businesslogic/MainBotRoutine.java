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
    
    /**This section below is for performing game related functions. . **/
    
    /** Laptop **/
    //Middle of HP icon is at 1176, 203
    //Middle of Prayer icon is at 1176, 254
    
    /** PC **/
    //Middle of HP icon is at 
    //Middle of Prayer icon is at 
    
    //0-19 are absorbs, 20-26 are overloads, 27 is rock. 
    /* Default, raw coords from laptop screen. 
    int[][] invSlots = new int[][] 
	{
		{1203, 432},{1256, 432},{1309, 423},{1361, 423},
		{1203, 470},{1256 ,470},{1309 ,470},{1361, 470},
		{1203, 515},{1256, 515},{1309, 515},{1361, 515},
		{1203, 558},{1256, 558},{1309, 558},{1361, 558},
		{1203, 602},{1256, 602},{1309, 602},{1361, 602},
		{1203, 647},{1256, 647},{1309, 647},{1361, 647},
		{1203, 693},{1256, 693},{1309, 693},{1361, 693}
	};
	*/
    
    //Change this according to where Pray center is. 
    int invXOffset = 1176; 
    int invYOffset = 254;
    
    int prayButtonX = invXOffset;
    int prayButtonY = invYOffset;
    
    int HPIconX = invXOffset; 
    int HPIconY = invYOffset - 51; //Remember to double check. 
    
    int hpColor = 50;
	
	//After subtracting by reference point. 
	int [][] absorbPots = new int[][]
	{
		{27+invXOffset, 178+invYOffset}, {80+invXOffset, 178+invYOffset}, {133+invXOffset, 178+invYOffset}, {185+invXOffset, 178+invYOffset}, 
		{27+invXOffset, 216+invYOffset}, {80+invXOffset, 216+invYOffset}, {133+invXOffset, 216+invYOffset}, {185+invXOffset, 216+invYOffset}, 
		{27+invXOffset, 261+invYOffset}, {80+invXOffset, 261+invYOffset}, {133+invXOffset, 261+invYOffset}, {185+invXOffset, 261+invYOffset}, 
		{27+invXOffset, 304+invYOffset}, {80+invXOffset, 304+invYOffset}, {133+invXOffset, 304+invYOffset}, {185+invXOffset, 304+invYOffset}, 
		{27+invXOffset, 348+invYOffset}, {80+invXOffset, 348+invYOffset}, {133+invXOffset, 348+invYOffset}, {185+invXOffset, 348+invYOffset}, 
		{27+invXOffset, 393+invYOffset}, {80+invXOffset, 393+invYOffset}, {133+invXOffset, 393+invYOffset}, {185+invXOffset, 393+invYOffset}, 
		{27+invXOffset, 439+invYOffset}, {80+invXOffset, 439+invYOffset}, {133+invXOffset, 439+invYOffset}, {185+invXOffset, 439+invXOffset}
	};
	
	int [][] overloadPots = new int[][]
	{
		{27+invXOffset, 393+invYOffset}, {80+invXOffset, 393+invYOffset}, {133+invXOffset, 393+invYOffset}, {185+invXOffset, 393+invYOffset}, 
		{27+invXOffset, 439+invYOffset}, {80+invXOffset, 439+invYOffset}, {133+invXOffset, 439+invYOffset}

	};
	
	//Can make this regular array, but just keeping it 2d for consistency sake. 
	int [][] rockCake = new int[][]
	{
		{185+invXOffset, 439+invXOffset}
	};
    
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
    