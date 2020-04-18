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
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.SHORT;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.LONG;

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
	String[] items = { "Coal", "Nature Rune", "Death Rune"};
	int lowPrice = 0;
	int highPrice = 0;

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
				exchangeWithClerk(); //Opens exchange menu
				
				while (running) {
					for (int i = 0; i < 3; i++)
					{
						checkIfPausedOrStopped();
						/** Start routine here. **/
						
						verifyGE.isOnGEScreen();
						collectFromGE(i);
						buyHigh(items[i], i);
						//collectFromGEWhenSuccessful(i);
						//sellLow();
						//collectFromGEWhenSuccessful(i);
						//validateItemPrices();
						
						//buy low
						//wait til green
						//gather item
						//sell high
						//wait to green
						//gather item
						
						Thread.sleep(random.nextInt(1000) + 1000);
						checkIfPausedOrStopped();
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		/*
		if (counter > 180) {
			System.out.println("Counter is: " + counter + ". Stopping routine. ");
			running = false;
		}
		*/
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
		System.out.println("Exchanging with clerk");
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

	/**
	 * This function will collect the items from the GE slot if there is an active trade going on, even if it is only partial complete. 
	 * This function will cancel the trade and then collect the items if the trade is only partially complete. 
	 * @param i The integer representing which GE slot to collect from. In f2p, there are 3 slots available
	 */
	private void collectFromGE(int i)
	{
		log.appendToEventLogsFile("Attempting to collect trade items from slot: " + i);
		try
		{
			int sellButtonX = rsc.sellButtonX()[i];
			int sellButtonY = rsc.sellButtonY()[i];
			Color sellArrowColor = bot.getPixelColor(sellButtonX, sellButtonY);
			Color rsOrange = new Color(198,105,3);
			
			if (!RSImageReader.isSameColor(sellArrowColor, rsOrange)) //Check if there is an item in the trade slot already. It will be orange if not. 
			{
				bot.delay(SHORT);
				bot.moveCursorTo(rsc.queuedButtonX()[i], rsc.queuedButtonY()[i]);
				bot.delay(SHORT);
				bot.mouseClick();
				bot.delay(SHORT);
				bot.moveCursorTo(rsc.cancelOrderButtonX(), rsc.cancelOrderButtonY());
				bot.delay(MEDIUM);
				bot.mouseClick();
				bot.delay(SHORT);
				bot.moveCursorTo(rsc.secondSoldItemSlotX(), rsc.secondSoldItemSlotY());
				bot.delay(SHORT);
				bot.mouseClick();
				bot.delay(SHORT);
				bot.moveCursorTo(rsc.firstSoldItemSlotX(), rsc.firstSoldItemSlotY());
				bot.delay(SHORT);
				bot.mouseClick();
				bot.delay(SHORT);
			}
			else
			{
				log.appendToEventLogsFile("No active trade detected, skipping collect. ");
				System.out.println(" No active trade detected, skipping collect. ");
			}
		}
		catch (Exception e)
		{
			System.out.println("Could not collect from slot " + i + ": " + e);
		}
		
	}
	
	private void buyHigh(String item, int slot) {
		System.out.println("Attempting to buy high: "+ item);
		try
		{
			//Search for item
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.buyButtonX()[slot], rsc.buyButtonY()[slot]);
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			verifyGE.isOnBuyScreen();
			bot.type(item);
			bot.delay(MEDIUM);
			bot.delay(MEDIUM);
			
			if (item.toLowerCase().equals("coal")) //A handful of items will be at the second position, everything else can usually be accessed by pressing enter
			{
				bot.moveCursorTo(rsc.secondSearchResultX(), rsc.secondSearchResultY());
				bot.delay(SHORT);
				bot.mouseClick();
			}
			else
			{
				bot.keyPress(KeyEvent.VK_ENTER);
			}
			
			//Set price to much higher than market price
			bot.delay(MEDIUM);
			bot.delay(MEDIUM);
			bot.moveCursorTo(rsc.increasePriceX(), rsc.increasePriceY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(MEDIUM);
			bot.mouseClick();
			bot.delay(MEDIUM);
			bot.mouseClick();
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.confirmTradeButtonX(), rsc.confirmTradeButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
		}
		catch (Exception e)
		{
			log.appendToEventLogsFile("Could not buyHigh: " + e);
		}
	}
}
