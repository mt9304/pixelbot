package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.Soundbank;

import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.AttackTimer;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.BuffTimer;
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
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Buff.OVERLOAD;
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
	Color hpArea = new Color(0,0,0);
	int cursorMovementStyle = 5;

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
				disableMeleeButton();
				
				initialize();
				verifyGE.loginScreenIsLoaded();
				chooseWorld();
				login();
				verifyGE.clickHereToPlayIsPresent();
				clickClickHereToPlayButton();
				Thread.sleep(5000); //Add verify chat box loaded
				//scrollIn();
				tiltScreenUp();
				//start clicking thread. reads top left, clicks mouse if text found. 
				
			    AttackTimer attackTimer = new AttackTimer(); //300800
			    attackTimer.setDaemon(true);
			    attackTimer.start();
				
				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					
					//Check if hp is enough. Currently checking if halfway. 
					hpArea = bot.getPixelColor(rsc.middleHPX(), rsc.middleHPY()); //Halfway point for hp bar. 
					if (hpArea.getRed() < 40) // Red value is higher if the hp bar is filled, less than 40 is empty. 
					{
						System.out.println("Red part in HP area not detected, stopping routine. ");
						return; 
					}
					//move mouse around after 10 to 15 seconds
					//cursorMovementStyle = random.nextInt(8);
					moveMouseRandomly(cursorMovementStyle);
					
					/** End routine here. **/
					Thread.sleep(random.nextInt(2000) + 2000);
					if(attackTimer.getState() == Thread.State.TERMINATED)
					{ 
						System.out.println("Attach timer has stopped. Exiting. ");
						return;
					}
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
				enableMeleeButton();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private void disableMeleeButton() {
		try 
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("meleeButton").setDisable(true);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private void enableMeleeButton() {
		try
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("meleeButton").setDisable(false);
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
			System.out.println("Cannot initialize Melee routine: " + e);
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
			System.out.println("Could not choose world Melee routine: " + e);
			log.appendToApplicationLogsFile("Could not choose world Melee routine: " + e);
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
			System.out.println("Could not login Melee routine: " + e);
			log.appendToApplicationLogsFile("Could not login Melee routine: " + e);
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
			System.out.println("Could not click here to play Melee routine: " + e);
			log.appendToApplicationLogsFile("Could not click here to play Melee routine: " + e);
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
			System.out.println("Could not scroll in: " + e);
			log.appendToApplicationLogsFile("Could not scroll in: " + e);
			throw e;
		}
	}
	
	private void tiltScreenUp() 
	{
		log.appendToApplicationLogsFile("Setting screen tilt... ");
		try
		{
			bot.delay(MEDIUM);
			bot.keyPress(KeyEvent.VK_UP);
			bot.delay(MEDIUM);
			bot.delay(MEDIUM);
			bot.keyRelease(KeyEvent.VK_UP);
			bot.delay(MEDIUM);
		}
		catch (Exception e)
		{
			System.out.println("Could not set screen tilt: " + e);
			log.appendToApplicationLogsFile("Could not set screen tilt: " + e);
			throw e;
		}
	}
	
	private void moveMouseRandomly(int style) 
	{
		log.appendToApplicationLogsFile("Moving mouse randomly, style: " + style);
		try
		{
			int x = rsc.getOffsetX();
			int y = rsc.getOffsetY();
			//zig zag, circle shape, v shape
			switch(style) 
			{
				case 0:
					
					bot.moveCursorSlowlyToGeneralArea(77 + x, 50 + y);
					bot.moveCursorSlowlyToGeneralArea(253 + x, 90 + y);
					bot.moveCursorSlowlyToGeneralArea(447 + x, 81 + y);
					bot.moveCursorSlowlyToGeneralArea(336 + x, 162 + y);
					bot.moveCursorSlowlyToGeneralArea(364 + x, 166 + y);
					bot.moveCursorSlowlyToGeneralArea(69 + x, 257 + y);
					bot.moveCursorSlowlyToGeneralArea(239 + x, 266 + y);
					bot.moveCursorSlowlyToGeneralArea(421 + x, 244 + y);
					
					/*
					bot.moveCursorSlowlyToGeneralArea(171 + x, 250 + y);
					bot.moveCursorSlowlyToGeneralArea(112 + x, 156 + y);
					bot.moveCursorSlowlyToGeneralArea(193 + x, 52 + y);
					bot.moveCursorSlowlyToGeneralArea(352 + x, 78 + y);
					bot.moveCursorSlowlyToGeneralArea(394 + x, 161 + y);
					bot.moveCursorSlowlyToGeneralArea(315 + x, 259 + y);
					bot.moveCursorSlowlyToGeneralArea(111 + x, 223 + y);
					*/
					break;
				case 1:
					bot.moveCursorSlowlyToGeneralArea(171 + x, 250 + y);
					bot.moveCursorSlowlyToGeneralArea(112 + x, 156 + y);
					bot.moveCursorSlowlyToGeneralArea(193 + x, 52 + y);
					bot.moveCursorSlowlyToGeneralArea(352 + x, 78 + y);
					bot.moveCursorSlowlyToGeneralArea(394 + x, 161 + y);
					bot.moveCursorSlowlyToGeneralArea(315 + x, 259 + y);
					bot.moveCursorSlowlyToGeneralArea(111 + x, 223 + y);
					break;
				case 2:
					bot.moveCursorSlowlyToGeneralArea(399 + x, 79 + y);
					bot.moveCursorSlowlyToGeneralArea(329 + x, 229 + y);
					bot.moveCursorSlowlyToGeneralArea(245 + x, 277 + y);
					bot.moveCursorSlowlyToGeneralArea(148 + x, 196 + y);
					bot.moveCursorSlowlyToGeneralArea(78 + x, 50 + y);
					break;
				default: 
					bot.moveCursorSlowlyTo(720, 122);
					bot.moveCursorSlowlyTo(744, 324);
					bot.moveCursorSlowlyTo(976, 323);
					bot.moveCursorSlowlyTo(960, 97);
					bot.moveCursorSlowlyTo(720, 122);
					break;
			}
		}
		catch (Exception e)
		{
			System.out.println("Could not moveMouseRandomly: " + e);
			log.appendToApplicationLogsFile("Could not moveMouseRandomly: " + e);
			throw e;
		}
	}
}
