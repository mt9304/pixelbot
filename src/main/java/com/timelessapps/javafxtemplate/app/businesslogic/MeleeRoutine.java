package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.Soundbank;

import main.java.com.timelessapps.javafxtemplate.app.verify.VerifyGrandExchange;
import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.InvalidPricesException;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.NoItemsToSellException;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.TransactionIncompleteException;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.SHORT;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.LONG;

public class MeleeRoutine extends Routine 
{
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();
	RSCoordinates rsc = new RSCoordinates();
	RSImageReader rsir = new RSImageReader();
	VerifyGrandExchange verifyGE = new VerifyGrandExchange();
	String pass = "";

	public MeleeRoutine(String pass) throws AWTException {
		this.pass = pass;
	}

	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
		log.appendToApplicationLogsFile("Starting bot routine in 3 seconds. ");
		System.out.println("Starting bot routine in 3 seconds. ");
		bot.delay(3000);

		synchronized (this) {
			try {
				disableGrandExchangeButton();
				
				initialize();
				verifyGE.loginScreenIsLoaded();
				chooseWorld();
				login();
				verifyGE.clickHereToPlayIsPresent();
				clickClickHereToPlayButton();
				Thread.sleep(5000); //Add verify chat box loaded
				scrollIn();
				//start clicking thread. reads top left, clicks mouse if text found. 
				
				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					
					//verifyHPHighEnough();
					//move mouse around after 10 to 15 seconds
					
					/** End routine here. **/
					Thread.sleep(random.nextInt(1000) + 1000);
					checkIfPausedOrStopped();
				}
			} catch (Exception ex) {
				System.out.println("Bot could not complete routine: " + ex);
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
	}
	
	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		try 
		{
			waitIfPaused();
			if (!running) {
				enableGrandExchangeButton();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private void disableGrandExchangeButton() {
		try 
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("grandExchangeButton").setDisable(true);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private void enableGrandExchangeButton() {
		try
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("grandExchangeButton").setDisable(false);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void initialize() throws Exception
	{
		System.out.println("Initializing. ");
		try
		{
			int y = RSCoordinates.getInitialOffsetY();
			int x = RSCoordinates.getInitialOffsetX(y);
			RSCoordinates.setOffsetX(x);
			RSCoordinates.setOffsetY(y);
			log.appendToApplicationLogsFile("Detected game area, starting coordinates (x,y): " + x + ", " + y);
		}
		catch (Exception e)
		{
			System.out.println("Cannot initialize GE routine: " + e);
			throw e;
		}
	}
	
	private void chooseWorld()
	{
		System.out.println("Choosing world. ");
		log.appendToApplicationLogsFile("Choosing world. ");
		try
		{
			bot.delay(500);
			bot.moveCursorTo(rsc.changeWorldButtonX(), rsc.changeWorldButtonY());
			bot.delay(500);
			bot.mouseClick();
			bot.delay(500);
			bot.moveCursorTo(rsc.freeWorldButtonX(), rsc.freeWorldButtonY());
			bot.delay(500);
			bot.mouseClick();
		}
		catch (Exception e)
		{
			System.out.println("Could not choose world GE routine: " + e);
			log.appendToApplicationLogsFile("Could not choose world GE routine: " + e);
			throw e;
		}
	}
	
	private void login()
	{
		System.out.println("Logging in. ");
		log.appendToApplicationLogsFile("Logging in. ");
		try
		{
    		bot.delay(500);
    		bot.moveCursorTo(rsc.existingUserButtonX(), rsc.existingUserButtonY());
    		bot.mouseClick();
    		bot.delay(500);
    		bot.type(pass, random.nextInt(15) + 35);
    		bot.delay(500);
    		bot.keyPress(KeyEvent.VK_ENTER);
    		bot.delay(SHORT);
    		bot.keyRelease(KeyEvent.VK_ENTER);
		}
		catch (Exception e)
		{
			System.out.println("Could not login GE routine: " + e);
			log.appendToApplicationLogsFile("Could not login GE routine: " + e);
			throw e;
		}
	}
	
	private void clickClickHereToPlayButton()
	{
		System.out.println("Clicking here to play");
		log.appendToApplicationLogsFile("Clicking play button. ");
		try
		{
    		bot.delay(500);
    		bot.moveCursorTo(rsc.clickHereToPlayButtonX(), rsc.clickHereToPlayButtonY());
    		bot.mouseClick();
    		bot.delay(500);
		}
		catch (Exception e)
		{
			System.out.println("Could not click here to play GE routine: " + e);
			log.appendToApplicationLogsFile("Could not click here to play GE routine: " + e);
			throw e;
		}
	}
	
	/**
	 * This function zooms in on the characters so that objects are easier to click. 
	 */
	private void scrollIn()
	{
		log.appendToApplicationLogsFile("Setting zoom... ");
		try
		{
			for (int i = 0; i < 10; i++)
			{
				bot.mouseWheel(-1);
				bot.delay(SHORT);
			}
			bot.delay(MEDIUM);
		}
		catch (Exception e)
		{
			System.out.println("Could not scroll in" + e);
			log.appendToApplicationLogsFile("Could not scroll in" + e);
			throw e;
		}
	}

}
