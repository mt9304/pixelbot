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
    int numberOfTripsToDo = 200;
    int numberOftripsSinceDiseaseCured = 0;
    
    //The top left most pixel of the game frame. Used as a reference to find the other pixels
    int osX = 621;
    int osY = 263;
    
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
                    tripNumber++;
                    
                    if (tripNumber >= numberOfTripsToDo) {
                        return;
                    }
                    
                    /** Start Routine Here **/
                    System.out.println("Starting Patrol. ");
                    if (IsDead()) {
                        throw new Exception("Character died in combat, please manually put character back in position and restart bot. ");
                    }
                    HealIfNotFullLife();
                    if (numberOftripsSinceDiseaseCured >= 10) {
                        //CureDisease();
                    }
                    
                    ClickPatrol();
                    if (IsInDialogueScreen()) {
                        ClickPastDialogueScreen();
                        continue;
                    } else if (IsInMainCombatScreen()) {
                        HandleCombat();
                    } else {
                        throw new Exception("Unknown screen detected, expected either dialogue screen or main combat screen. ");
                    }
                    
                    /** End Routine Here **/
                    
                    Thread.sleep(random.nextInt(1000) + 1000);
                    checkIfPausedOrStopped();
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(MainBotRoutine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                System.out.println("Unable to complete routine: " + e);
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
        //For RGB values of black screen when text is present to indicate spcial actions
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
        //For RGB values of orange screen when in the inventory screen
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private void ClickInventory() throws Exception {
        //For RGB values of black screen, where inventory bar would not be accessible
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            throw new Exception("Unable to click inventory, black screen detected. ");
        }
        
        //For RGB values of bottom black bar that takes user to inventory
        red = bot.getPixelColor(0, 0).getRed();
        green = bot.getPixelColor(0, 0).getGreen();
        blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red != 0 && green != 0 && blue != 0) {
            throw new Exception("Unable to click inventory, invetory bar not detected. ");
        }
        
        //Click inventory;
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if in inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 5; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(1000);
            }
        }
        
        if (!isInInventoryScreen) {
            throw new Exception("Unable verify if in inventory screen. ");
        }
    }
    
    private void ClickPatrol() throws Exception {
        if (!IsInCaves()) {
            throw new Exception("Could not click patrol, unable to detect caves screen. ");
        }
        
        //Click patrol map icon;
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in caves screen
        Boolean isStillInCavesScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInCavesScreen = IsInCaves();
            if (isStillInCavesScreen) {
                bot.delay(1000);
            }
        }
        
        if (isStillInCavesScreen) {
            throw new Exception("Unable verify if moved past caves screen. ");
        }
    }
    
    private void ClickPastDialogueScreen() throws Exception {
        if (!IsInDialogueScreen()) {
            throw new Exception("Unable to click past dialogue screen, black background not detected. ");
        }
        
        //Click top left of screen
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in dialogue screen
        Boolean isStillInDialogueScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInDialogueScreen = IsInDialogueScreen();
            if (isStillInDialogueScreen) {
                bot.delay(1000);
            }
        }
        
        if (isStillInDialogueScreen) {
            throw new Exception("Unable verify if moved past dialogue screen. ");
        }
    }
    
    private void ClickPastDamageScreen() throws Exception {
        if (!IsInDamageScreen()) {
            throw new Exception("Unable to click past damage screen, black background not detected. ");
        }
        
        //Click top left of screen
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in dialogue screen
        Boolean isStillInDamageScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInDamageScreen = IsInDamageScreen();
            if (isStillInDamageScreen) {
                bot.delay(1000);
            }
        }
        
        if (isStillInDamageScreen) {
            throw new Exception("Unable verify if moved past damage screen. ");
        }
    }
    
    private int[] GetFirstCombatOptionCoordinates() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to find the first combat option on the combat screen, main combat screen not detected. ");
        }
        
        Boolean textColourFound = false;
	int x = 0;
	int y = 0;
        
        //Need to move mouse away from text area incase it highlights and changes text colour
        bot.mouseMove(10, 10);

	for (int i = 0; i < 500; i++) {
            int red = bot.getPixelColor(x, y).getRed();
            int green = bot.getPixelColor(x, y).getGreen();
            int blue = bot.getPixelColor(x, y).getBlue();
            
            if (red == 0 && green == 0 && blue == 0) {
                    textColourFound = true;
                    break;
            }
            x++;
            y++;
            y++;
	}
        
        if (textColourFound) {
            int[] coords = {x,y};
            return coords;
        } else {
            throw new Exception("Unable to find the first combat option on the combat screen. ");
        }
    }
    
    private Boolean EnemyIsMagmaDragon() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to detect enemy, main combat screen not detected. ");
        }
        
        int enemyRed = bot.getPixelColor(0, 0).getRed();
        int enemyGreen = bot.getPixelColor(0, 0).getGreen();
        int enemyBlue = bot.getPixelColor(0, 0).getBlue();
        
        if (enemyRed == 0 && enemyGreen == 0 && enemyBlue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Finds the first combat option, mouses over it to turn the text red, then finds the second combat option (run away) and click. 
    private void RunAway() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to run away, main combat screen not detected. ");
        }
        bot.mouseMove(100, 100);
        int[] firstCombatOptionCoordinates = GetFirstCombatOptionCoordinates();
        bot.mouseMove(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]);
        bot.delay(500);
        
        int firstOptionRed = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getRed();
        int firstOptionGreen = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getGreen();
        int firstOptionBlue = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getBlue();
        
        //Checking first combat option text to see if it turned red after mouse moved to it
        if (firstOptionRed == 0 || firstOptionGreen == 0 && firstOptionBlue == 0) {
            Boolean textColourFound = false;
            int x = firstCombatOptionCoordinates[0];
            int y = firstCombatOptionCoordinates[1];
            
            for (int i = 0; i < 500; i++) {
                int red = bot.getPixelColor(x, y).getRed();
                int green = bot.getPixelColor(x, y).getGreen();
                int blue = bot.getPixelColor(x, y).getBlue();

                if (red == 0 && green == 0 && blue == 0) {
                        textColourFound = true;
                        break;
                }
                x++;
                y++;
                y++;
            }
            
            if (textColourFound) {
                bot.mouseMove(x, y);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(250);
            } else {
                throw new Exception("Unable to find the second combat option on the combat screen. ");
            }
        } else {
            throw new Exception("Unable to run away, first combat text not highlighted. ");
        }
        
        //Check if still in main combat screen
        Boolean isInMainCombatScreen = true;
        for (int i = 0; i < 5; i++) {
            isInMainCombatScreen = IsInMainCombatScreen();
            if (isInMainCombatScreen) {
                bot.delay(1000);
            }
        }
        if (isInMainCombatScreen) {
            throw new Exception("Unable verify if ran away, main combat screen still detected. ");
        }
    }
    
    private void HandleCombat() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to handle combat, main combat screen not detected. ");
        }

        //Running away assumes enemy does not pursue. 
        if (EnemyIsMagmaDragon()) {
            RunAway();
            ClickPastDialogueScreen();
            return;
        }
        
        int timesToTryAttacking = 20;
        for (int i = 0; i < timesToTryAttacking; i++) {
            if (IsInCaves() || IsDead()) {
                return;
            }
            
            int[] firstCombatOptionCoordinates = GetFirstCombatOptionCoordinates();
            bot.mouseMove(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);

            //Check if still in main combat screen
            Boolean isInMainCombatScreen = true;
            for (int j = 0; j < 5; j++) {
                isInMainCombatScreen = IsInMainCombatScreen();
                if (isInMainCombatScreen) {
                    bot.delay(1000);
                }
            }
            if (isInMainCombatScreen) {
                throw new Exception("Unable verify attack clicked, main combat screen still detected. ");
            }

            //Sometimes enemies will do something that involve this screen before the damage screen
            if (IsInDialogueScreen()) {
                ClickPastDialogueScreen();
            }

            if (IsInDamageScreen()) {
                ClickPastDamageScreen();
            }

            //This screen might appear if they run away, use an item, drop loot, or revive
            if (IsInDialogueScreen()) {
                ClickPastDialogueScreen();
            }
        }
    }
    
    private Boolean InventoryBarIsAvailable() {
        int red = bot.getPixelColor(0, 0).getRed();
        int green = bot.getPixelColor(0, 0).getGreen();
        int blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 & green == 0 & blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private void HealIfNotFullLife() throws Exception {
        if (!InventoryBarIsAvailable()) {
            throw new Exception("Unable to heal, inventory bar not detected. ");
        }
        
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 5; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(1000);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to heal, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDamaged = false;
        for (int i = 0; i < 10; i++) {
            //Scan guts area for [ ] characters
            for (int j = 716; j < 850; j++) {
                int red = bot.getPixelColor(j, 374).getRed();
                int green = bot.getPixelColor(j, 374).getGreen();
                int blue = bot.getPixelColor(j, 374).getBlue();

                if (red == 255 && green == 255 && blue == 255) {
                    isDamaged = true;
                    break;
                }
            }
            
            if (isDamaged = true) {
                bot.mouseMove(691, 566);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(1000);
                bot.mouseMove(1044, 840);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(250);
                isDamaged = false;
            } else {
                break;
            }
        }

        bot.mouseMove(1396, 842);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved away from inventory screen
        Boolean isStillInInventoryScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(1000);
            }
        }
        if (isStillInInventoryScreen) {
            throw new Exception("Unable to exit inventory screen, inventory screen still detected. ");
        }
    }
    
    private void CureDiseaseAndStatuses() throws Exception {
        if (!InventoryBarIsAvailable()) {
            throw new Exception("Unable to cure disease, inventory bar not detected. ");
        }
        
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 5; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(1000);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to cure disease, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDebuffed = false;
        //Scan guts area for [ ] characters
        for (int j = 1046; j < 1200; j++) {
            //Scanning skill for [ ] indicators
            int red = bot.getPixelColor(j, 450).getRed();
            int green = bot.getPixelColor(j, 450).getGreen();
            int blue = bot.getPixelColor(j, 450).getBlue();

            if (red == 255 && green == 255 && blue == 255) {
                isDebuffed = true;
                break;
            }
            
            //Scanning character portrait in top right for status indicators
            int statusRed = bot.getPixelColor(j+250, 312).getRed();
            int statusGreen = bot.getPixelColor(j+250, 312).getGreen();
            int statusBlue = bot.getPixelColor(j+250, 312).getBlue();
            
            if (statusRed == 255 && statusGreen == 255 && statusBlue == 255) {
                isDebuffed = true;
                break;
            }
        }
        
        //Uses first item in inventory. 
        if (isDebuffed) {
            bot.mouseMove(691, 566);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(1000);
            bot.mouseMove(1044, 840);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);
        }

        //Exits inventory screen
        bot.mouseMove(1396, 842);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved away from inventory screen
        Boolean isStillInInventoryScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(1000);
            }
        }
        if (isStillInInventoryScreen) {
            throw new Exception("Unable to exit inventory screen, inventory screen still detected. ");
        }
    }
    
    private void CureStatuses() throws Exception {
        if (!InventoryBarIsAvailable()) {
            throw new Exception("Unable to cure statuses, inventory bar not detected. ");
        }
        
        bot.mouseMove(0, 0);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 5; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(1000);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to cure statuses, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDebuffed = false;
        //Scan guts area for [ ] characters
        for (int j = 1300; j < 1432; j++) {
            //Scanning character portrait in top right for status indicators
            int statusRed = bot.getPixelColor(j, 312).getRed();
            int statusGreen = bot.getPixelColor(j, 312).getGreen();
            int statusBlue = bot.getPixelColor(j, 312).getBlue();
            
            if (statusRed == 255 && statusGreen == 255 && statusBlue == 255) {
                isDebuffed = true;
                break;
            }
        }
        
        //Uses first item in inventory. 
        if (isDebuffed) {
            bot.mouseMove(691, 566);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(1000);
            bot.mouseMove(1044, 840);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);
        }

        //Exits inventory screen
        bot.mouseMove(1396, 842);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved away from inventory screen
        Boolean isStillInInventoryScreen = true;
        for (int i = 0; i < 5; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(1000);
            }
        }
        
        if (isStillInInventoryScreen) {
            throw new Exception("Unable to exit inventory screen, inventory screen still detected. ");
        }
    }
}
