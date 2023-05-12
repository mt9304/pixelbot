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
    /*
    ClickableArea bankSlot2_W1 = new ClickableArea(2143, 141, 2158, 151, 2138, 143, 2150, 157, 2151, 135, 2161, 145);
    ClickableArea bankSlot2_W2 = new ClickableArea(3119, 140, 3135, 146, 3116, 148, 3130, 158, 3129, 135, 3140, 149);
    ClickableArea bankSlot3_W1 = new ClickableArea(2195, 142, 2201, 147, 2200, 138, 2205, 151, 2193, 148, 2199, 152);
    ClickableArea bankSlot3_W2 = new ClickableArea(3170, 143, 3179, 149, 3171, 148, 3177, 151, 3178, 139, 3180, 143);
    ClickableArea invSlot1_W1 = new ClickableArea(2672, 716, 2680, 723, 2671, 727, 2678, 730, 2680, 715, 2682, 727);
    ClickableArea invSlot1_W2 = new ClickableArea(3668, 755, 3677, 761, 3667, 761, 3674, 765, 3675, 750, 3678, 762);
    
    ClickableArea depositInvToBankButton_W1 = new ClickableArea(2449, 822, 2465, 832, 2448, 817, 2470, 827, 2446, 833, 2468, 841);
    ClickableArea depositInvToBankButton_W2 = new ClickableArea(3433, 823, 3443, 831, 3424, 818, 3447, 828, 3425, 834, 3444, 839);
    
    ClickableArea bankBoothFromBank_W1 = new ClickableArea(2387, 562, 2392, 570, 2377, 557, 2392, 559, 2390, 565, 2396, 577);
    ClickableArea bankBoothFromBank_W2 = new ClickableArea(3358, 561, 3370, 570, 3357, 556, 3375, 557, 3355, 569, 3376, 575);
    
    ClickableArea bankBoothFromFurnace_W1 = new ClickableArea(1944, 730, 1956, 738, 1944, 721, 1958, 727, 1938, 745, 1950, 748);
    ClickableArea bankBoothFromFurnace_W2 = new ClickableArea(2909, 726, 2930, 734, 2921, 723, 2934, 726, 2915, 742, 2930, 747);
    
    ClickableArea furnaceFromBankBooth_W1 = new ClickableArea(2737, 399, 2745, 407, 2732, 412, 2739, 417, 2754, 382, 2759, 388);
    ClickableArea furnaceFromBankBooth_W2 = new ClickableArea(3714, 394, 3725, 404, 3705, 403, 3716, 416, 3732, 382, 3737, 389);
    
    ClickableArea furnaceFromFurnace_W1 = new ClickableArea(2418, 521, 2426, 531, 2418, 538, 2425, 545, 2423, 513, 2431, 520);
    ClickableArea furnaceFromFurnace_W2 = new ClickableArea(3397, 524, 3408, 531, 3395, 536, 3404, 543, 3400, 515, 3408, 523);
    */
    
    //Declaring various coordinates for buttons, slots and objects. 
    int W2XOffset = 7;
    int bankSlot2X_W1 = 2153;
    int bankSlot2Y_W1 = 145;
    int bankSlot2X_W2 = 3129 + W2XOffset;
    int bankSlot2Y_W2 = 144;
    int bankSlot3X_W1 = 2197;
    int bankSlot3Y_W1 = 147;
    int bankSlot3X_W2 = 3176 + W2XOffset;
    int bankSlot3Y_W2 = 145;
    int invSlot1X_W1 = 2680;
    int invSlot1Y_W1 = 722;
    int invSlot1X_W2 = 3676 + W2XOffset;
    int invSlot1Y_W2 = 760;
    int depositInvToBankButtonX_W1 = 2457;
    int depositInvToBankButtonY_W1 = 829;
    int depositInvToBankButtonX_W2 = 3437 + W2XOffset;
    int depositInvToBankButtonY_W2 = 829;

    int bankBoothFromBankX_W1 = 2382;
    int bankBoothFromBankY_W1 = 562;
    int bankBoothFromBankX_W2 = 3361 + W2XOffset;
    int bankBoothFromBankY_W2 = 562;
    int bankBoothFromFurnaceX_W1 = 1953;
    int bankBoothFromFurnaceY_W1 = 736;
    int bankBoothFromFurnaceX_W2 = 2935 + W2XOffset;
    int bankBoothFromFurnaceY_W2 = 733;
    int furnaceFromBankBoothX_W1 = 2741;
    int furnaceFromBankBoothY_W1 = 396;
    int furnaceFromBankBoothX_W2 = 3716 + W2XOffset;
    int furnaceFromBankBoothY_W2 = 397;
    int furnaceFromFurnaceX_W1 = 2423;
    int furnaceFromFurnaceY_W1 = 524;
    int furnaceFromFurnaceX_W2 = 3400 + W2XOffset;
    int furnaceFromFurnaceY_W2 = 526;

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
                        WithdrawRingOfForging(1);
                        Thread.sleep(random.nextInt(750) + 750);
                        PressEscapeKey();
                        
                        WithdrawRingOfForging(2);
                        Thread.sleep(random.nextInt(750) + 750);
                        PressEscapeKey();  //Tools => All Settings => Make sure Press Esc To Exit Current Interface is checked in game. 
                        
                        EquipRingOfForging(1);
                        EquipRingOfForging(2);
                        ClickBankFromInFrontOfTeller(1);
                        ClickBankFromInFrontOfTeller(2);
                        StoreInvToBank(1);
                        StoreInvToBank(2);
                    }
                    
                    Thread.sleep(random.nextInt(500) + 500);
                    WithDrawIronOre(1);
                    WithDrawIronOre(2);
                    Thread.sleep(random.nextInt(500) + 500);
                    ClickFurnaceFromBankTeller(1);
                    ClickFurnaceFromBankTeller(2);
                    Thread.sleep(random.nextInt(500) + 500);
                    AltTab();
                    Thread.sleep(random.nextInt(5000) + 10000);
                    bot.type(" ", random.nextInt(400));
                    Thread.sleep(random.nextInt(1000) + 1000);
                    AltTab();
                    Thread.sleep(random.nextInt(1000) + 1500);
                    bot.type(" ", random.nextInt(400));
                    Thread.sleep(random.nextInt(1000) + 1000);

                    CheckForLevelUp();
                    Thread.sleep(random.nextInt(5000) + 2000);

                    ClickBankBoothFromFurnace(1);
                    ClickBankBoothFromFurnace(2);
                    Thread.sleep(random.nextInt(5000) + 10000);
                    StoreInvToBank(1);
                    StoreInvToBank(2);
                    Thread.sleep(random.nextInt(1000) + 1000);
                    
                    /**
                     * End routine here. *
                     */
                    //bot.delay(random.nextInt(17000) + 17000);
                    //bot.delay(random.nextInt(17000) + 17000);
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
        int bankSlot3X = 0;
        int bankSlot3Y = 0;
        
        if (windowNumber == 1) {
            bankSlot3X = bankSlot3X_W1;
            bankSlot3Y = bankSlot3Y_W1;
        } else if (windowNumber == 2) {
            bankSlot3X = bankSlot3X_W2;
            bankSlot3Y = bankSlot3Y_W2;
        }
        
        bot.moveCursorTo(bankSlot3X, bankSlot3Y);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void StoreInvToBank(int windowNumber) {
        int storeInvButtonX = 0;
        int storeInvButtonY = 0;
        
        if (windowNumber == 1) {
            storeInvButtonX = depositInvToBankButtonX_W1;
            storeInvButtonY = depositInvToBankButtonY_W1;
        } else if (windowNumber == 2) {
            storeInvButtonX = depositInvToBankButtonX_W2;
            storeInvButtonY = depositInvToBankButtonY_W2;
        }
        
        bot.moveCursorTo(storeInvButtonX, storeInvButtonY);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void EquipRingOfForging(int windowNumber) {
        int invSlot1X = 0;
        int invSlot1Y = 0;
        
        if (windowNumber == 1) {
            invSlot1X = invSlot1X_W1;
            invSlot1Y = invSlot1Y_W1;
        } else if (windowNumber == 2) {
            invSlot1X = invSlot1X_W2;
            invSlot1Y = invSlot1Y_W2;
        }
        
        bot.moveCursorTo(invSlot1X, invSlot1Y);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void ClickBankFromInFrontOfTeller(int windowNumber) {
        int bankBoothFromBankX = 0;
        int bankBoothFromBankY = 0;
        
        if (windowNumber == 1) {
            bankBoothFromBankX = bankBoothFromBankX_W1;
            bankBoothFromBankY = bankBoothFromBankY_W1;
        } else if (windowNumber == 2) {
            bankBoothFromBankX = bankBoothFromBankX_W2;
            bankBoothFromBankY = bankBoothFromBankY_W2;
        }
        
        bot.moveCursorTo(bankBoothFromBankX, bankBoothFromBankY);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void WithDrawIronOre(int windowNumber) {
        int bankSlot2X = 0;
        int bankSlot2Y = 0;
        
        if (windowNumber == 1) {
            bankSlot2X = bankSlot2X_W1;
            bankSlot2Y = bankSlot2Y_W1;
        } else if (windowNumber == 2) {
            bankSlot2X = bankSlot2X_W2;
            bankSlot2Y = bankSlot2Y_W2;
        }
        
        bot.moveCursorTo(bankSlot2X, bankSlot2Y);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void ClickFurnaceFromBankTeller(int windowNumber) {
        int furnaceFromBankBoothX = 0;
        int furnaceFromBankBoothY = 0;
        
        if (windowNumber == 1) {
            furnaceFromBankBoothX = furnaceFromBankBoothX_W1;
            furnaceFromBankBoothY = furnaceFromBankBoothY_W1;
        } else if (windowNumber == 2) {
            furnaceFromBankBoothX = furnaceFromBankBoothX_W2;
            furnaceFromBankBoothY = furnaceFromBankBoothY_W2;
        }
        
        bot.moveCursorTo(furnaceFromBankBoothX, furnaceFromBankBoothY);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void ClickBankBoothFromFurnace(int windowNumber) {
        int bankBoothFromFurnaceX = 0;
        int bankBoothFromFurnaceY = 0;
        
        if (windowNumber == 1) {
            bankBoothFromFurnaceX = bankBoothFromFurnaceX_W1;
            bankBoothFromFurnaceY = bankBoothFromFurnaceY_W1;
        } else if (windowNumber == 2) {
            bankBoothFromFurnaceX = bankBoothFromFurnaceX_W2;
            bankBoothFromFurnaceY = bankBoothFromFurnaceY_W2;
        }
        
        bot.moveCursorTo(bankBoothFromFurnaceX, bankBoothFromFurnaceY);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void ClickFurnaceFromFurnace(int windowNumber) {
        int furnaceFromFurnaceX = 0;
        int furnaceFromFurnaceY = 0;
        
        if (windowNumber == 1) {
            furnaceFromFurnaceX = furnaceFromFurnaceX_W1;
            furnaceFromFurnaceY = furnaceFromFurnaceY_W1;
        } else if (windowNumber == 2) {
            furnaceFromFurnaceX = furnaceFromFurnaceX_W2;
            furnaceFromFurnaceY = furnaceFromFurnaceY_W2;
        }
        
        bot.moveCursorTo(furnaceFromFurnaceX, furnaceFromFurnaceY);
        try {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (Exception e) {

        }
        bot.mouseClick();
        try {
            Thread.sleep(random.nextInt(1000) + 750);
        } catch (Exception e) {

        }
    }
    
    private void CheckForLevelUp() throws InterruptedException {
        int scrollBarX_W1 = 2428;
        int scrollBarY_W1 = 984;
        int scrollBarX_W2 = 3388;
        int scrollBarY_W2 = 984;
        
        for (int i = 0; i < 28; i++) {
            int red1 = bot.getPixelColor(scrollBarX_W1, scrollBarY_W1).getRed();
            int green1 = bot.getPixelColor(scrollBarX_W1, scrollBarY_W1).getGreen();
            int blue1 = bot.getPixelColor(scrollBarX_W1, scrollBarY_W1).getBlue();

            if (red1 > 150 && green1 > 125 && blue1 > 90) {
                Thread.sleep(random.nextInt(750) + 750);
                ClickFurnaceFromFurnace(1);
                Thread.sleep(random.nextInt(750) + 750);
                bot.type(" ", random.nextInt(400));
                Thread.sleep(random.nextInt(750) + 750);
            }
            
            int red2 = bot.getPixelColor(scrollBarX_W2, scrollBarY_W2).getRed();
            int green2 = bot.getPixelColor(scrollBarX_W2, scrollBarY_W2).getGreen();
            int blue2 = bot.getPixelColor(scrollBarX_W2, scrollBarY_W2).getBlue();

            if (red2 > 150 && green2 > 125 && blue2 > 90) {
                Thread.sleep(random.nextInt(750) + 750);
                ClickFurnaceFromFurnace(2);
                Thread.sleep(random.nextInt(750) + 750);
                bot.type(" ", random.nextInt(400));
                Thread.sleep(random.nextInt(750) + 750);
            }

            Thread.sleep(2750);
        }
    }
}
