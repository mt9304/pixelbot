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
	 * Finds the top of the RS game area to use for calculating game coordinate offsets. 
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
		//Checks for game area under the black area
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
	public Integer getXOffset(int y) throws AWTException {
		Robot bot = new Robot();
		bot.getPixelColor(0, 0);
		
		int minX = 0;
		int maxX = 1000; //Screens are 1920x1080, so 1000 is around the halfway mark of the screen. 
		int midpointX = minX + ((maxX-minX)/2);
		Color black = new Color(0,0,0);
		
		//Binary search style scanning of screen for the starting X coordinate of game area. 
		for (int i = 0; i < 1000; i++) //Set to 1000 because it should not take over 1000 iterations to find it due to screen size. 
		{
			//System.out.println(i);
			Color currentColor = bot.getPixelColor(midpointX, y);
			if (isSameColor(currentColor, black))
			{
				Color rightOfCurrentColor = bot.getPixelColor(midpointX+1, y);
				if (!isSameColor(rightOfCurrentColor, black))
				{
					return midpointX+1;
				}
				else
				{
					minX = midpointX;
					midpointX = minX + ((maxX-minX)/2);
				}
				System.out.println("Current X is black: " + midpointX);
			}
			else if (!isSameColor(currentColor, black))
			{
				Color leftOfCurrentColor = bot.getPixelColor(midpointX-1, y);
				if (isSameColor(leftOfCurrentColor, black))
				{
					return midpointX;
				}
				else
				{
					maxX = midpointX;
					midpointX = minX + ((maxX-minX)/2);
				}
				System.out.println("Current X is not black: " + midpointX);
			}
			
		}
		return null;
	}
	
	public boolean isSameColor(Color firstColor, Color secondColor)
	{
		if (firstColor.getRed() == secondColor.getRed())
		{
			if  (firstColor.getGreen() == secondColor.getGreen())
			{
				if (firstColor.getBlue() == secondColor.getBlue())
				{
					return true;
				}
			}
		} 
		else 
		{
			return false;
		}
		return false;
	}

}
