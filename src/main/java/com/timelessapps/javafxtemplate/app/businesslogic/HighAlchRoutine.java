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

public class HighAlchRoutine extends Routine {
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();

	// For arrows, remember to include more in inv than stated number, otherwise
	// stack will shrink and pixel detector may be off.
	int numberToAlch = 800;
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

	public HighAlchRoutine() throws AWTException {

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
                                        
					/** Start routine here. **/
					// F5 = Inv, F6 = Equipment, F7 = SpellBook.
					// Move to starting spot.
                                        /* //## Uncomment section if reverting back to old routine. Temporarily changing. 
					bookStillLoading = true;
					bot.delay(250);
					// System.out.println("Moving to alch spot. ");
					if (!((bot.getCurrentMousePosition(X) >= alchX - 2 && bot.getCurrentMousePosition(X) <= alchX + 2)
							&& (bot.getCurrentMousePosition(Y) >= alchY - 2
									&& bot.getCurrentMousePosition(Y) <= alchY + 2))) {
						bot.accuratelyMoveCursor(alchX, alchY);
						bot.delay(random.nextInt(500) + 500);
					}

					// For random taking a break and readjusting mouse.
					if (random.nextInt(130) == 25) {
						System.out.println("Moving mouse. ");
						bot.moveCursorTo(random.nextInt(50) + 1700, random.nextInt(300) + 300);
						Thread.sleep(random.nextInt(4000) + 4000);
						bot.accuratelyMoveCursor(alchX, alchY);
						bot.delay(random.nextInt(500) + 500);
					}

					// For readjusting mouse.
					if (random.nextInt(80) == 10) {
						System.out.println("Readjusting mouse. ");
						bot.accuratelyMoveCursor(alchX, alchY);
						bot.delay(random.nextInt(500) + 500);
					}

					// Cast High Alch.
					// switchTo(BOOK);
					bot.mouseClick();
					bot.delay(random.nextInt(500) + 500);
					bot.mouseClick();
					bot.delay(random.nextInt(500) + 500);

					// Check if finished casting.
					checkIfStillCasting();
                                        */ //##Uncomment above for old routine. 
					/** End routine here. **/
					//bot.delay(random.nextInt(500) + 500);
                                        
                                        if (numberToAlch <= 0) {
                                            break;
                                        }
                                        bot.mouseClick();
					bot.delay(random.nextInt(5) + 5);
					bot.mouseClick();
					bot.delay(random.nextInt(5) + 5);
                                        
					numberToAlch--;
                                        
                                        
					checkIfPausedOrStopped();
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		if (numberToAlch <= 0) {
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
