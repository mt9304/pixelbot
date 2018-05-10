package main.java.com.timelessapps.javafxtemplate.app.supportingthreads;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MainBotRoutine;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Buff;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;

public class BuffTimer extends Thread
{
    LoggingService log = new LoggingService();
     
    MainBotRoutine mainBotRoutine;
    int timeToWait;
    Buff buff;
    
    public BuffTimer(MainBotRoutine mainBotRoutine, int timeToWait, Buff buff)
    {
	this.mainBotRoutine = mainBotRoutine;
    }
    
    @Override
    public void run()
    {
	try 
	{
	    log.appendToEventLogsFile("The timer for buff: " + buff + "has started for " + timeToWait + "milliseconds. ");
	} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
	
	try 
	{
	    Thread.sleep(timeToWait);
	} catch (InterruptedException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
	
	try 
	{
	    log.appendToEventLogsFile("The timer for buff: " + buff + "has expired. ");
	} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
	
	switch (buff)
	{
	    case ABSORB:
		mainBotRoutine.setShouldAbsorb(true);
		break;
		
	    case OVERLOAD:
		mainBotRoutine.setShouldOverload(true);
		break;
		
	    default:
		try 
		{
		    log.appendToEventLogsFile("The current buff (" + buff.toString() + ") could not be handled. ");
		} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		break;
	}
	
    }
}
