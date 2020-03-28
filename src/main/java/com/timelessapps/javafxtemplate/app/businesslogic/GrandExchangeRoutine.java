package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class GrandExchangeRoutine extends Routine 
{
	RobotService bot = new RobotService();
	LoggingService log = new LoggingService();
	Random random = new Random();

	int counter = 0;

	public GrandExchangeRoutine() throws AWTException {

	}

	public void run() {
		log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");

		System.out.println("Starting bot routine in 3 seconds. ");
		bot.delay(3000);

		synchronized (this) {
			try {
				disableGrandExchangeButton();
				Random random = new Random();

				while (running) {
					checkIfPausedOrStopped();
					/** Start routine here. **/
					
					//check if loading bar still there
					//get screen offset
					//check if loaded
					//select world
					//login
					//exchange
					
					//buy item (validate pages)
					//wait for green 
					//gather item
					//sell item
					//wait for green
					//set prices
					
					//buy low
					//wait til green
					//gather item
					//sell high
					//wait to green
					//gather item
					
					//Repeat 2 and 3 for 2 more slots in future
					
					int randomNumber = random.nextInt(27);
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
			enableGrandExchangeButton();
		}

	}

	private void disableGrandExchangeButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("grandExchangeButton").setDisable(true);
	}

	private void enableGrandExchangeButton() {
		CustomSceneHelper sceneHelper = new CustomSceneHelper();
		sceneHelper.getNodeById("grandExchangeButton").setDisable(false);
	}
}
