package main.java.com.timelessapps.javafxtemplate.app.verify;

import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.exceptions.ElementNotFoundException;

public class VerifyGrandExchange 
{
	RSImageReader rsir = new RSImageReader();
	RSCoordinates rsc = new RSCoordinates();
	
	public void loginScreenIsLoaded() throws ElementNotFoundException 
	{
		System.out.println("Verifying login screen loaded. ");
		try
		{
			for (int i = 0; i < 20; i++)
			{
				String loginText = rsir.getRSLoginText(rsc.existingUserButton());
				System.out.println("Login text: " + loginText);
				if (loginText.contains("Existing User"))
				{
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void clickHereToPlayIsPresent() throws ElementNotFoundException 
	{
		System.out.println("Verifying login screen loaded. ");
		try
		{
			for (int i = 0; i < 20; i++)
			{
				String clickHereToPlayText = rsir.getRSLoginText(rsc.clickHereToPlayButton());
				System.out.println("Text: " + clickHereToPlayText);
				if (clickHereToPlayText.contains("CLICK HERE TO FLAY"))
				{
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (Exception e)
		{
			System.out.println("Could not click here to play: " + e);
		}
	}
	
	public void isOnBuyScreen() throws ElementNotFoundException
	{
		System.out.println("Veryfying RS is on buy screen at GE. ");
		try
		{
			for (int i = 0; i < 10; i++)
			{
				String buyText = rsir.getGESearchAreaText(rsc.buySearchAreaText());
				System.out.println("Text: " + buyText);
				if (buyText.contains("What would you")) //Text in game should turn out as What would you like to huH?
				{
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect search area saying What would you like to buy?");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void isOnGEScreen() 
	{
		System.out.println("Veryfying RS is on the main GE screen. ");
		try
		{
			for (int i = 0; i < 10; i++)
			{
				String geText = rsir.getRSText(rsc.mainGrandExchangeText());
				System.out.println("Text: " + geText);
				if (geText.contains("Grand"))
				{
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect top of page saying Grand Exchange. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
