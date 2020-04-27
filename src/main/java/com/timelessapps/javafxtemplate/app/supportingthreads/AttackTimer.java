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

public class AttackTimer
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
	
	public void test()
	{
		try 
		{
			/*
			while (killCount < numberOfMonstersToKill)
			{
				attackText = rsir.getYellowRSText(rsc.topLeftText()).trim();
				System.out.println("Text: " + attackText);
				if (attackText.equals("Minotaur") || attackText.equals("Wolf"))
				{
					bot.mouseClick();
					Thread.sleep(random.nextInt(10000) + 10000);
					killCount++;
				}
			}
			*/
			Thread.sleep(2000);
			bot.moveCursorSlowlyTo(577, 43);
			return; 
		} catch (Exception e) 
		{
			System.out.println("Could not get attackText with AttackTimer. " + e);
			return;
		}
	}
}
