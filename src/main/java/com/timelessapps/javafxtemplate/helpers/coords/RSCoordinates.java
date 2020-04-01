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
	public int offsetX = 577;
	public int offsetY = 43;
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public int existingUserButtonX() {
		return getOffsetX() + 459;
	}
	
	public int existingUserButtonY() {
		return getOffsetY() + 291;
	}
	
	public int changeWorldButtonX() {
		return getOffsetX() + 54;
	}
	
	public int changeWorldButtonY() {
		return getOffsetY() + 481;
	}
	
	public int freeWorldButtonX() { // World 474
		return getOffsetX() + 660;
	}
	
	public int freeWorldButtonY() { // World 474
		return getOffsetY() + 272;
	}

	public int clickHereToPlayButtonX() {
		return getOffsetX() + 389;
	}
	
	public int clickHereToPlayButtonY() {
		return getOffsetY() + 317;
	}
	
	public int grandExchangeClerkX() {
		return getOffsetX() + 340;
	}
	
	public int grandExchangeClerkY() {
		return getOffsetY() + 8;
	}
	
	public int buyButton1X() {
		return getOffsetX() + 55;
	}
	
	public int buyButton1Y() {
		return getOffsetY() + 130;
	}
	
	public int sellButton1X() {
		return getOffsetX() + 108;
	}
	
	public int sellButton1Y() {
		return getOffsetY() + 130;
	}
	
	public int buyButton2X() {
		return getOffsetX() + 169;
	}
	
	public int buyButton2Y() {
		return getOffsetY() + 130;
	}

	public int sellButton2X() {
		return getOffsetX() + 225;
	}
	
	public int sellButton2Y() {
		return getOffsetY() + 130;
	}

	public int buyButton3X() {
		return getOffsetX() + 288;
	}
	
	public int buyButton3Y() {
		return getOffsetY() + 130;
	}
	
	public int sellButton3X() {
		return getOffsetX() + 341;
	}
	
	public int sellButton3Y() {
		return getOffsetY() + 130;
	}
	//
	public int historyButtonX() { //Shows up in exchange screen and default. 
		return getOffsetX() + 45;
	}
	public int historyButtonY() {
		return getOffsetY() + 17;
	}
	
	public int exchangeButtonX() { //Shows up in history screen. 
		return getOffsetX() + 62;
	}
	
	public int exchangeButtonY() {
		return getOffsetY() + 19;
	}
	
	public int firstSearchResultX() {
		return getOffsetX() + 61;
	}
	
	public int firstSearchResultY() {
		return getOffsetY() + 364;
	}
	//
	public int secondSearchResultX() {
		return getOffsetX() + 225;
	}
	
	public int secondSearchResultY() {
		return getOffsetY() + 364;
	}
	
	public int decreasePriceX() {
		return getOffsetX() + 289;
	}
	
	public int decreasePriceY() {
		return getOffsetY() + 194;
	}
	
	public int increasePriceX() {
		return getOffsetX() + 446;
	}
	
	public int increasePriceY() {
		return getOffsetY() + 193;
	}
	
	public int specifyPriceButtonX() {
		return getOffsetX() + 388;
	}
	
	public int speciftPriceButtonY() {
		return getOffsetY() + 194;
	}
	//
	public int confirmTradeButtonX() {
		return getOffsetX() + 256;
	}
	
	public int confirmTradeButtonY() {
		return getOffsetY() + 269;
	}
	
	public int firstInventorySlotX() {
		return getOffsetX() + 575;
	}
	
	public int firstInventorySlotY() {
		return getOffsetY() + 210;
	};
	
	public int secondInventorySlotX() {
		return getOffsetX() + 618;
	}
	
	public int secondInventorySlotY() {
		return getOffsetY() + 209;
	}
	
	public int thirdInventorySlotX() {
		return getOffsetX() + 662;
	}
	
	public int thirdInventorySlotY() {
		return getOffsetY() + 208;
	}
	
	public int queuedButton1X() {
		return getOffsetX() + 83;
	}
	
	public int queuedButton1Y() {
		return getOffsetY() + 124;
	}
	//
	public int progressBar1X() {
		return getOffsetX() + 47;
	}
	
	public int progressBar1Y() {
		return getOffsetY() + 146;
	}
	
	public int queuedButton2X() {
		return getOffsetX() + 200;
	}
	
	public int queuedButton2Y() {
		return getOffsetY() + 124;
	}
	
	public int progressBar2X() {
		return getOffsetX() + 165;
	}
	
	public int progressBar2Y() {
		return getOffsetY() + 146;
	}
	
	public int queuedButton3X() {
		return getOffsetX() + 316;
	}
	
	public int queuedButton3Y() {
		return getOffsetY() + 124;
	}
	
	public int progressBar3X() {
		return getOffsetX() + 282;
	}
	
	public int progressBar3Y() {
		return getOffsetY() + 146;
	}
	
	public int cancelOrderButtonX() {
		return getOffsetX() + 356;
	}
	
	public int cancelOrderButtonY() {
		return getOffsetY() + 258;
	}
	
	public int firstSoldItemSlotX() {
		return getOffsetX() + 413;
	}
	
	public int firstSoldItemSlotY() {
		return getOffsetY() + 267;
	};
	//
	public int secondSoldItemSlotX() { //Should be gold here
		return getOffsetX() + 463;
	}
	
	public int secondSoldItemSlotY() { //Should be gold here
		return getOffsetY() + 270;
	}
	
	public int backButtonX() { //White arrow to go back to exchange screen
		return getOffsetX() + 50;
	}
	
	public int backButtonY() { //White arrow to go back to exchange screen
		return getOffsetY() + 268;
	}
	
	public int collectButtonX() {
		return getOffsetX() + 450;
	}
	
	public int collectButtonY() {
		return getOffsetY() + 50;
	}
	
	public Rectangle firstHistoryRowPrice() {
		//Coords 353 + x , 55 + y , 73, 8
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle secondHistoryRowPrice() {
		//Coords 353 + x , 93 + y , 73, 8
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle clickHereToPlayButton() {
		//Coords 349 + x , 306 + y , 138, 23
		Rectangle result = new Rectangle();
		return result;
	}
	
	public Rectangle existingUserButton() {
		//Coords 407 + x , 260 + y , 100, 20
		Rectangle result = new Rectangle();
		return result;
	}

	public RSCoordinates()
	{
		/*
		Rectangle screen = new Rectangle(945,117,60,20);
		BufferedImage capture = null;
		try {
			capture = new Robot().createScreenCapture(screen);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		File output = new File("C:\\Users\\Max\\Desktop\\test2.png");
		try {
			ImageIO.write(capture, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
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
