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

public class HumidifyRoutine extends Routine {
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();

	// For arrows, remember to include more in inv than stated number, otherwise
	// stack will shrink and pixel detector may be off.
	int numberToAlch = 980;
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
					// Move to starting spot.
					bookStillLoading = true;
					bot.delay(250);


					/** End routine here. **/
					bot.delay(random.nextInt(500) + 500);
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
		}

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

}
