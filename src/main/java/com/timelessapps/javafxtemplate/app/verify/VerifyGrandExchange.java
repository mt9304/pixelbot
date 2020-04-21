package main.java.com.timelessapps.javafxtemplate.app.verify;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.ElementNotFoundException;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.TransactionIncompleteException;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.SHORT;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.MEDIUM;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration.LONG;

public class VerifyGrandExchange
{
	RobotService bot = new RobotService();
	RSImageReader rsir = new RSImageReader();
	RSCoordinates rsc = new RSCoordinates();
	LoggingService log = new LoggingService();
	
	public VerifyGrandExchange() throws AWTException
	{
		
	}
	
	public void loginScreenIsLoaded() throws Exception 
	{
		System.out.println("Verifying login screen loaded. ");
		log.appendToApplicationLogsFile("Verifying login screen loaded. ");
		try
		{
			for (int i = 0; i < 20; i++)
			{
				String loginText = rsir.getRSLoginText(rsc.existingUserButton());
				//System.out.println("Login text: " + loginText);
				if (loginText.contains("Existing User"))
				{
					System.out.println("Existing user button detected. ");
					log.appendToApplicationLogsFile("Existing user button detected. ");
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	public void clickHereToPlayIsPresent() throws Exception 
	{
		System.out.println("Verifying click to play screen is loaded. ");
		log.appendToApplicationLogsFile("Verifying click to play screen is loaded. ");
		try
		{
			for (int i = 0; i < 20; i++)
			{
				String clickHereToPlayText = rsir.getRSLoginText(rsc.clickHereToPlayButton());
				//System.out.println("Text: " + clickHereToPlayText);
				if (clickHereToPlayText.contains("CLICK HERE TO FLAY"))
				{
					System.out.println("Click here to play button detected. ");
					log.appendToApplicationLogsFile("Click here to play button detected. ");
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not click here to play: " + e);
			throw e;
		}
	}
	
	public void isOnBuyScreen() throws Exception
	{
		System.out.println("Veryfying RS is on buy screen at GE. ");
		log.appendToApplicationLogsFile("Veryfying RS is on buy screen at GE. ");
		try
		{
			for (int i = 0; i < 10; i++)
			{
				String buyText = rsir.getGESearchAreaText(rsc.buySearchAreaText());
				//System.out.println("Text: " + buyText);
				if (buyText.contains("What would you")) //Text in game should turn out as What would you like to huH?
				{
					System.out.println("Buy screen detected. ");
					log.appendToApplicationLogsFile("Buy screen detected. ");
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect search area saying What would you like to buy?");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

	public void isOnGEScreen() throws Exception 
	{
		System.out.println("Veryfying RS is on the main GE screen. ");
		log.appendToApplicationLogsFile("Veryfying RS is on the main GE screen. ");
		try
		{
			for (int i = 0; i < 10; i++)
			{
				String geText = rsir.getRSText(rsc.mainGrandExchangeText());
				//System.out.println("Text: " + geText);
				if (geText.contains("Grand"))
				{
					System.out.println("GE screen detected. ");
					log.appendToApplicationLogsFile("GE screen detected. ");
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect top of page saying Grand Exchange. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

	public void transactionCompleted(int geSlot) throws Exception {
		System.out.println("Verifying that all items have sold in GE slot: " + geSlot);
		log.appendToApplicationLogsFile("Verifying that all items have sold in GE slot: " + geSlot);
		try
		{
			for (int i = 0; i < 31; i++)
			{
				System.out.println("Checking " + i + "/30 times. ");
				Color green = new Color(0, 95, 0);
				Color currentColor = bot.getPixelColor(rsc.progressBarX()[geSlot], rsc.progressBarY()[geSlot]);
				
				if (RSImageReader.isSameColor(green, currentColor))
				{
					System.out.println("Green progress bar detected for slot: " + geSlot);
					log.appendToApplicationLogsFile("Green progress bar detected for slot: " + geSlot);
					return;
				}
				bot.type(" "); //Typing to prevent being disconnected. 
				bot.keyPress(KeyEvent.VK_BACK_SPACE);
				bot.delay(SHORT);
				bot.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect green progress bar. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}
	
	public void transactionCompleted(int geSlot, Duration duration) throws Exception {
		System.out.println("Verifying that all items have sold in GE slot: " + geSlot);
		log.appendToApplicationLogsFile("Verifying that all items have sold in GE slot: " + geSlot);
		try
		{
			for (int i = 0; i < 51; i++)
			{
				System.out.println("Checking " + i + "/50 times. ");
				Color green = new Color(0, 95, 0);
				Color currentColor = bot.getPixelColor(rsc.progressBarX()[geSlot], rsc.progressBarY()[geSlot]);
				
				if (RSImageReader.isSameColor(green, currentColor))
				{
					System.out.println("Green progress bar detected for slot: " + geSlot);
					log.appendToApplicationLogsFile("Green progress bar detected for slot: " + geSlot);
					return;
				}
				bot.type(" "); //Typing to prevent being disconnected. 
				bot.keyPress(KeyEvent.VK_BACK_SPACE);
				bot.delay(SHORT);
				bot.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(5000);
			}
			throw new TransactionIncompleteException("Did not detect green progress bar in time. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

	public void isOnSellScreen() throws Exception 
	{
		System.out.println("Veryfying RS is on the GE sell screen. ");
		log.appendToApplicationLogsFile("Veryfying RS is on the GE sell screen. ");
		try
		{
			for (int i = 0; i < 5; i++)
			{
				String geText = rsir.getRSText(rsc.sellOfferText());
				//System.out.println("Text: " + geText);
				if (geText.contains("ell"))
				{
					System.out.println("Sell screen detected. ");
					log.appendToApplicationLogsFile("Sell screen detected. ");
					return;
				}
				Thread.sleep(1000);
			}
			throw new ElementNotFoundException("Could not detect Sell offer text on the GE sell screen. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

	public void isOnTradeHistoryScreen() throws Exception 
	{
		System.out.println("Veryfying RS is on the trade history screen");
		log.appendToApplicationLogsFile("Veryfying RS is on the trade history screen");
		try
		{
			for (int i = 0; i < 5; i++)
			{
				String geText = rsir.getRSText(rsc.tradeHistoryText());
				//System.out.println("Text: " + geText);
				if (geText.contains("Trade"))
				{
					System.out.println("Trade History screen detected. ");
					log.appendToApplicationLogsFile("Trade History screen detected. ");
					return;
				}
				Thread.sleep(1000);
			}
			throw new ElementNotFoundException("Could not detect trade history screen. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}

	public boolean isItemToSell() {
		try
		{
			bot.delay(MEDIUM);
			bot.moveCursorTo(rsc.firstInventorySlotX(), rsc.firstInventorySlotY());
			bot.delay(SHORT);
			Color grey = new Color(219, 219, 218);
			Color currentColor = bot.getPixelColor(rsc.offerTextX(), rsc.offerTextY());
			bot.delay(SHORT);
			bot.delay(MEDIUM);
			//If there is an item collected, then sell it at high price
			if (currentColor.getRed() > 150 && currentColor.getGreen() > 150 && currentColor.getBlue() > 150)
			{
				log.appendToApplicationLogsFile("Detected item to sell. ");
				return true;
			}
			else
			{
				log.appendToApplicationLogsFile("Did not detect item to sell. ");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			throw e;
		}
	}
}
