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
	String[] items = { "coal", "nature", "death", "iron ore", "chaos", "gold ore", "law"};
	int lowPrice = 0;
	int highPrice = 0;
	int currentGold = 210000 ;
	int geLimit = 11000; //GE limits the amount of an item that can be bought every 8 hours. 
	int currentSlot = 0; //Up to 3 slots for f2p
	int currentItemIndex = 0;

	public GrandExchangeRoutine(String pass) throws AWTException {
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
				exchangeWithClerk(); //Opens exchange menu
				
				while (running && currentItemIndex < 7) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					
					verifyGE.isOnGEScreen();
					if(collectFromGE(currentSlot))
					{
						checkIfPausedOrStopped();
						sellLow(currentSlot);
					}
					//Sell first inventory slot
					buyHigh(items[currentItemIndex], currentSlot);
					collectFromGEWhenSuccessful(currentSlot);
					checkIfPausedOrStopped();
					sellLow(currentSlot);
					collectFromGEWhenSuccessful(currentSlot);
					checkIfPausedOrStopped();
					if (!pricesSuccessfullySet()) //Will go to trade history to read values
					{
						checkIfPausedOrStopped();
						clickExchangeButton(); //Returns the screen to the main GE page. 
						resetPrices();
						//incrementCurrentSlot(); //Uncomment when there is enough gp to use multiple slots
						currentItemIndex++;
						continue;
					}
					clickExchangeButton();
					buyLow(items[currentItemIndex], currentSlot);
					collectFromGEWhenSuccessful(currentSlot, LONG);
					checkIfPausedOrStopped();
					if (verifyGE.isItemToSell())
					{
						sellHigh(currentSlot);
						collectFromGEWhenSuccessful(currentSlot, LONG);
					}
					//Below only runs if there is left over unsold items at high price. Need to sell it at low price to recover gp and move on. 
					if (verifyGE.isItemToSell())
					{
						sellLow(currentSlot);
						collectFromGEWhenSuccessful(currentSlot);
					}
					checkIfPausedOrStopped();
					resetPrices();
					//incrementCurrentSlot(); //Uncomment when there is enough gp to use multiple slots
					currentItemIndex++;
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
	
	private void exchangeWithClerk() 
	{
		System.out.println("Exchanging with clerk");
		log.appendToApplicationLogsFile("Exchanging with clerk");
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
			log.appendToApplicationLogsFile("Could not exchange with clerk: " + e);
			throw e;
		}
	}

	/**
	 * This function will collect the items from the GE slot if there is an active trade going on, even if it is only partial complete. 
	 * This function will cancel the trade and then collect the items if the trade is only partially complete. 
	 * @param i The integer representing which GE slot to collect from. In f2p, there are 3 slots available
	 */
	private boolean collectFromGE(int i)
	{
		System.out.println("Attempting to collect from GE slot: " + i);
		log.appendToApplicationLogsFile("Attempting to collect trade items from slot: " + i);
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
				
				Color cancelButtonRed = bot.getPixelColor(rsc.cancelOrderRedPixelX(), rsc.cancelOrderRedPixelY());
				//Color greenProgressBar = new Color(0, 95, 0);
				//if (RSImageReader.isSameColor(bot.getPixelColor(rsc.largeProgressBarX(), rsc.largeProgressBarY()), greenProgressBar))
				if (cancelButtonRed.getRed() > 80)
				{
					bot.moveCursorTo(rsc.cancelOrderButtonX(), rsc.cancelOrderButtonY());
					bot.delay(MEDIUM);
					bot.mouseClick();
					bot.delay(MEDIUM);
				}
				bot.delay(MEDIUM);
				bot.moveCursorTo(rsc.secondSoldItemSlotX(), rsc.secondSoldItemSlotY());
				bot.delay(SHORT);
				bot.mouseClick();
				bot.delay(SHORT);
				bot.moveCursorTo(rsc.firstSoldItemSlotX(), rsc.firstSoldItemSlotY());
				bot.delay(SHORT);
				bot.mouseClick();
				bot.delay(SHORT);
				return true;
			}
			else
			{
				log.appendToApplicationLogsFile("No active trade detected, skipping collect. ");
				System.out.println(" No active trade detected, skipping collect. ");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Could not collect from slot " + i + ": " + e);
			throw e;
		}
		
	}
	
	private void buyHigh(String item, int slot) throws Exception {
		System.out.println("Attempting to buy " + item + " at high price. ");
		log.appendToApplicationLogsFile("Attempting to buy " + item + " at high price. ");
		try
		{
			//Search for item
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.buyButtonX()[slot], rsc.buyButtonY()[slot]);
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			verifyGE.isOnBuyScreen();
			bot.delay(MEDIUM);
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
				bot.keyRelease(KeyEvent.VK_ENTER);
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
			System.out.println("Finished buying high. ");
			log.appendToApplicationLogsFile("Finished buying at high price. ");
		}
		catch (Exception e)
		{
			log.appendToApplicationLogsFile("Could not buyHigh: " + e);
			System.out.println("Could not buyHigh: " + e);
			throw e;
		}
	}
		
	private void collectFromGEWhenSuccessful(int geSlot) throws Exception 
	{
		System.out.println("Waiting for transaction to complete before collecting. ");
		log.appendToApplicationLogsFile("Waiting for transaction to complete before collecting. ");
		try
		{
			verifyGE.transactionCompleted(geSlot);
			collectFromGE(geSlot);
			System.out.println("Finished collecting from GE. ");
			log.appendToApplicationLogsFile("Finished collecting from GE. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not collectFromGEWhenSuccessful(): " + e);
			log.appendToApplicationLogsFile("Could not collectFromGEWhenSuccessful(): " + e);
			throw e;
		}
	}
	
	private void collectFromGEWhenSuccessful(int geSlot, Duration duration) throws Exception 
	{
		System.out.println("Waiting for transaction to complete before collecting. ");
		log.appendToApplicationLogsFile("Waiting for transaction to complete before collecting. ");
		try
		{
			verifyGE.transactionCompleted(geSlot, LONG);
			collectFromGE(geSlot);
			System.out.println("Finished collecting from GE. ");
			log.appendToApplicationLogsFile("Finished collecting from GE. ");
		}
		catch (TransactionIncompleteException ex)
		{
			log.appendToApplicationLogsFile("Caught TransactionIncompleteException, will sell remaining items if found. ");
			collectFromGE(geSlot);
		}
		catch (Exception e)
		{
			System.out.println("Could not collectFromGEWhenSuccessful(): " + e);
			log.appendToApplicationLogsFile("Could not collectFromGEWhenSuccessful(): " + e);
			throw e;
		}
	}
	
	private void sellLow(int slot) throws Exception
	{
		System.out.println("Selling item below market price. ");
		log.appendToApplicationLogsFile("Selling item below market price. ");
		try
		{
			verifyGE.isOnGEScreen();
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.sellButtonX()[slot], rsc.sellButtonY()[slot]);
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.firstInventorySlotX(), rsc.firstInventorySlotY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			verifyGE.isOnSellScreen();
			bot.moveCursorTo(rsc.decreasePriceX(), rsc.decreasePriceY());
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
			System.out.println("Finished selling low. ");
			log.appendToApplicationLogsFile("Finished selling at low price. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not sellLow(): " + e);
			log.appendToApplicationLogsFile("Could not sellLow(): " + e);
			throw e;
		}
	}
	
	/**
	 * This function will to the trade history screen on the GE page to check last 2 transations and see the price difference. 
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean pricesSuccessfullySet() throws Exception 
	{
		System.out.println("Validating item prices. "); 
		log.appendToApplicationLogsFile("Validating item prices. "); 
		try
		{
			bot.delay(SHORT);
			verifyGE.isOnGEScreen();
			bot.moveCursorTo(rsc.historyButtonX(), rsc.historyButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			verifyGE.isOnTradeHistoryScreen();
			bot.delay(SHORT);
			lowPrice = rsir.getRSNumber(rsc.firstHistoryRowPrice());
			highPrice = rsir.getRSNumber(rsc.secondHistoryRowPrice());
			bot.delay(SHORT);
			int priceDifference = highPrice - lowPrice;
			System.out.println("Current low: " + lowPrice + " Current high: " + highPrice);
			log.appendToApplicationLogsFile("Current low: " + lowPrice + " Current high: " + highPrice);
			if (lowPrice == 0 || highPrice == 0) //Prices should never be 0, 0 is the default value assigned and something would have been wrong if either stayed at 0. Program should throw error and stop for analysis. 
			{
				throw new InvalidPricesException("Current prices are not valid, lowPrice: " + lowPrice + " highPrice: " + highPrice);
			}
			if (priceDifference < 1 || priceDifference > 5) //If price difference is too high, then this is not a typical scenario suitable for automated trading. Should skip to next item and wait for volatility to decrease. 
			{
				System.out.println("Current price difference is too high for effective trading. " + priceDifference);
				log.appendToApplicationLogsFile("Current price difference is too high for effective trading. " + priceDifference);
				return false;
			}
			if (priceDifference == 5 || priceDifference == 4) //Sometimes the price difference will be a lot, but still sort of reasonable. If it is 4 or 5, then decrease the high and lows by 1 to get faster trading times. 
			{
				System.out.println("Adjusted priced by 1 in each direction. ");
				log.appendToApplicationLogsFile("Adjusted priced by 1 in each direction. ");
				lowPrice = lowPrice + 1;
				highPrice = highPrice - 1;
			}
			
			int quantity = currentGold/lowPrice;
			if (quantity < 1)
			{
				System.out.println("Not enough gold to buy items. ");
				log.appendToApplicationLogsFile("Not enough gold to buy items. ");
				throw new InvalidPricesException("Not enough gold to buy items. ");
			}
			log.appendToApplicationLogsFile("Current price differences are reasonable. Low: " + lowPrice + " . High: " + highPrice);
			System.out.println("Current price differences are reasonable. Low: " + lowPrice + " . High: " + highPrice);
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Could not validateItemPrices(): " + e);
			log.appendToApplicationLogsFile("Could not validateItemPrices(): " + e);
			throw e;
		}
	}
	
	private void clickExchangeButton() throws Exception
	{
		System.out.println("Clicking Exchange Button. ");
		log.appendToApplicationLogsFile("Clicking Exchange Button. ");
		try
		{
			bot.delay(SHORT);
			verifyGE.isOnTradeHistoryScreen();
			bot.moveCursorTo(rsc.exchangeButtonX(), rsc.exchangeButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			verifyGE.isOnGEScreen();
			bot.delay(SHORT);
			System.out.println("Clicked on exchange button. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not clickExchangeButton(): " + e);
			log.appendToApplicationLogsFile("Could not clickExchangeButton(): " + e);
			throw e;
		}
	}
	
	private void buyLow(String item, int slot) throws Exception 
	{
		System.out.println("Buying low");
		log.appendToApplicationLogsFile("Buying at low price");
		try
		{
			//Search for item
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.buyButtonX()[slot], rsc.buyButtonY()[slot]);
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			verifyGE.isOnBuyScreen();
			bot.delay(MEDIUM);
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
				bot.keyRelease(KeyEvent.VK_ENTER);
			}
			//Specify price
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.specifyPriceButtonX(), rsc.specifyPriceButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(MEDIUM);
			
			bot.delay(MEDIUM);
			bot.type(Integer.toString(lowPrice));
			bot.delay(SHORT);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			bot.delay(SHORT);
			//Specify quantity
			bot.moveCursorTo(rsc.specifyQuantityButtonX(), rsc.specifyQuantityButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(MEDIUM);
			bot.delay(MEDIUM);
			int quantity = currentGold/lowPrice;
			bot.type(Integer.toString(quantity));
			bot.delay(SHORT);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			bot.delay(SHORT);
			//Confirm trade
			bot.moveCursorTo(rsc.confirmTradeButtonX(), rsc.confirmTradeButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			System.out.println("Finished buyingLow()");
			log.appendToApplicationLogsFile("Finished setting up buy request");
		}
		catch (Exception e)
		{
			System.out.println("Could not buyLow(): " + e);
			log.appendToApplicationLogsFile("Could not buyLow(): " + e);
			throw e;
		}
	}
	
	private void sellHigh(int slot) throws Exception 
	{
		System.out.println("Selling high");
		log.appendToApplicationLogsFile("Selling at high price");
		try
		{
			verifyGE.isOnGEScreen();
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.sellButtonX()[slot], rsc.sellButtonY()[slot]);
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.firstInventorySlotX(), rsc.firstInventorySlotY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			verifyGE.isOnSellScreen();
			bot.moveCursorTo(rsc.specifyPriceButtonX(), rsc.specifyPriceButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(MEDIUM);
			bot.delay(MEDIUM);
			bot.type(Integer.toString(highPrice));
			bot.delay(SHORT);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			bot.delay(SHORT);
			bot.delay(SHORT);
			bot.delay(SHORT);
			bot.moveCursorTo(rsc.confirmTradeButtonX(), rsc.confirmTradeButtonY());
			bot.delay(SHORT);
			bot.mouseClick();
			bot.delay(SHORT);
			System.out.println("Finished selling high. ");
			log.appendToApplicationLogsFile("Finished selling at high price");
		}
		catch (NoItemsToSellException ex)
		{
			log.appendToApplicationLogsFile("Caught NoItemsToSellException, skipping sell. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not sellHigh(): " + e);
			log.appendToApplicationLogsFile("Could not sellHigh(): " + e);
			throw e;
		}
	}

	private void resetPrices()
	{
		lowPrice = 0;
		highPrice = 0;
	}
	
	private void incrementCurrentSlot()
	{
		currentSlot++;
		if (currentSlot > 2)
		{
			currentSlot = 0;
		}
	}
}
