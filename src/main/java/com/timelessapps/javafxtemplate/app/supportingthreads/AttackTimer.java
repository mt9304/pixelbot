package main.java.com.timelessapps.javafxtemplate.app.supportingthreads;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class AttackTimer extends Thread
{
	private RSImageReader rsir = new RSImageReader();
	private RSCoordinates rsc = new RSCoordinates();
	private String attackText = "";
	private RobotService bot = new RobotService();
	private Random random = new Random();
	private int numberOfMonstersToKill = 100;
	private int killCount = 0;
	
	public AttackTimer() throws AWTException {
		
	}
	
	@Override
	public void run()
	{
		try 
		{
			while (killCount < numberOfMonstersToKill)
			{
				attackText = rsir.getYellowRSText(rsc.topLeftText()).trim();
				System.out.println("Text: " + attackText);
				if (attackText.contains("Min") || attackText.equals("Wolf")) //Minotaur
				{
					bot.mouseClick();
					Thread.sleep(random.nextInt(4000) + 4000);
					killCount++;
				}
			}
			Thread.sleep(2000);
			return; 
		} catch (Exception e) 
		{
			System.out.println("AttackTimer was not able to function properly: " + e);
			return;
		}
	}
}
