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
				
				if (loginText.contains("Existing User"))
				{
					return;
				}
				Thread.sleep(1500);
			}
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (ElementNotFoundException e)
		{
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
		catch (ElementNotFoundException e)
		{
			throw new ElementNotFoundException("Could not detect login button saying Existing User. ");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
