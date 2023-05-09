package main.java.com.timelessapps.javafxtemplate.app.businesslogic;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.BuffTimer;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.ClickableArea;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.X;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.Y;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Slots.BOOK;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class SmeltIronRoutine extends Routine
{
    RobotService bot = new RobotService();
    LoggingService log = new LoggingService();
    Random random = new Random();
    int tripNumber = 0;
    
    //Declaring various coordinates for buttons, slots and objects. 
    ClickableArea bankSlot3_W1 = new ClickableArea(2195, 142, 2201, 147, 2200, 138, 2205, 151, 2193, 148, 2199, 152);
    ClickableArea bankSlot3_W2 = new ClickableArea(3170, 143, 3179, 149, 3171, 148, 3177, 151, 3178, 139, 3180, 143);
    ClickableArea invSlot1_W1 = new ClickableArea(2672, 716, 2680, 723, 2671, 727, 2678, 730, 2680, 715, 2682, 727);
    ClickableArea invSlot1_W2 = new ClickableArea(3668, 755, 3677, 761, 3667, 761, 3674, 765, 3675, 750, 3678, 762);

    public SmeltIronRoutine() throws AWTException
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
                    bot.delay(random.nextInt(1000) + 1000);
                    //bot.delay(random.nextInt(3500) + 3500);

                    //For random taking a break and readjusting mouse. 
                    if (random.nextInt(50) == 25)
                    {
                        System.out.println("Moving mouse. ");
                        bot.moveCursorTo(random.nextInt(800), random.nextInt(800));
                        Thread.sleep(random.nextInt(4000) + 4000);
                        bot.delay(random.nextInt(500) + 500);
                    }
                    
                    //  Start at first teller with bank tab open before running routine. 
                    //  Make sure no ring of forging equipped, iron ore in second bank  slot and irng of forging in 3rd bank slot. 
                    //  This routine uses 2 accounts in 2 different windows. Can modify to only use 1. 
                    if (tripNumber % 5 == 0) { //28 * 5 is 140, which is the startinc harges on a new ring of forging. { 
                    //  StoreInvToBank();
                        WithdrawRingOfForging(1);
                        WithdrawRingOfForging(2);
                    //  PressEscapeKey();  //Tools => All Settings => Make sure Press Esc To Exit Current Interface is checked in game. 
                    //  EquipRingOfForging();
                    //  ClickBankFromInFrontOfTeller();
                    }
                    
                    //  StoreInvToBank();
                    //  WithDrawIronOre();
                    //  ClickFurnaceFromBankTeller();
                    //  bot.type(" ", random.nextInt(400));
                    //  CheckForLevelUp();
                    //  ClickBankBoothFromFurnace();
                    
                    
                    /**
                     * End routine here. *
                     */
                    bot.delay(random.nextInt(17000) + 17000);
                    bot.delay(random.nextInt(17000) + 17000);
                    //bot.delay(random.nextInt(3500) + 3500);
                    tripNumber++;
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
        if (tripNumber >= 100)
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
    
    private void AltTab() {
        bot.keyPress(KeyEvent.VK_ALT);
        bot.delay(random.nextInt(500));
        bot.keyPress(KeyEvent.VK_TAB);
        bot.delay(random.nextInt(500));
        bot.keyRelease(KeyEvent.VK_TAB);
        bot.delay(random.nextInt(500));
        bot.keyRelease(KeyEvent.VK_ALT);
    }
    
    private void PressEscapeKey() {
        bot.keyPress(KeyEvent.VK_ESCAPE);
        bot.delay(random.nextInt(50) + 50);
        bot.keyRelease(KeyEvent.VK_ESCAPE);
    }
    
    private void WithdrawRingOfForging(int windowNumber) {
        if (windowNumber == 1) {
            bot.accuratelyMoveCursor(bankSlot3_W1.GetRandomXY());
            try {
                Thread.sleep(random.nextInt(500) + 500);
            } catch (Exception e) {
                
            }
            bot.mouseClick();
        } else if (windowNumber == 2) {
            bot.accuratelyMoveCursor(bankSlot3_W2.GetRandomXY());
            try {
                Thread.sleep(random.nextInt(500) + 500);
            } catch (Exception e) {
                
            }
            bot.mouseClick();
        }
    }
}
