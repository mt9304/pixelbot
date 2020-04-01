package main.java.com.timelessapps.javafxtemplate.helpers.coords;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RSCoordinates 
{
	public int offsetX = 481; //577 //96
	public int offsetY = 53; //43 //11
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public int existingUserButtonX() {
		return getOffsetX + ;
	}
	public int existingUserButtonY() {
		return getOffsetY + ;
	}
	public int changeWorldButtonX() {
		return getOffSetX + ;
	}
	public int changeWorldButtonY() {
		return getOffSetY + ;
	}
	public int freeWorldButtonX() {
		return getOffSetX + ;
	}
	public int freeWorldButtonY() {
		return getOffSetY + ;
	}
	public int clickHereToPlayButtonX() {
		return getOffSetX + ;
	}
	public int clickHereToPlayButtonY() {
		return getOffSetY + ;
	}
	
	public int grandExchangeClerkX() {
		return getOffsetX + ;
	}
	public int grandExchangeClerkY() {
		return getOffsetY + ;
	}
	public int buyButton1X() {
		return getOffsetX + ;
	}
	public int buyButton1Y() {
		return getOffsetY + ;
	}
	public int sellButton1X() {
		return getOffsetX + ;
	}
	public int sellButton1Y() {
		return getOffsetY + ;
	}
	public int buyButton2X() {
		return getOffsetX + ;
	}
	public int buyButton2Y() {
		return getOffsetY + ;
	}
	public int sellButton2X() {
		return getOffsetX + ;
	}
	public int sellButton2Y() {
		return getOffsetY + ;
	}
	public int buyButton3X() {
		return getOffsetX + ;
	}
	public int buyButton3Y() {
		return getOffsetY + ;
	}
	public int sellButton3X() {
		return getOffsetX + ;
	}
	public int sellButton3Y() {
		return getOffsetY + ;
	}
	
	public int exchangeButtonX() { //Shows up in history screen. 
		return getOffsetX + ;
	}
	public int exchangeButtonY() {
		return getOffsetY + ;
	}
	public int historyButtonX() { //Shows up in exchange screen and default. 
		return getOffsetX + ;
	}
	public int historyButtonY() {
		return getOffsetY + ;
	}
	
	public int firstSearchResultX() {
		return getOffsetX + ;
	}
	public int firstSearchResultY() {
		return getOffsetY + ;
	}
	public int secondSearchResultX() {
		return getOffsetX + ;
	}
	public int secondSearchResultY() {
		return getOffsetY + ;
	}
	public int decreasePriceX() {
		return getOffsetX + ;
	}
	public int decreasePriceY() {
		return getOffsetY + ;
	}
	public int increasePriceX() {
		return getOffsetX + ;
	}
	public int increasePriceY() {
		return getOffsetY + ;
	}
	public int specifyPriceButtonX() {
		return getOffsetX + ;
	}
	public int speciftPriceButtonY() {
		return getOffsetY + ;
	}
	public int confirmTradeButtonX() {
		return getOffsetX + ;
	}
	public int confirmTradeButtonY() {
		return getOffsetY + ;
	}
	public int firstInventorySlotX() {
		return getOffsetX + ;
	}
	public int firstInventorySlotY() {
		return getOffsetY + ;
	};
	public int secondInventorySlotX() {
		return getOffsetX + ;
	}
	public int secondInventorySlotY() {
		return getOffsetY + ;
	}
	public int thirdInventorySlotX() {
		return getOffsetX + ;
	}
	public int thirdInventorySlotY() {
		return getOffsetY + ;
	}
	
	public int queuedButton1X() {
		return getOffsetX + ;
	}
	
	public int queuedButton1Y() {
		return getOffsetY + ;
	}
	
	public int progressBar1X() {
		return getOffsetX + ;
	}
	
	public int progressBar1Y() {
		return getOffsetY + ;
	}
	
	public int queuedButton2X() {
		return getOffsetX + ;
	}
	
	public int queuedButton2Y() {
		return getOffsetY + ;
	}
	
	public int progressBar2X() {
		return getOffsetX + ;
	}
	
	public int progressBar2Y() {
		return getOffsetY + ;
	}
	
	public int queuedButton3X() {
		return getOffsetX + ;
	}
	
	public int queuedButton3Y() {
		return getOffsetY + ;
	}
	
	public int progressBar3X() {
		return getOffsetX + ;
	}
	
	public int progressBar3Y() {
		return getOffsetY + ;
	}
	
	public int cancelOrderButtonX() {
		return getOffsetX + ;
	}
	
	public int cancelOrderButtonY() {
		return getOffsetY + ;
	}
	
	public int firstSoldItemSlotX() {
		return getOffsetX + ;
	}
	
	public int firstSoldItemSlotY() {
		return getOffsetY + ;
	};
	
	public int secondSoldItemSlotX() {
		return getOffsetX + ;
	}
	
	public int secondSoldItemSlotY() {
		return getOffsetY + ;
	}
	public int collectButtonX() {
		return getOffsetX + ;
	}
	public int collectButtonY() {
		return getOffsetY + ;
	}
	public int redXButtonX() {
		return getOffsetX + ;
	}
	public int redXButtonY() {
		return getOffsetY + ;
	}

	public Rectangle firstHistoryRowPrice() {
		//Coords
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle secondHistoryRowPrice() {
		//Coords
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle clickHereToPlayButton() {
		//Coords
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle existingUserButton() {
		//Coords
		Rectangle result = new Rectangle();
		return result;
	}
	
	public RSCoordinates()
	{
		Rectangle screen = new Rectangle(945,117,60,20);
		BufferedImage capture = null;
		try {
			capture = new Robot().createScreenCapture(screen);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File output = new File("C:\\Users\\Max\\Desktop\\test2.png");
		try {
			ImageIO.write(capture, "png", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds the top of the RS game area to use for calculating game coordinate offsets. 
	 * @return the Y coordinate of the top of RS game area
	 * @throws AWTException 
	 */
	public Integer getInitialOffsetY() throws AWTException
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
	public Integer getInitialOffsetX(int y) throws AWTException {
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
