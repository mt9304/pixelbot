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
    int numberOfTripsToDo = 40;
    int numberOftripsSinceDiseaseCured = 0;
    
    //The top left most pixel of the game frame. Used as a reference to find the other pixels
    //Game should be set to 100% zoom, not 150% or 200%
    //int osX = 621; //For 200%
    //int osY = 263; //For 200%
    
    int osX = 837; //For 100% Chrome
    int osY = 286; //For 100% Chrome
    //int osX = 841; //For 100% Firefox
    //int osY = 279; //For 100% Firefox
    
    //For checking current background color. 
    int bgColorCheckX = osX + 2;
    int bgColorCheckY = osY + 2;
    
    int dunjeonX = osX + 326;
    int dunjeonY = osY + 214;
    
    int patrolX = osX + 211;
    int patrolY = osY + 200;

    int invBarX = osX + 399;
    int invBarY = osY + 308;

    int firstInvItemX = osX + 27;
    int firstInvItemY = osY + 152;
    int firstInvItemBlankX = osX + 144;
    int firstInvItemBlankY = osY + 152;

    int invUseButtonX = osX + 213;
    int invUseButtonY = osY + 288;

    int invExitButtonX = osX + 391;
    int invExitButtonY = osY + 288;

    int invStatusBracketXStart = osX + 344;
    int invStatusBracketXEnd = osX + 397;
    int invStatusBracketY = osY + 21;

    int invGutsBracketXStart = osX + 59;
    int invGutsBracketXEnd = osX + 124;
    int invGutsBracketY = osY + 56;

    int invDiseaseBracketXStart = osX + 214;
    int invDiseaseBracketXEnd = osX + 299;
    int invDiseaseBracketY = osY + 93;

    int combatTopOfOptionBoxX = osX + 205;
    int combatTopOfOptionBoxY = osY + 21;    
    
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

        System.out.println("Starting Patrol in 3 seconds.  ");
        bot.delay(3000);

        synchronized (this)
        {
            try
            {
                disableStartButton();
                while (running)
                {
                    checkIfPausedOrStopped();
                    //bot.delay(random.nextInt(1000) + 1000);
                    tripNumber++;
                    
                    if (tripNumber >= numberOfTripsToDo) {
                        return;
                    }
                    
                    /** Start Routine Here **/
                    System.out.println("Questions: " + tripNumber + " / " + numberOfTripsToDo);
                    if (IsDead()) {
                        throw new Exception("Character died in combat, please manually put character back in position and restart bot. ");
                    }
                    //CureDiseaseAndStatuses();
                    HealIfNotFullLife();
                    if (numberOftripsSinceDiseaseCured >= 10) {
                        CureStatuses();
                    }
                    
                    //ClickPatrol();
                    ClickDunjeon();
                    if (IsInDialogueScreen()) {
                        ClickPastDialogueScreen(); //Sometimes this happens if new area gets discovered
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
        //For RGB values of healing tower
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 128 && green == 255 && blue == 128) {
            return true;
        }
        
        /*
        //For RGB areas of fields. 
        red = bot.getPixelColor(0, 0).getRed();
        green = bot.getPixelColor(0, 0).getGreen();
        blue = bot.getPixelColor(0, 0).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        }
        */
        return false;
    }
    
    //Checks if the background colour is yellow. 
    private Boolean IsInMainCombatScreen() {
        //For RGB values of initial combat screen (yellow)
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 255 && green == 255 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is black. 
    private Boolean IsInDialogueScreen() {
        //For RGB values of black screen when text is present to indicate spcial actions
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 0 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is red. 
    private Boolean IsInDamageScreen() {
        //For RGB values of red screen when damaging combat actions occur
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 192 && green == 0 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is dark red. 
    private Boolean IsInCaves() {
        //For RGB values of orange screen when idle in caves
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 238 && green == 68 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is dark red. 
    private Boolean IsInCastle() {
        //For RGB values of pink screen when idle in castle
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 255 && green == 136 && blue == 255) {
            //System.out.println(bgColorCheckX + ", " + bgColorCheckY + ": " + red + "/" + blue + "/" + green);
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the background colour is orange. 
    private Boolean IsInInventoryScreen() {
        //For RGB values of orange screen when in the inventory screen
        int red = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getRed();
        int green = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getGreen();
        int blue = bot.getPixelColor(bgColorCheckX, bgColorCheckY).getBlue();
        
        if (red == 192 && green == 64 && blue == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Checks if the first item in the inv is already clicked 
    private Boolean IsFirstInvItemClicked() {
        //For RGB values of orange screen when in the inventory screen
        int red = bot.getPixelColor(firstInvItemBlankX, firstInvItemBlankY).getRed();
        int green = bot.getPixelColor(firstInvItemBlankX, firstInvItemBlankY).getGreen();
        int blue = bot.getPixelColor(firstInvItemBlankX, firstInvItemBlankY).getBlue();
        
        if (red == 85 && green == 85 && blue == 85) {
            return true;
        } else {
            return false;
        }
    }
    
    private void ClickInventory() throws Exception {
        //For RGB values of bottom black bar that takes user to inventory
        int red = bot.getPixelColor(invBarX, invBarY).getRed();
        int green = bot.getPixelColor(invBarX, invBarY).getGreen();
        int blue = bot.getPixelColor(invBarX, invBarY).getBlue();
        
        if (red != 66 && green != 0 && blue != 66) {
            throw new Exception("Unable to click inventory, invetory bar not detected. ");
        }
        
        //Click inventory;
        bot.mouseMove(invBarX, invBarY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if in inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 10; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(500);
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
        bot.mouseMove(patrolX, patrolY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in caves screen
        Boolean isStillInCavesScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInCavesScreen = IsInCaves();
            if (isStillInCavesScreen) {
                bot.delay(500);
            }
        }
        
        if (isStillInCavesScreen) {
            throw new Exception("Unable verify if moved past caves screen. ");
        }
    }
    
    private void ClickDunjeon() throws Exception {
        if (!IsInCastle()) {
            throw new Exception("Could not click Dunjeon, unable to detect castle screen. ");
        }
        
        //Click patrol map icon;
        bot.mouseMove(dunjeonX, dunjeonY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in caves screen
        Boolean isStillInCastleScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInCastleScreen = IsInCastle();
            if (isStillInCastleScreen) {
                bot.delay(500);
            }
        }
        
        if (isStillInCastleScreen) {
            throw new Exception("Unable verify if moved past castle screen. ");
        }
    }
    
    private void ClickPastDialogueScreen() throws Exception {
        if (!IsInDialogueScreen()) {
            throw new Exception("Unable to click past dialogue screen, black background not detected. ");
        }
        
        //Click top left of screen
        bot.mouseMove(bgColorCheckX, bgColorCheckY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in dialogue screen
        Boolean isStillInDialogueScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInDialogueScreen = IsInDialogueScreen();
            if (isStillInDialogueScreen) {
                bot.delay(500);
            }
        }
        
        if (isStillInDialogueScreen) {
            //Character might level up and show this screen again
            bot.mouseMove(bgColorCheckX, bgColorCheckY);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);
            
            //Check if still in dialogue screen
            isStillInDialogueScreen = true;
            for (int i = 0; i < 10; i++) {
                isStillInDialogueScreen = IsInDialogueScreen();
                if (isStillInDialogueScreen) {
                    bot.delay(500);
                }
            }
            
            if (isStillInDialogueScreen) {
                throw new Exception("Unable verify if moved past dialogue screen. ");
            }
        }
        
        //Sometimes it fails to run away and another damage screen happens. 
        if (IsInDamageScreen()) {
            ClickPastDamageScreen();
        }
    }
    
    private void ClickPastDamageScreen() throws Exception {
        if (!IsInDamageScreen()) {
            throw new Exception("Unable to click past damage screen, black background not detected. ");
        }
        
        //Click top left of screen
        bot.mouseMove(bgColorCheckX, bgColorCheckY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if still in dialogue screen
        Boolean isStillInDamageScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInDamageScreen = IsInDamageScreen();
            if (isStillInDamageScreen) {
                bot.delay(500);
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
	int x = combatTopOfOptionBoxX;
	int y = combatTopOfOptionBoxY;
        
        //Need to move mouse away from text area incase it highlights and changes text colour
        bot.mouseMove(bgColorCheckX, bgColorCheckY);

	for (int i = 0; i < 50; i++) {
            int red = bot.getPixelColor(x, y).getRed();
            int green = bot.getPixelColor(x, y).getGreen();
            int blue = bot.getPixelColor(x, y).getBlue();
            
            if (red == 0 && green == 0 && blue == 0) {
                    textColourFound = true;
                    break;
            }
            
            //For if the top option is still highlighted
            if (red == 255 && green == 0 && blue == 0) {
                textColourFound = true;
            break;
            }
            x++;
            y++;
            y++;
	}
        
        if (textColourFound) {
            int[] coords = {x,y};
            //System.out.println("coords: " + x + ", " + y);
            return coords;
        } else {
            throw new Exception("Unable to find the first combat option on the combat screen. ");
        }
    }
    
    private Boolean EnemyIsMagmaDragon() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to detect enemy, main combat screen not detected. ");
        }
        
        // This section below are coordinates for checking unique identifiers from certain enemies
        int cavernDragonX1 = osX + 39; // 1 0 16
        int cavernDragonY1 = osY + 30;
        int cavernDragonX2 = osX + 80; // 24 21 40
        int cavernDragonY2 = osY + 65;
        int cavernDragonX3 = osX + 62; // 173 16 43
        int cavernDragonY3 = osY + 85;
        int cavernDragonX4 = osX + 142; // 255 217 104
        int cavernDragonY4 = osY + 148;
        
        Boolean coord1Matches = false;
        Boolean coord2Matches = false;
        Boolean coord3Matches = false;
        Boolean coord4Matches = false;
        
        int enemyRed = bot.getPixelColor(cavernDragonX1, cavernDragonY1).getRed();
        int enemyGreen = bot.getPixelColor(cavernDragonX1, cavernDragonY1).getGreen();
        int enemyBlue = bot.getPixelColor(cavernDragonX1, cavernDragonY1).getBlue();
        
        if (enemyRed == 1 && enemyGreen == 0 && enemyBlue == 16) {
            coord1Matches = true;
        }
        
        enemyRed = bot.getPixelColor(cavernDragonX2, cavernDragonY2).getRed();
        enemyGreen = bot.getPixelColor(cavernDragonX2, cavernDragonY2).getGreen();
        enemyBlue = bot.getPixelColor(cavernDragonX2, cavernDragonY2).getBlue();
        
        if (enemyRed == 24 && enemyGreen == 21 && enemyBlue == 40) {
            coord2Matches = true;
        }
        
        enemyRed = bot.getPixelColor(cavernDragonX3, cavernDragonY3).getRed();
        enemyGreen = bot.getPixelColor(cavernDragonX3, cavernDragonY3).getGreen();
        enemyBlue = bot.getPixelColor(cavernDragonX3, cavernDragonY3).getBlue();
        
        if (enemyRed == 173 && enemyGreen == 16 && enemyBlue == 43) {
            coord3Matches = true;
        }
        
        enemyRed = bot.getPixelColor(cavernDragonX4, cavernDragonY4).getRed();
        enemyGreen = bot.getPixelColor(cavernDragonX4, cavernDragonY4).getGreen();
        enemyBlue = bot.getPixelColor(cavernDragonX4, cavernDragonY4).getBlue();
        
        if (enemyRed == 255 && enemyGreen == 217 && enemyBlue == 104) {
            coord4Matches = true;
        }
        
        if (coord1Matches && coord2Matches && coord3Matches && coord4Matches) {
            return true;
        } else {
            return false;
        }
    }
    
    private Boolean EnemyIsGang() throws Exception {
        if (!IsInMainCombatScreen()) {
            throw new Exception("Unable to detect enemy, main combat screen not detected. ");
        }
        
        // This section below are coordinates for checking unique identifiers from certain enemies
        int gangX1 = osX + 40; //254 252 227
        int gangY1 = osY + 48;
        int gangX2 = osX + 76; //169 182 112
        int gangY2 = osY + 48;
        int gangX3 = osX + 115; //114 54 20
        int gangY3 = osY + 52;
        int gangX4 = osX + 165; //248 252 132
        int gangY4 = osY + 79;
        
        Boolean coord1Matches = false;
        Boolean coord2Matches = false;
        Boolean coord3Matches = false;
        Boolean coord4Matches = false;
        
        int enemyRed = bot.getPixelColor(gangX1, gangY1).getRed();
        int enemyGreen = bot.getPixelColor(gangX1, gangY1).getGreen();
        int enemyBlue = bot.getPixelColor(gangX1, gangY1).getBlue();
        
        System.out.println(gangX1 + ", " + gangY1 + ": " + enemyRed + "/" + enemyGreen + "/" + enemyBlue);
        if (enemyRed == 254 && enemyGreen == 252 && enemyBlue == 227) {
            coord1Matches = true;
        }
        
        enemyRed = bot.getPixelColor(gangX2, gangY2).getRed();
        enemyGreen = bot.getPixelColor(gangX2, gangY2).getGreen();
        enemyBlue = bot.getPixelColor(gangX2, gangY2).getBlue();
        
        System.out.println(gangX2 + ", " + gangY2 + ": " + enemyRed + "/" + enemyGreen + "/" + enemyBlue);
        if (enemyRed == 169 && enemyGreen == 182 && enemyBlue == 112) {
            coord2Matches = true;
        }
        
        enemyRed = bot.getPixelColor(gangX3, gangY3).getRed();
        enemyGreen = bot.getPixelColor(gangX3, gangY3).getGreen();
        enemyBlue = bot.getPixelColor(gangX3, gangY3).getBlue();
        
        System.out.println(gangX3 + ", " + gangY3 + ": " + enemyRed + "/" + enemyGreen + "/" + enemyBlue);
        if (enemyRed == 96 && enemyGreen == 95 && enemyBlue == 51) {
            coord3Matches = true;
        }
        
        enemyRed = bot.getPixelColor(gangX4, gangY4).getRed();
        enemyGreen = bot.getPixelColor(gangX4, gangY4).getGreen();
        enemyBlue = bot.getPixelColor(gangX4, gangY4).getBlue();
        
        System.out.println(gangX4 + ", " + gangY4 + ": " + enemyRed + "/" + enemyGreen + "/" + enemyBlue);
        if (enemyRed == 240 && enemyGreen == 240 && enemyBlue == 142) {
            coord4Matches = true;
        }
        
        if (coord1Matches && coord2Matches && coord3Matches && coord4Matches) {
            System.out.println();
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
        bot.mouseMove(bgColorCheckX, bgColorCheckY);
        int[] firstCombatOptionCoordinates = GetFirstCombatOptionCoordinates();
        bot.mouseMove(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]);
        bot.delay(500);
        
        int firstOptionRed = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getRed();
        int firstOptionGreen = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getGreen();
        int firstOptionBlue = bot.getPixelColor(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]).getBlue();
        
        //Checking first combat option text to see if it turned red after mouse moved to it
        if (firstOptionRed == 255 || firstOptionGreen == 0 && firstOptionBlue == 0) {
            Boolean textColourFound = false;
            int x = firstCombatOptionCoordinates[0];
            int y = firstCombatOptionCoordinates[1];
            
            for (int i = 0; i < 50; i++) {
                int red = bot.getPixelColor(x, y).getRed();
                int green = bot.getPixelColor(x, y).getGreen();
                int blue = bot.getPixelColor(x, y).getBlue();

                if (red == 0 && green == 0 && blue == 0) {
                        textColourFound = true;
                        break;
                }
                x++;
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
        for (int i = 0; i < 10; i++) {
            isInMainCombatScreen = IsInMainCombatScreen();
            if (isInMainCombatScreen) {
                bot.delay(500);
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
        if (EnemyIsMagmaDragon() || EnemyIsGang()) {
            RunAway();
            ClickPastDialogueScreen();
            return;
        }
        
        int timesToTryAttacking = 5;
        for (int i = 0; i < timesToTryAttacking; i++) {
            if (IsInCaves() || IsDead() || IsInCastle()) {
                return;
            }
            
            int[] firstCombatOptionCoordinates = GetFirstCombatOptionCoordinates();
            bot.mouseMove(firstCombatOptionCoordinates[0], firstCombatOptionCoordinates[1]);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);

            //Check if still in main combat screen
            Boolean isInMainCombatScreen = true;
            for (int j = 0; j < 10; j++) {
                isInMainCombatScreen = IsInMainCombatScreen();
                if (isInMainCombatScreen) {
                    bot.delay(500);
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
            
            //This screen might appear if the character levels up
            if (IsInDialogueScreen()) {
                ClickPastDialogueScreen();
            }
        }
    }
    
    private Boolean InventoryBarIsAvailable() {
        int red = bot.getPixelColor(invBarX, invBarY).getRed();
        int green = bot.getPixelColor(invBarX, invBarY).getGreen();
        int blue = bot.getPixelColor(invBarX, invBarY).getBlue();
        
        if (red == 66 & green == 0 & blue == 66) {
            return true;
        } else {
            return false;
        }
    }
    
    private void HealIfNotFullLife() throws Exception {
        if (!InventoryBarIsAvailable()) {
            throw new Exception("Unable to heal, inventory bar not detected. ");
        }
        
        //Clicks inventory bar to get into inventory screen. 
        bot.mouseMove(invBarX, invBarY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 10; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(500);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to heal, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDamaged = false;
        for (int i = 0; i < 10; i++) {
            //Scan guts area for [ ] characters
            for (int j = invGutsBracketXStart; j < invGutsBracketXEnd; j++) {
                int red = bot.getPixelColor(j, invGutsBracketY).getRed();
                int green = bot.getPixelColor(j, invGutsBracketY).getGreen();
                int blue = bot.getPixelColor(j, invGutsBracketY).getBlue();

                if (red == 255 && green == 255 && blue == 255) {
                    isDamaged = true;
                    //System.out.println(j + ", " + invGutsBracketY);
                    //System.out.println(red + ", " + green + " " + blue);
                    break;
                }
            }
            
            if (isDamaged) {
                if (!IsFirstInvItemClicked()) {
                    bot.mouseMove(firstInvItemX, firstInvItemY);
                    bot.delay(250);
                    bot.mouseClick();
                    bot.delay(125);
                }
                bot.delay(125);
                bot.mouseMove(invUseButtonX, invUseButtonY);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(250);
                isDamaged = false;
            } else {
                break;
            }
        }

        bot.mouseMove(invExitButtonX, invExitButtonY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved away from inventory screen
        Boolean isStillInInventoryScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(500);
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
        
        bot.mouseMove(invBarX, invBarY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 10; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(500);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to cure disease, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDebuffed = false;
        for (int j = invDiseaseBracketXStart; j < invDiseaseBracketXEnd; j++) {
            //Scanning skill for [ ] indicators
            int red = bot.getPixelColor(j, invDiseaseBracketY).getRed();
            int green = bot.getPixelColor(j, invDiseaseBracketY).getGreen();
            int blue = bot.getPixelColor(j, invDiseaseBracketY).getBlue();

            if (red == 255 && green == 255 && blue == 255) {
                isDebuffed = true;
                break;
            }
            
            //Scanning character portrait in top right for status indicators
            int statusRed = bot.getPixelColor(j+250, invStatusBracketY).getRed();
            int statusGreen = bot.getPixelColor(j+250, invStatusBracketY).getGreen();
            int statusBlue = bot.getPixelColor(j+250, invStatusBracketY).getBlue();
            
            if (statusRed == 255 && statusGreen == 255 && statusBlue == 255) {
                isDebuffed = true;
                break;
            }
        }
        
        //Uses first item in inventory. 
        if (isDebuffed) {
            if (!IsFirstInvItemClicked()) {
                bot.mouseMove(firstInvItemX, firstInvItemY);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(125);
            }
            bot.delay(125);
            bot.mouseMove(invUseButtonX, invUseButtonY);
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
        for (int i = 0; i < 10; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(500);
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
        
        bot.mouseMove(invBarX, invBarY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved into inventory screen
        Boolean isInInventoryScreen = false;
        for (int i = 0; i < 10; i++) {
            isInInventoryScreen = IsInInventoryScreen();
            if (!isInInventoryScreen) {
                bot.delay(500);
            }
        }
        if (!isInInventoryScreen) {
            throw new Exception("Unable to cure statuses, inventory screen not detected. ");
        }
        
        //Uses first item in inventory to heal. 
        Boolean isDebuffed = false;
        //Scan guts area for [ ] characters
        for (int j = invStatusBracketXStart; j < invStatusBracketXEnd; j++) {
            //Scanning character portrait in top right for status indicators
            int statusRed = bot.getPixelColor(j, invStatusBracketY).getRed();
            int statusGreen = bot.getPixelColor(j, invStatusBracketY).getGreen();
            int statusBlue = bot.getPixelColor(j, invStatusBracketY).getBlue();
            
            if (statusRed == 255 && statusGreen == 255 && statusBlue == 255) {
                isDebuffed = true;
                break;
            }
        }
        
        //Uses first item in inventory. 
        if (isDebuffed) {
            if (!IsFirstInvItemClicked()) {
                bot.mouseMove(firstInvItemX, firstInvItemY);
                bot.delay(250);
                bot.mouseClick();
                bot.delay(125);
            }
            bot.delay(125);
            bot.mouseMove(invUseButtonX, invUseButtonY);
            bot.delay(250);
            bot.mouseClick();
            bot.delay(250);
        }

        //Exits inventory screen
        bot.mouseMove(invExitButtonX, invExitButtonY);
        bot.delay(250);
        bot.mouseClick();
        bot.delay(250);
        
        //Check if moved away from inventory screen
        Boolean isStillInInventoryScreen = true;
        for (int i = 0; i < 10; i++) {
            isStillInInventoryScreen = IsInInventoryScreen();
            if (isStillInInventoryScreen) {
                bot.delay(500);
            }
        }
        
        if (isStillInInventoryScreen) {
            throw new Exception("Unable to exit inventory screen, inventory screen still detected. ");
        }
    }
}
