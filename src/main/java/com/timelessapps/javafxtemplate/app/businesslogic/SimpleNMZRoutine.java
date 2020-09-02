package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class SimpleNMZRoutine extends Routine{
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();
	
	int counter = 0;
	
	public SimpleNMZRoutine() throws AWTException
	{
		
	}
	
	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");

		System.out.println("Starting bot routine in 3 seconds. ");
		bot.delay(3000);

		synchronized (this) {
			try {
				disableNMZButton();
				Random random = new Random();

				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					int randomNumber = random.nextInt(27);
					String randomLetter = getRandomLetter(randomNumber);
					bot.mouseClick();
					Thread.sleep(random.nextInt(300) + 300);
					bot.mouseClick();
					counter++;
					System.out.println("Clicked");
					//TimeUnit.MINUTES.sleep(1);
					Thread.sleep(random.nextInt(30000) + 25000);
					checkIfPausedOrStopped();
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		if (counter > 200) {
			System.out.println("Counter is: " + counter + ". Stopping routine. ");
			running = false;
		}

		waitIfPaused();
		if (!running) {
			enableNMZButton();
		}

	}

	private void disableNMZButton() {
		try
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("simpleNMZButton").setDisable(true);
		}
		catch (Exception e)
		{
			
		}
	}

	private void enableNMZButton() {
		try
		{
			CustomSceneHelper sceneHelper = new CustomSceneHelper();
			sceneHelper.getNodeById("simpleNMZButton").setDisable(false);
		}
		catch (Exception e)
		{
			
		}
	}
	
	private String getRandomLetter(int x) {
		String letter = "0";
		switch (x) {
		case 0:
			letter = "1";
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
