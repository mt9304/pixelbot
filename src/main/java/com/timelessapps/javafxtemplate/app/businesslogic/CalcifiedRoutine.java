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
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots.BOOK;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class CalcifiedRoutine extends Routine {
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();

	// Run in main monitor, runelite exp tab showing. 
	int numberToClick = 276;
	int alchX = 1372; // 1369
	int alchY = 520; // 546

	int equippedArrowSlotX = 1334;
	int equippedArrowSlotY = 463;
 
	int blankInvSlotX = 1165;
	int blankInvSlotY = 416;

	int firstInvSlotX = 0;
	int firstInvSlotY = 0;

	// Superheat item red part.
	int bookIndicatorX = 1313;
	int bookIndicatorY = 518;

	volatile Boolean bookStillLoading = true;

	public CalcifiedRoutine() throws AWTException {

	}

	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");

		System.out.println("Starting bot routine in 5 seconds. ");
		bot.delay(5000);

		synchronized (this) {
			try {
				disableAlchButton();
				while (running) {
					checkIfPausedOrStopped();
                                        
                                        //if (isMining()) {
                                        //  if (!veinIsDepleted()) {
                                        //      veinIsDepleted = veinIsDepleted();
                                        //  {
                                        //{ else {
                                        //
                                         //{
                                        //Verifies hovering over knight of ardy
                                        int currentRed = bot.getPixelColor(530, 37).getRed();
                                        int currentGreen = bot.getPixelColor(530, 37).getGreen();
                                        int currentBlue = bot.getPixelColor(530, 37).getBlue();

                                        int currentRed2 = bot.getPixelColor(519, 37).getRed();
                                        int currentGreen2 = bot.getPixelColor(519, 37).getGreen();
                                        int currentBlue2 = bot.getPixelColor(519, 37).getBlue();

                                        //Health bar red
                                        int currentRed3 = bot.getPixelColor(992, 89).getRed();
                                        int currentGreen3 = bot.getPixelColor(992, 89).getGreen();
                                        int currentBlue3 = bot.getPixelColor(992, 89).getBlue();

                                        int currentRed4 = bot.getPixelColor(451, 37).getRed();
                                        int currentGreen4 = bot.getPixelColor(451, 37).getGreen();
                                        int currentBlue4 = bot.getPixelColor(451, 37).getBlue();

                                        //if (currentRed >= 207 && currentGreen >= 205 && currentBlue >= 0 && currentRed2 >= 207 && currentGreen2 >=205 && currentBlue2 >= 204 && currentRed3 > 80) {
                                        if (currentRed4 >= 190 && currentGreen4 >= 190 && currentBlue4 >= 190 && currentRed3 > 80) {
                                            bot.mouseClick();
                                            bot.delay(random.nextInt(378) + 546);

                                            numberToClick--;
                                        } else {
                                            //System.out.println("currentRed 207:" + currentRed + " | currentGreen 205:" + currentGreen + " | currentBlue 0:" + currentBlue);
                                            //System.out.println("currentRed2 207:" + currentRed2 + " | currentGreen2 205:" + currentGreen2 + " | currentBlue2 204:" + currentBlue2);
                                            //System.out.println("currentRed3 80:" + currentRed3 + " | currentGreen3:" + currentGreen3 + " | currentBlue3:" + currentBlue3);
                                            System.out.println("currentRed4 210:" + currentRed4 + " | currentGreen4 211:" + currentGreen4 + " | currentBlue4 209:" + currentBlue4);
                                            System.out.println("Not clicking, either not mousing over knight or hp low. ");
                                            bot.delay(random.nextInt(2500) + 2500);
                                        }
 
                                        
                                        
					checkIfPausedOrStopped();
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		if (numberToClick <= 0) {
			System.out.println("Preparing to shut down. ");
			running = false;
                        /*
			// For sleeping computer.
			bot.delay(1000);
			bot.moveCursorTo(35, 1050);
			bot.delay(1000);
			bot.mouseClick();
			bot.delay(1000);
			bot.moveCursorTo(35, 985);
			bot.delay(1000);
			bot.mouseClick();
			bot.delay(1000);
			bot.moveCursorTo(35, 811);
			bot.delay(1000);
			bot.mouseClick();
                        */
		}

		waitIfPaused();
		if (!running) {
			enableAlchButton();
		}

	}

	private void disableAlchButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("alchButton").setDisable(true);
	}

	private void enableAlchButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("alchButton").setDisable(false);
	}

	private void moveToAlchSpot() {

	}

	private void checkIfOnMagicScreen() {

	}

	private void checkIfArrowIsInPlace() {

	}

	private void unequiptArrowAndPutBackInPlace() {

	}

	private void switchTo(Slots slot) {
		switch (slot) {
		case INV:
			bot.keyPress(KeyEvent.VK_F5);
			bot.delay(random.nextInt(20) + 10);
			bot.keyRelease(KeyEvent.VK_F5);
			break;
		case EQUIP:
			bot.keyPress(KeyEvent.VK_F6);
			bot.delay(random.nextInt(20) + 10);
			bot.keyRelease(KeyEvent.VK_F6);
			break;
		case BOOK:
			bot.keyPress(KeyEvent.VK_F7);
			bot.delay(random.nextInt(20) + 10);
			bot.keyRelease(KeyEvent.VK_F7);
		}
	}

	private void checkIfStillCasting() {
		while (bookStillLoading) {
			if (bot.getPixelColor(bookIndicatorX, bookIndicatorY).getBlue() < 45) {
				// System.out.println("Detected. Superheat item blue value is: " +
				// bot.getPixelColor(bookIndicatorX, bookIndicatorY).getBlue());
				bookStillLoading = false;
			} else {
				// System.out.println("Not detected. Superheat item blue value is: " +
				// bot.getPixelColor(bookIndicatorX, bookIndicatorY).getBlue());
				bot.delay(500);
			}
		}
	}
}
