package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.BuffTimer;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.X;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.Y;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots.BOOK;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class HumidifyRoutine extends Routine {
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();
	
	int numberOfClay = 10000; //Will decrement by 27 each inv. 

	int[] bankX = { 788,800,812 };
	int[] bankY = { 391,395,410 };
	
	int[] closeBankX = { 1084,1093 };
	int[] closeBankY = { 158,160 };
	
	int clayX[] = { 646,654 };
	int clayY[] = { 254,255 };
	
	int humidifyX = 1282;
	int humidifyY = 435;
	
	//These are closer to the bank tab and are usually the slots being clicked to deposit. 
	int[][] firstPriorityClaySlots = 
		{
				{1203, 418},{1257, 417},{1307, 417},{1201, 463},{1256, 462},{1204, 508},{1256, 508}
		};
	
	int[][] secondPriorityClaySlots = 
		{
				{1363, 416},{1311, 466},{1312, 505},{1308, 551},{1256, 554},{1204, 554}
		};
	
	int[][] thirdPriorityClaySlots = 
		{
				{1364, 643},{1362, 554},{1203, 640},{1255, 642},{1202, 685},{1255, 687}
		};
	
	//Indicator for bank tab being open. Looks for the All button to be selected and red. Also needed for routine to work as well. 
	//Red is: r129 g31 b29 and Grey is: r82 g82 b78
	int allButtonX = 891;
	int allButtonY = 540;
	
	boolean bankIsOpen = false;

	public HumidifyRoutine() throws AWTException {

	}

	public void run() {
		try {
			log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("Starting bot routine in 3 seconds. ");
		bot.delay(3000);

		synchronized (this) {
			try {
				disableHumidifyButton();
				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					// F5 = Inv, F6 = Equipment, F7 = SpellBook.
					// Move to starting spot. Open spell book, stand at Edgeville bank, select All for bank option, select clay tab so normal clay is second slot, make astral runes in the last inv slot. 
					bot.delay(250);
					
					castHumidify();
					openBank();
					while (!bankIsOpen) {
						checkIfBankOpen();
					}
					depositSoftClay();
					withdrawClay();
					closeBank();

					/** End routine here. **/
					bot.delay(random.nextInt(250) + 250);
					numberOfClay -= 27;
					randomlyTakeBreak();
					checkIfPausedOrStopped();
					
					//If no more clay left, sleep the machine and exit. 
					if (numberOfClay <= 0) {
						bot.moveCursorTo(37, 1047);
						bot.delay(Duration.MEDIUM);
						bot.mouseClick();
						
						bot.moveCursorTo(39, 983);
						bot.delay(Duration.MEDIUM);
						bot.mouseClick();
						
						bot.moveCursorTo(38, 801);
						bot.delay(Duration.MEDIUM);
						bot.mouseClick();
						System.exit(0);
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		waitIfPaused();
		if (!running) {
			enableHumidifyButton();
		}

	}

	private void disableHumidifyButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("humidifyButton").setDisable(true);
	}

	private void enableHumidifyButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("humidifyButton").setDisable(false);
	}
	
	private void randomlyTakeBreak() {
		if (random.nextInt(204) == 25) {
			System.out.println("Taking a random break. ");
			bot.moveCursorTo(random.nextInt(50) + 1700, random.nextInt(300) + 300);
			try {
				Thread.sleep(random.nextInt(4000) + 4000);
			} catch (InterruptedException e) {
				System.out.println("Randomly Taking Break ran into an error. "+e);
			}
			
			bot.moveCursorTo((random.nextInt(800) + 500), (random.nextInt(500) + 170));
			bot.delay(random.nextInt(500) + 500);
		}
	}
	
	private void castHumidify() {
		System.out.println("Casting Humidify. ");
		bot.delay(Duration.SHORT);
		bot.moveCursorTo(humidifyX, humidifyY);
		bot.delay(Duration.SHORT);
		bot.mouseClick();
		bot.delay(Duration.MEDIUM);
	}
	
	private void openBank() {
		System.out.println("Opening bank. ");
		int rand = random.nextInt(2);
		bot.delay(Duration.SHORT);
		bot.moveCursorTo(bankX[rand], bankY[rand]);
		bot.delay(Duration.SHORT);
		bot.mouseClick();
		bot.delay(Duration.MEDIUM);
	}
	
	private void moveToInvSlots() {
		int priority = random.nextInt(200);
		System.out.println("Moving to inv slot. Rolled " + priority);
		bot.delay(Duration.SHORT);
		
		if (priority < 150) {
			//First priority slot. 
			int slot = random.nextInt(6);
			bot.moveCursorTo(firstPriorityClaySlots[slot][0], firstPriorityClaySlots[slot][1]);
			
		} else if (priority >= 150 && priority <= 195) {
			//Second priority slot. 
			int slot = random.nextInt(5);
			bot.moveCursorTo(secondPriorityClaySlots[slot][0], secondPriorityClaySlots[slot][1]);
		} else if (priority > 195) {
			//Third priority slot. 
			int slot = random.nextInt(5);
			bot.moveCursorTo(thirdPriorityClaySlots[slot][0], thirdPriorityClaySlots[slot][1]);
		}
		
		bot.delay(Duration.MEDIUM);
	}
	
	private void depositSoftClay() {
		System.out.println("Depositing the soft clay. ");
		moveToInvSlots();
		bot.delay(Duration.SHORT);
		bot.mouseClick();
		bot.delay(Duration.MEDIUM);
	}
	
	private void withdrawClay() {
		System.out.println("Withdrawing clay. ");
		int rand = random.nextInt(1);
		bot.delay(Duration.SHORT);
		bot.moveCursorTo(clayX[rand], clayY[rand]);
		bot.delay(Duration.SHORT);
		bot.mouseClick();
		bot.delay(Duration.MEDIUM);
	}
	
	private void closeBank() {
		System.out.println("Closing bank. ");
		int rand = random.nextInt(1);
		bot.delay(Duration.SHORT);
		bot.moveCursorTo(closeBankX[rand], closeBankY[rand]);
		bot.delay(Duration.SHORT);
		bot.mouseClick();
		bot.delay(Duration.SHORT);
		bankIsOpen = false;
	}
	
	private void checkIfBankOpen() {
		System.out.println("Checking if bank is open. ");
		int redValue = bot.getPixelColor(allButtonX, allButtonY).getRed();
		//System.out.println("RED is: " + redValue);
		if (redValue > 100) {
			bankIsOpen = true;
			System.out.println("Bank is open. ");
		} else {
			System.out.println("Bank is not open, waiting. ");
			bot.delay(Duration.MEDIUM);
			bot.mouseClick();
			bot.delay(Duration.SHORT);
		}
	}

}
