package main.java.com.timelessapps.javafxtemplate.app.supportingthreads;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MeleeRoutine;
import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class AttackTimer extends Thread
{
	private RSCoordinates rsc = new RSCoordinates();
	private RobotService bot = new RobotService();
	private Random random = new Random();
	private Color hpArea = new Color(0,0,0);
	
	public AttackTimer() throws AWTException {
		
	}
	
	@Override
	public void run()
	{
		try 
		{
			while (hpArea.getRed() < 40)
			{
				//Check if hp is enough. Currently checking if halfway. 
				hpArea = bot.getPixelColor(rsc.middleHPX(), rsc.middleHPY()); //Halfway point for hp bar. 
				if (hpArea.getRed() < 40) // Red value is higher if the hp bar is filled, less than 40 is empty. 
				{
					System.out.println("Red part in HP area not detected, stopping routine. ");
					return; 
				}
				
				if (!MeleeRoutine.getIsResettingAggro())
				{
					int randomNumber = random.nextInt(27);
					String randomLetter = getRandomLetter(randomNumber);
					bot.type(randomLetter, random.nextInt(15) + 35);
					Thread.sleep(random.nextInt(1000) + 1000);
					bot.keyPress(KeyEvent.VK_BACK_SPACE);
					Thread.sleep(random.nextInt(40) + 40);
					bot.keyRelease(KeyEvent.VK_BACK_SPACE);
				}
				Thread.sleep(random.nextInt(120000) + 120000);
			}
			Thread.sleep(2000);
			return; 
		} catch (Exception e) 
		{
			System.out.println("AttackTimer was not able to function properly: " + e);
			return;
		}
	}
	
	private String getRandomLetter(int x) {
		String letter = "0";
		switch (x) {
		case 0:
			letter = "r";
			break;
		case 1:
			letter = "a";
			break;
		case 2:
			letter = "b";
			break;
		case 3:
			letter = "c";
			break;
		case 4:
			letter = "d";
			break;
		case 5:
			letter = "e";
			break;
		case 6:
			letter = "f";
			break;
		case 7:
			letter = "g";
			break;
		case 8:
			letter = "h";
			break;
		case 9:
			letter = "i";
			break;
		case 10:
			letter = "j";
			break;
		case 11:
			letter = "a";
			break;
		case 12:
			letter = "k";
			break;
		case 13:
			letter = "l";
			break;
		case 14:
			letter = "m";
			break;
		case 15:
			letter = "n";
			break;
		case 16:
			letter = "o";
			break;
		case 17:
			letter = "p";
			break;
		case 18:
			letter = "q";
			break;
		case 19:
			letter = "r";
			break;
		case 20:
			letter = "s";
			break;
		case 21:
			letter = "t";
			break;
		case 22:
			letter = "u";
			break;
		case 23:
			letter = "v";
			break;
		case 24:
			letter = "w";
			break;
		case 25:
			letter = "x";
			break;
		case 26:
			letter = "y";
			break;
		case 27:
			letter = "z";
			break;
		default:
			letter = " ";
			break;
		}
		return letter;
	}
}
