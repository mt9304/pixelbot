package main.java.com.timelessapps.javafxtemplate.helpers.coords;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class RSCoordinates 
{
	public int offSetX = 481; //577 //96
	public int offSetY = 54; //43 //11
	public int existingUserButtonX = 1054;
	public int existingUserButtonY = 417;
	public int changeWorldButtonX = 552;
	public int changeWorldButtonY = 654;
	public int freeWorldButtonX = 1311; //World 464
	public int freeWorldButtonY = 348; //World 464
	public int clickHereToPlayButtonX = 962;
	public int clickHereToPlayButtonY = 448;
	
	public int grandExchangeClerkX = 1011; //Space, 2, space
	public int grandExchangeClerkY = 63;
	public int buyButton1X = 553; //634
	public int buyButton1Y = 214; //173
	public int sellButton1X = 621;
	public int sellButton1Y = 215;
	public int buyButton2X = 706; 
	public int buyButton2Y = 216;
	public int sellButton2X = 764;
	public int sellButton2Y = 215;
	public int buyButton3X = 844;
	public int buyButton3Y = 215;
	public int sellButton3X = 920;
	public int sellButton3Y = 214;
	
	public int historyButtonX = 535;
	public int historyButtonY = 77;
	public int firstHistoryRowPriceX = ;
	public int firstHistoryRowPriceY = ;
	public int secondHistoryRowPriceX = ;
	public int secondHistoryRowPriceY = ;
	
	public int firstSearchResultX = 517;
	public int firstSearchResultY = 510;
	public int secondSearchResultX = ;
	public int secondSearchResultY = ;
	public int decreasePriceX = ;
	public int decreasePriceY = ;
	public int increasePriceX = ;
	public int increasePriceY = ;
	public int confirmTradeButtonX = ;
	public int confirmTradeButtonY = ;
	public int firstInventorySlotX = ;
	public int firstInventorySlotY = ;
	
	public int queuedButton1X = ; 
	public int queuedButton1Y = ;
	public int progressBar1X = ;
	public int progressBar1Y = ;
	public int queuedButton2X = ; 
	public int queuedButton2Y = ;
	public int progressBar2X = ;
	public int progressBar2Y = ;
	public int queuedButton3X = ;
	public int queuedButton3Y = ;
	public int progressBar3X = ;
	public int progressBar3Y = ;
	
	public int cancelOrderButtonX = ;
	public int cancelOrderButtonY = ;
	public int firstSoldItemSlotX = ;
	public int firstSoldItemSlotY = ;
	public int secondSoldItemSlotX = ;
	public int secondSoldItemSlotY = ;
	public int collectButtonX = ;
	public int collectButtonY = ;
	public int redXButtonX = ;
	public int redXButtonY = ;
	
	public Rectangle clickHereToPlayButton = new Rectangle(917, 434, 171, 24); 
	public Rectangle existingUserButton = new Rectangle(997, 403, 131, 29); 
	
	public RSCoordinates()
	{
		
	}
	
	/**
	 * Finds the top left of the RS game area to use as offset. 
	 * @return the Y coordinate of the top of RS game area
	 * @throws AWTException 
	 */
	public Integer getYOffSet() throws AWTException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int middleX = (int) screenSize.getWidth()/2;
		int middleY = (int) screenSize.getHeight()/2;
		Robot bot = new Robot();
		int startingY = 0;
		
		//Checks for black area right under the moveable frame on top of a window
		for (int y = 0; y < middleY; y++)
		{
			int red = bot.getPixelColor(middleX, y).getRed();
			int green = bot.getPixelColor(middleX, y).getGreen();
			int blue = bot.getPixelColor(middleX, y).getBlue();
			//System.out.println(red + " " + green + " " + blue);
			
			if (red == 0 && green == 0 && blue == 0) //If black
			{
				startingY = y;
				break;
			}
		}
		
		for (int y = startingY; y < middleY; y++)
		{
			int red = bot.getPixelColor(middleX, y).getRed();
			int green = bot.getPixelColor(middleX, y).getGreen();
			int blue = bot.getPixelColor(middleX, y).getBlue();
			
			//System.out.println(red + " " + green + " " + blue);
			if (!(red == 0 && green == 0 && blue == 0)) //If not black
			{
				return y;
			}
		}
		return null;
	}
	
	/**
	 * Once you get the Y position (top of the game area) using getYOffSet() then pass it to this function to find the left of the game area. 
	 * @param startingY
	 * @return the X coordinate of the top of RS game area
	 * @throws AWTException
	 */
	public Integer getXOffSet(int startingY) throws AWTException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int middleX = (int) screenSize.getWidth()/2;
		Robot bot = new Robot();
		
		//Checks for black area right under the moveable frame on top of a window
		for (int x = 0; x < middleX; x++)
		{
			int red = bot.getPixelColor(x, startingY).getRed();
			int green = bot.getPixelColor(x, startingY).getGreen();
			int blue = bot.getPixelColor(x, startingY).getBlue();
			System.out.println(red + " " + green + " " + blue);
			
			if (!(red == 0 && green == 0 && blue == 0)) //If not black
			{
				return x;
			}
		}
		return null;
	}
	
	private int lookForFirstNonBlackPixelFromLeft(int startingY)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int middleX = (int) screenSize.getWidth()/2;
		Robot bot = new Robot();
		string rgbStringToFind = getrgbstring(r, g, b);
		string rgbStringResult = getrgbstring(r, g, b);
		
		int x = 0;
		int red = bot.getPixelColor(x, startingY).getRed();
		int green = bot.getPixelColor(x, startingY).getGreen();
		int blue = bot.getPixelColor(x, startingY).getBlue();
		
		int redRight = bot.getPixelColor(x+1, startingY).getRed();
		int greenRight = bot.getPixelColor(x+1, startingY).getGreen();
		int blueRight = bot.getPixelColor(x+1, startingY).getBlue();
		
		if (currentRGBString == rgbStringToFind)
		{
			if ()
			{
				return 
			}
			x = x/2;
		}
		else
		{
			x = x + ((middleX - x) /2);
		}
	}
}
