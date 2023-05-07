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

public class StayLoggedRoutine extends Routine
{
    RobotService bot = new RobotService();
    LoggingService log = new LoggingService();
    Random random = new Random();

    int totalNumberOfTimesToClick = 5;

    volatile Boolean bookStillLoading = true;

    public StayLoggedRoutine() throws AWTException
    {

    }

    public void run()
    {
        try
        {
            log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Starting bot routine in 3 seconds. ");
        bot.delay(3000);

        synchronized (this)
        {
            try
            {
                disableStartButton();
                while (running)
                {
                    checkIfPausedOrStopped();
                    /**
                     * Start routine here.  *
                     */
                    //F5 = Inv, F6 = Equipment, F7 = SpellBook.
                    //Move to starting spot. 
                    bot.delay(random.nextInt(35000) + 35000);
                    //bot.delay(random.nextInt(3500) + 3500);

                    //For random taking a break and readjusting mouse. 
                    if (random.nextInt(50) == 25)
                    {
                        System.out.println("Moving mouse. ");
                        bot.moveCursorTo(random.nextInt(800), random.nextInt(800));
                        Thread.sleep(random.nextInt(4000) + 4000);
                        bot.delay(random.nextInt(500) + 500);
                    }
                    
                    bot.type(" ", 1000);
                    bot.delay(random.nextInt(5000) + 1000);
                    bot.keyPress(KeyEvent.VK_ALT);
                    bot.delay(random.nextInt(500));
                    bot.keyPress(KeyEvent.VK_TAB);
                    bot.delay(random.nextInt(500));
                    bot.keyRelease(KeyEvent.VK_TAB);
                    bot.delay(random.nextInt(500));
                    bot.keyRelease(KeyEvent.VK_ALT);
                    
                    bot.delay(random.nextInt(5000) + 1000);
                    bot.type(" ", 1000);
                    bot.delay(random.nextInt(5000) + 1000);
                    bot.keyPress(KeyEvent.VK_ALT);
                    bot.delay(random.nextInt(500));
                    bot.keyPress(KeyEvent.VK_TAB);
                    bot.delay(random.nextInt(500));
                    bot.keyRelease(KeyEvent.VK_TAB);
                    bot.delay(random.nextInt(500));
                    bot.keyRelease(KeyEvent.VK_ALT);
                    
                    /**
                     * End routine here. *
                     */
                    bot.delay(random.nextInt(35000) + 35000);
                    //bot.delay(random.nextInt(3500) + 3500);
                    totalNumberOfTimesToClick--;
                    checkIfPausedOrStopped();
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void checkIfPausedOrStopped() throws InterruptedException
    {
        if (totalNumberOfTimesToClick <= 0)
        {
            System.out.println("Preparing to shut down. ");
            running = false;
            //For sleeping computer. 
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

    private void switchTo(Slots slot)
    {
        switch (slot)
        {
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
}
