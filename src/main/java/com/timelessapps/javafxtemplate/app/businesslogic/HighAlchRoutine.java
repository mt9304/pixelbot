package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.BuffTimer;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class HighAlchRoutine extends Routine 
{
				RobotService bot = new RobotService();
				LoggingService log = new LoggingService();
				Random random = new Random();

				//For arrows, remember to include more in inv than stated number, otherwise stack will shrink and pixel detector may be off. 
				int numberToAlch = 1000;
				int alchX = 0;
				int alchY = 0;
				
				int equippedArrowSlotX = 0;
				int equippedArrowSlotY = 0;
				
				int blankInvSlotX = 0;
				int blankInvSlotY = 0;
				
				int firstInvSlotX = 0;
				int firstInvSlotY = 0;

				public HighAlchRoutine () throws AWTException
				{

				}
   
				public void run()
				{
								try 
								{
												log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
								} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}

								System.out.println("Starting bot routine in 3 seconds. ");
								bot.delay(3000);

								synchronized (this)
								{
												try 
												{
																disableStartButton();
																while(running)
																{
																				checkIfPausedOrStopped();
																				/** Start routine here.  **/ 
																				
																				
																				
																				
																				/** End routine here. **/
																				Thread.sleep(random.nextInt(15000) + 10000); //HP goes up every minute, so have to make sure this runs around every 45 seconds or less. 
																				checkIfPausedOrStopped();
																}
												}  catch (InterruptedException ex) {Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);}
								}
				}
   
				@Override
				public void checkIfPausedOrStopped() throws InterruptedException
				{
								waitIfPaused();
								if (!running)
								{
												enableStartButton();
								}
				}

				private void disableStartButton() 
				{
								CustomSceneHelper sceneHelper = new CustomSceneHelper();
								sceneHelper.getNodeById("startButton").setDisable(true);
				}

				private void enableStartButton() 
				{
								CustomSceneHelper sceneHelper = new CustomSceneHelper();
								sceneHelper.getNodeById("startButton").setDisable(false);
				}
				
				private void moveToAlchSpot()
				{
								
				}
				
				private void checkIfOnMagicScreen()
				{
								
				}
				
				private void checkIfArrowIsInPlace()
				{
								
				}
				
				private void unequiptArrowAndPutBackInPlace()
				{
								
				}
}
    