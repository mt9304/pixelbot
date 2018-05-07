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
		{1203, 423},{1256, 423},{1309, 423},{1361, 423},
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
    int invYOffset = 245;
    
    int prayButtonX = invXOffset;
    int prayButtonY = invYOffset;
    
    int HPIconX = 1166; 
    int HPIconY = 207;
    
    int hpColor = 50;
	
	//After subtracting by reference point. 
	int[][] invSlots = new int[][] 
	{
		{27+invXOffset, 179+invYOffset},   {80+invXOffset, 179+invYOffset},   {131+invXOffset, 179+invYOffset},   {185+invXOffset, 179+invYOffset},
		{27+invXOffset, 223+invYOffset},   {80+invXOffset, 223+invYOffset},   {131+invXOffset, 223+invYOffset},   {185+invXOffset, 223+invYOffset},
		{27+invXOffset, 267+invYOffset},   {80+invXOffset, 267+invYOffset},   {131+invXOffset, 267+invYOffset},   {185+invXOffset, 223+invYOffset},
		{27+invXOffset, 312+invYOffset},   {80+invXOffset, 312+invYOffset},   {131+invXOffset, 312+invYOffset},   {185+invXOffset, 312+invYOffset},
		{27+invXOffset, 358+invYOffset},   {80+invXOffset, 358+invYOffset},   {131+invXOffset, 358+invYOffset},   {185+invXOffset, 358+invYOffset}
	};
	
	int [][] overloadPots = new int[][]
	{
		{27+invXOffset, 401+invYOffset},   {80+invXOffset, 401+invYOffset},   {131+invXOffset, 401+invYOffset},   {185+invXOffset, 401+invYOffset},
		{27+invXOffset, 448+invYOffset},   {80+invXOffset, 448+invYOffset},   {131+invXOffset, 448+invYOffset}
	};
	
	//Can make this regular array, but just keeping it 2d for consistency sake. 
	int [][] rockCake = new int[][]
	{
		{185+invXOffset, 448+invYOffset}
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
    