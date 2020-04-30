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

import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;

public class RSCoordinates 
{
	public static int offsetX = 577;
	public static int offsetY = 43;
	
	public static void setOffsetX(int x) {
		offsetX = x;
	}
	
	public static void setOffsetY(int y) {
		offsetY = y;
	}
	
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
		return getOffsetY() + 232;
	}

	public int clickHereToPlayButtonX() {
		return getOffsetX() + 389;
	}
	
	public int clickHereToPlayButtonY() {
		return getOffsetY() + 317;
	}
	
	public int grandExchangeClerkX() {
		return getOffsetX() + 474;
	}
	
	public int grandExchangeClerkY() {
		return getOffsetY() + 8;
	}
	
	private int buyButton1X() {
		return getOffsetX() + 55;
	}
	
	private int buyButton1Y() {
		return getOffsetY() + 130;
	}
	
	private int sellButton1X() {
		return getOffsetX() + 111;
	}
	
	private int sellButton1Y() {
		return getOffsetY() + 118;
	}
	
	private int buyButton2X() {
		return getOffsetX() + 169;
	}
	
	private int buyButton2Y() {
		return getOffsetY() + 130;
	}

	private int sellButton2X() {
		return getOffsetX() + 228;
	}
	
	private int sellButton2Y() {
		return getOffsetY() + 117;
	}

	private int buyButton3X() {
		return getOffsetX() + 288;
	}
	
	private int buyButton3Y() {
		return getOffsetY() + 130;
	}
	
	private int sellButton3X() {
		return getOffsetX() + 345;
	}
	
	private int sellButton3Y() {
		//922, 161
		return getOffsetY() + 118;
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
	
	public int specifyPriceButtonY() {
		return getOffsetY() + 194;
	}
	
	public int specifyQuantityButtonX() {
		return getOffsetX() + 233;
	}
	
	public int specifyQuantityButtonY() {
		return getOffsetY() + 191;
	}
	
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
	
	private int queuedButton1X() {
		return getOffsetX() + 83;
	}
	
	private int queuedButton1Y() {
		return getOffsetY() + 124;
	}
	//
	private int progressBar1X() {
		return getOffsetX() + 47;
	}
	
	private int progressBar1Y() {
		return getOffsetY() + 146;
	}
	
	private int queuedButton2X() {
		return getOffsetX() + 200;
	}
	
	private int queuedButton2Y() {
		return getOffsetY() + 124;
	}
	
	private int progressBar2X() {
		return getOffsetX() + 165;
	}
	
	private int progressBar2Y() {
		return getOffsetY() + 146;
	}
	
	private int queuedButton3X() {
		return getOffsetX() + 316;
	}
	
	private int queuedButton3Y() {
		return getOffsetY() + 124;
	}
	
	private int progressBar3X() {
		return getOffsetX() + 282;
	}
	
	private int progressBar3Y() {
		return getOffsetY() + 146;
	}
	
	public int largeProgressBarX() { //The progress bar at the bottom when clicking a slot
		return getOffsetX() + 210;
	}
	
	public int largeProgressBarY() { //The progress bar at the bottom when clicking a slot
		return getOffsetX() + 286;
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
	
	//Top left text that appears when you hover over an inventory item while GE menu open. If there is an item, pixel here should be 206, 206, 206
	public int offerTextX() {
		return getOffsetX() + 19;
	}
	
	//Top left text that appears when you hover over an inventory item while GE menu open. If there is an item, pixel here should be 206, 206, 206
	public int offerTextY() {
		return getOffsetY() + 1;
	}
	
	public int cancelOrderRedPixelX() {
		return getOffsetX() + 359;
	}
	
	public int cancelOrderRedPixelY() {
		return getOffsetY() + 260;
	}
	
	public int middleHPX() {
		return getOffsetX() + 546;
	}
	
	public int middleHPY() {
		return getOffsetY() + 36;
	}
	
	public Rectangle firstHistoryRowPrice() {
		//Coords 353 + x , 55 + y , 73, 8
		Rectangle result = new Rectangle(359 + getOffsetX(), 55 + getOffsetY(), 64, 8);
		return result;
	}
	
	public Rectangle secondHistoryRowPrice() {
		//Coords 353 + x , 93 + y , 73, 8
		Rectangle result = new Rectangle(359 + getOffsetX(), 93 + getOffsetY(), 64, 8);
		return result;
	}
	
	public Rectangle clickHereToPlayButton() {
		//Coords 349 + x , 306 + y , 138, 23
		Rectangle result = new Rectangle(353 + getOffsetX(), 309 + getOffsetY(), 129, 14);
		return result;
	}
	
	public Rectangle existingUserButton() {
		//Coords 407 + x , 260 + y , 100, 20
		//992, 326, 95, 16
		Rectangle result = new Rectangle(415 + getOffsetX(), 283 + getOffsetY(), 95, 16);
		System.out.println(415 + getOffsetX() + ", " + 283 + getOffsetY());
		return result;
	}
	
	public Rectangle mainGrandExchangeText() {
		Rectangle result = new Rectangle(202 + getOffsetX(), 9 + getOffsetY(), 113, 18);
		return result;
	}
	
	public Rectangle buySearchAreaText() {
		Rectangle result = new Rectangle(162 + getOffsetX(), 326 + getOffsetY(), 186, 18);
		return result;
	}
	
	public Rectangle sellOfferText() {
		Rectangle result = new Rectangle(77 + getOffsetX(), 43 + getOffsetY(), 78, 18);
		return result;
	}
	
	public Rectangle tradeHistoryText() {
		Rectangle result = new Rectangle(266 + getOffsetX(), 10 + getOffsetY(), 95, 18);
		return result;
	}
	
	public Rectangle topLeftText() {
		Rectangle result = new Rectangle(3 + getOffsetX(), -18 + getOffsetY(), 320, 24);
		return result;
	}
	
	public Rectangle question1Of2() {
		Rectangle result = new Rectangle(47 + getOffsetX(), 367 + getOffsetY(), 425, 24);//
		return result;
	}
	
	public Rectangle question2Of2() {
		Rectangle result = new Rectangle(47 + getOffsetX(), 401 + getOffsetY(), 425, 24);//
		return result;
	}
	
	public Rectangle question1Of3() {
		Rectangle result = new Rectangle(47 + getOffsetX(), 358 + getOffsetY(), 425, 24);
		return result;
	}
	
	public Rectangle question2Of3() {
		Rectangle result = new Rectangle(47 + getOffsetX(), 385 + getOffsetY(), 425, 24);
		return result;
	}
	
	public Rectangle question3Of3() {
		Rectangle result = new Rectangle(47 + getOffsetX(), 408 + getOffsetY(), 425, 29);
		return result;
	}
	
	public int[] buyButtonX() {
		int[] slots = new int[3];
		slots[0] = buyButton1X();
		slots[1] = buyButton2X();
		slots[2] = buyButton3X();
		return slots;
	}
	
	public int[] buyButtonY() {
		int[] slots = new int[3];
		slots[0] = buyButton1Y();
		slots[1] = buyButton2Y();
		slots[2] = buyButton3Y();
		return slots;
	}
	
	public int[] sellButtonX() {
		int[] slots = new int[3];
		slots[0] = sellButton1X();
		slots[1] = sellButton2X();
		slots[2] = sellButton3X();
		return slots;
	}
	
	public int[] sellButtonY() {
		int[] slots = new int[3];
		slots[0] = sellButton1Y();
		slots[1] = sellButton2Y();
		slots[2] = sellButton3Y();
		return slots;
	}
	
	public int[] progressBarX() {
		int[] slots = new int[3];
		slots[0] = progressBar1X();
		slots[1] = progressBar2X();
		slots[2] = progressBar3X();
		return slots;
	}
	
	public int[] progressBarY() {
		int[] slots = new int[3];
		slots[0] = progressBar1Y();
		slots[1] = progressBar2Y();
		slots[2] = progressBar3Y();
		return slots;
	}
	
	public int[] queuedButtonX() {
		int[] slots = new int[3];
		slots[0] = queuedButton1X();
		slots[1] = queuedButton2X();
		slots[2] = queuedButton3X();
		return slots;
	}
	
	public int[] queuedButtonY() {
		int[] slots = new int[3];
		slots[0] = queuedButton1Y();
		slots[1] = queuedButton2Y();
		slots[2] = queuedButton3Y();
		return slots;
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
	public static Integer getInitialOffsetY() throws AWTException
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
	public static Integer getInitialOffsetX(int y) throws AWTException {
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
			if (RSImageReader.isSameColor(currentColor, black))
			{
				Color rightOfCurrentColor = bot.getPixelColor(midpointX+1, y);
				if (!RSImageReader.isSameColor(rightOfCurrentColor, black))
				{
					return midpointX+1;
				}
				else
				{
					minX = midpointX;
					midpointX = minX + ((maxX-minX)/2);
				}
			}
			else if (!RSImageReader.isSameColor(currentColor, black))
			{
				Color leftOfCurrentColor = bot.getPixelColor(midpointX-1, y);
				if (RSImageReader.isSameColor(leftOfCurrentColor, black))
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
}
