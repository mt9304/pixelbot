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

public class DC_PatrolRoutine extends Routine
{
    RobotService bot = new RobotService();
    LoggingService log = new LoggingService();
    Random random = new Random();
    int numberOfQuestsToUse = 100;
    int tripNumber = 0;
    
    public DC_PatrolRoutine() throws AWTException
    {

    }

    public void run()
    {
        try
        {
            log.appendToEventLogsFile("Starting bot routine in 3 seconds. ");
        } catch (Exception ex)
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
                    bot.delay(random.nextInt(1000) + 1000);
                    
                    /** Start Routine Here **/
                    System.out.println("Starting Patrol. ");
                    if (!SpelunkMapDetected()) {
                        return;
                    }
                    RefreshHealthOutOfCombat();
                    Spelunk();
                    /** End Routine Here **/
                    
                    Thread.sleep(random.nextInt(1000) + 1000);
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
        if (tripNumber >= numberOfQuestsToUse)
        {
            System.out.println("Preparing to shut down. ");
            running = false;
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
    
    private void Spelunk() {
        //RunIfDragon
        //Fight
            //ClickAttack
            //ClickPastDoneScreenIfExist //If enemy uses items or ability
            //ClickPastDamageScreen
            //IfVictoryScreen Return
            //CheckHealthInCombat(uses)
                //If blind status Return
                //HealthDeficit = number after negative sign
                //EatEfficientlyToFullHealth
                    //StartWithRedMushroom //Incase of Status
                //If panic statuc use seltzer
                //ReturnToCombat
    }
    
    private void RefreshHealthOutOfCombat() {
        //If "blind" or "panic" contained in portrait
            //UseSeltzer
        //If guts contains "/"
            //ClickInventory
                //for i in 100
                    //If guts contains "/"
                        //EatFood
                    //else return
    }
    
    private Boolean SpelunkMapDetected(){
        return false;
    }
    
    //Check for background color to be either of fields or healing tower. Usually this is an indicator that the user lost the combat 
    private Boolean IsDead() {
        //For RGB values of fields
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        }
        
        //For RGB areas of healing towertower
        red = bot.getPixelColor(0, 0).getRed();
        green = bot.getPixelColor(0, 0).getGreen();
        blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        }
        return false;
    }
    
    //Checks if the background colour is yellow. 
    private Boolean IsInMainCombatScreen() {
        //For RGB values of initial combat screen (yellow)
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is black. 
    private Boolean IsInDialogueScreen() {
        //For RGB values of black screen when text is present to indicate spcial actions. 
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is red. 
    private Boolean IsInDamageScreen() {
        //For RGB values of red screen when damaging combat actions occur
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is dark red. 
    private Boolean IsInCaves() {
        //For RGB values of dark red screen when idle in caves
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is orange. 
    private Boolean IsInInventoryScreen() {
        //For RGB values of orange screen when in the inventory screen. 
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
}
