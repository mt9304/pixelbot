package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.BuffTimer;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.X;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.Y;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class SplashRoutine extends Routine {
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();

	// For arrows, remember to include more in inv than stated number, otherwise
	// stack will shrink and pixel detector may be off.
	int numberToAlch = 5000;
	int alchX = 1369;
	int alchY = 546;

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
	int counter = 0;

	public SplashRoutine() throws AWTException {

	}

	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");

		System.out.println("Starting bot routine in 3 seconds. ");
		bot.delay(3000);

		synchronized (this) {
			try {
				disableSplashButton();
				Random random = new Random();

				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					int randomNumber = random.nextInt(27);
					String randomLetter = getRandomLetter(randomNumber);
					bot.type(randomLetter, random.nextInt(15) + 35);
					counter++;
					System.out.println("Typed letter. ");
					Thread.sleep(random.nextInt(120000) + 120000);
					checkIfPausedOrStopped();
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void checkIfPausedOrStopped() throws InterruptedException {
		if (counter > 180) {
			System.out.println("Counter is: " + counter + ". Stopping routine. ");
			running = false;
		}

		waitIfPaused();
		if (!running) {
			enableSplashButton();
		}

	}

	private void disableSplashButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("splashButton").setDisable(true);
	}

	private void enableSplashButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("splashButton").setDisable(false);
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