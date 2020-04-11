package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.timelessapps.javafxtemplate.app.verify.VerifyGrandExchange;
import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class GrandExchangeRoutine extends Routine 
{
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();
	RSCoordinates rsc = new RSCoordinates();
	RSImageReader rsir = new RSImageReader();
	Rectangle rect = rsc.existingUserButton();
	VerifyGrandExchange verifyGE = new VerifyGrandExchange();
	String pass = "";

	int counter = 0;

	public GrandExchangeRoutine(String pass) throws AWTException {
		this.pass = pass;
	}

	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
		
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
				Thread.sleep(5000);
				exchangeWithClerk(); //Open sexchange menu
				
				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					
					//buy item (validate pages)
					//wait for green 
					//gather item
					//sell item
					//wait for green
					//set prices
					
					//buy low
					//wait til green
					//gather item
					//sell high
					//wait to green
					//gather item
					
					//Repeat 2 and 3 for 2 more slots in future
					
					int randomNumber = random.nextInt(27);
					counter++;
					System.out.println("Typed letter. ");
					Thread.sleep(random.nextInt(120000) + 120000);
					checkIfPausedOrStopped();
				}
			} catch (Exception ex) {
				System.out.println(ex);
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		if (counter > 180) {
			System.out.println("Counter is: " + counter + ". Stopping routine. ");
			running = false;
		}

		waitIfPaused();
		if (!running) {
			enableGrandExchangeButton();
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
	
	private void initialize()
	{
		System.out.println("Initializing. ");
		try
		{
			int y = rsc.getInitialOffsetY();
			int x = rsc.getInitialOffsetX(y);
			rsc.setOffsetX(x);
			rsc.setOffsetY(y);
		}
		catch (Exception e)
		{
			System.out.println("Cannot initialize GE routine: " + e);
		}
	}
	
	private void chooseWorld()
	{
		System.out.println("Choosing world. ");
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
		}
	}
	
	private void login()
	{
		System.out.println("Logging in. ");
		try
		{
    		bot.delay(500);
    		bot.moveCursorTo(rsc.existingUserButtonX(), rsc.existingUserButtonY());
    		bot.mouseClick();
    		bot.delay(500);
    		bot.type(pass, random.nextInt(15) + 35);
    		bot.delay(500);
    		bot.keyPress(KeyEvent.VK_ENTER);
		}
		catch (Exception e)
		{
			System.out.println("Could not login GE routine: " + e);
		}
	}
	
	private void clickClickHereToPlayButton()
	{
		System.out.println("Clicking here to play");
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
		}
	}
	
	private void exchangeWithClerk() 
	{
		System.out.println("Clicking here to play");
		try
		{
    		bot.delay(500);
    		bot.moveCursorTo(rsc.grandExchangeClerkX(), rsc.exchangeButtonY());
    		bot.delay(500);
    		bot.mouseClick();
    		bot.delay(2000);
    		bot.keyPress(KeyEvent.VK_SPACE);
    		bot.delay(2500);
    		bot.type("2", 500);
    		bot.delay(2500);
    		bot.keyPress(KeyEvent.VK_SPACE);
    		bot.delay(500);
		}
		catch (Exception e)
		{
			System.out.println("Could not exchange with clerk: " + e);
		}
	}

}
