package main.java.com.timelessapps.javafxtemplate.helpers.services;

import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.X;
import static main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates.Y;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.util.Random;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Coordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Duration;

public class RobotService extends Robot
{
    public RobotService() throws AWTException
    {
        //super();
    }
    
    /** Section below is for typing related functions. **/
    private boolean isUpperCase(char c)
    {
        char[] capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char a:capitalLetters)
        {
            if (a == c)
            {
                return true;
            }
        }
        return false;
    }
    
    public void type (String text, int delayBetweenKeyPress)
    {
        String textUpper = text.toUpperCase();
        
        for (int i=0; i < text.length(); ++i)
        {
            if (isUpperCase(text.charAt(i)))
            {
                //For typing capital letters.
                keyType((int)text.charAt(i), KeyEvent.VK_SHIFT);
            }
            else
            {
                //For typing symbols and lowercase. 
                typeChar(textUpper.charAt(i));
            }
            delay(delayBetweenKeyPress);
        }
    }
    
    //Used if getting text from a source that may contain weird symbols and other stuff that is not covered by the normal typing function. 
    public void typeFromClipboard(String text)
    {
        writeToClipboard(text);
        pasteFromClipboard();
    }
    
    public void pasteFromClipboard()
    {
        keyPress(KeyEvent.VK_CONTROL);
        keyPress(KeyEvent.VK_V);
        delay(50);
        keyRelease(KeyEvent.VK_V);
        keyRelease(KeyEvent.VK_CONTROL);
    }
        
    public void writeToClipboard(String s)
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }
    
    private void keyType (int keyCode)
    {
        keyPress(keyCode);
        delay(10);
        keyRelease(keyCode);
    }
	
    private void keyType(int keyCode, int keyCodeModifier)
    {
        keyPress(keyCodeModifier);
        keyPress(keyCode);
        delay(10);
        keyRelease(keyCode);
        keyRelease(keyCodeModifier);
    }

    private void typeChar (char c)
    {
        boolean shift = true;
        int keyCode;

        switch (c)
        {
        case '~':
            keyCode = (int)'`';
            break;
        case '!':
            keyCode = (int)'1';
            break;
        case '@':
            keyCode = (int)'2';
            break;
        case '#':
            keyCode = (int)'3';
            break;
        case '$':
            keyCode = (int)'4';
            break;
        case '%':
            keyCode = (int)'5';
            break;
        case '^':
            keyCode = (int)'6';
            break;
        case '&':
            keyCode = (int)'7';
            break;
        case '*':
            keyCode = (int)'8';
            break;
        case '(':
            keyCode = (int)'9';
            break;
        case ')':
            keyCode = (int)'0';
            break;
        case ':':
            keyCode = (int)';';
            break;
        case '_':
            keyCode = (int)'-';
            break;
        case '+':
            keyCode = (int)'=';
            break;
        case '|':
            keyCode = (int)'\\';
            break;
        case '?':
            keyCode = (int)'/';
            break;
        case '{':
            keyCode = (int)'[';
            break;
        case '}':
            keyCode = (int)']';
            break;
        case '<':
            keyCode = (int)',';
            break;
        case '>':
            keyCode = (int)'.';
            break;
        default:
            keyCode = (int)c;
            shift = false;
        }

        if (shift)
        {
            keyType (keyCode, KeyEvent.VK_SHIFT);
        }
        else
        {
            keyType(keyCode);
        }
    }
    /** End typing section. **/
    
    /** Sections below is for mouse movement related functions. **/
    
    //Randomly selects a curving style with random speed and steps. 
    public void mouseCurve(int endPointX, int endPointY)
    {
            int startingX = getCurrentMousePosition(X);
            int startingY = getCurrentMousePosition(Y);
            
            Random random = new Random();
            
            Boolean curveX = random.nextBoolean();
            Boolean style1 = random.nextBoolean();
            
            int timeInMilis = random.nextInt(2000) + 200;
            int steps = random.nextInt(125) + 75;
            
            if (curveX)
            {
                if (style1)
                {
                    mouseCurveX1(startingX, startingY, endPointX, endPointY, timeInMilis, steps);
                }
                else
                {
                    mouseCurveX2(startingX, startingY, endPointX, endPointY, timeInMilis, steps);
                }
            }
            else
            {
                if (style1)
                {
                    mouseCurveY1(startingX, startingY, endPointX, endPointY, timeInMilis, steps);
                }
                else
                {
                    mouseCurveY2(startingX, startingY, endPointX, endPointY, timeInMilis, steps);
                }
            }
    }
    
    //TODO: When happy with functions, remember to reduce it to one method. 
    //Actual starting X may be different from declared value, since it starts with the startingX + other values. 
    public void mouseCurveX1(int startingX, int startingY, int endpointX, int endpointY, int timeInMilis, int numberOfSteps)
    {
        double distanceOverStepsX = (endpointX - startingX) / ((double) numberOfSteps);
        double distanceOverStepsY = (endpointY - startingY) / ((double) numberOfSteps);
        double timeOverSteps = timeInMilis / ((double) numberOfSteps);
        
        //Runs in 3 phases. starts with i=1 to move Y down a bit, then 0 to go straight, then -1 to move the Y back to original endpoint. 
        for (int i = 1; i >=-1; i--)
        {
            for (int step = 1; step <= numberOfSteps/3; step++) 
            {
                //Accelerates the mouse just a bit in the middle to look more natural. 
                if (i==0)
                {
                    delay(((int) timeOverSteps)*4/5);
                }
                else
                {
                    delay((int) timeOverSteps);
                }
                mouseMove((int) (startingX + distanceOverStepsX * step)+(step*i), (int) (startingY + distanceOverStepsY * step));
            }
            startingX = getCurrentMousePosition(X);
            startingY = getCurrentMousePosition(Y);
        }
    }
    
    public void mouseCurveY1(int startingX, int startingY, int endpointX, int endpointY, int timeInMilis, int numberOfSteps)
    {
        double distanceOverStepsX = (endpointX - startingX) / ((double) numberOfSteps);
        double distanceOverStepsY = (endpointY - startingY) / ((double) numberOfSteps);
        double timeOverSteps = timeInMilis / ((double) numberOfSteps);
        
        for (int i = 1; i >=-1; i--)
        {
            for (int step = 1; step <= numberOfSteps/3; step++) 
            {
                if (i==0)
                {
                    delay(((int) timeOverSteps)*4/5);
                }
                else
                {
                    delay((int) timeOverSteps);
                }
                mouseMove((int) (startingX + distanceOverStepsX * step), (int) (startingY + distanceOverStepsY * step)+(step*i));
            }
            startingX = getCurrentMousePosition(X);
            startingY = getCurrentMousePosition(Y);
        }
    }
    
    public void mouseCurveX2(int startingX, int startingY, int endpointX, int endpointY, int timeInMilis, int numberOfSteps)
    {
        double distanceOverStepsX = (endpointX - startingX) / ((double) numberOfSteps);
        double distanceOverStepsY = (endpointY - startingY) / ((double) numberOfSteps);
        double timeOverSteps = timeInMilis / ((double) numberOfSteps);
        
        for (int i = -1; i <= 1; i++)
        {
            for (int step = 1; step <= numberOfSteps/3; step++) 
            {
               if (i==0)
                {
                    delay(((int) timeOverSteps)*4/5);
                }
                else
                {
                    delay((int) timeOverSteps);
                }
                mouseMove((int) (startingX + distanceOverStepsX * step)+(step*i), (int) (startingY + distanceOverStepsY * step));
            }
            startingX = getCurrentMousePosition(X);
            startingY = getCurrentMousePosition(Y);
        }
    }
    
    public void mouseCurveY2(int startingX, int startingY, int endpointX, int endpointY, int timeInMilis, int numberOfSteps)
    {
        double distanceOverStepsX = (endpointX - startingX) / ((double) numberOfSteps);
        double distanceOverStepsY = (endpointY - startingY) / ((double) numberOfSteps);
        double timeOverSteps = timeInMilis / ((double) numberOfSteps);
        
        for (int i = -1; i <= 1; i++)
        {
            for (int step = 1; step <= numberOfSteps/3; step++) 
            {
               if (i==0)
                {
                    delay(((int) timeOverSteps)*4/5);
                }
                else
                {
                    delay((int) timeOverSteps);
                }
                mouseMove((int) (startingX + distanceOverStepsX * step), (int) (startingY + distanceOverStepsY * step)+(step*i));
            }
            startingX = getCurrentMousePosition(X);
            startingY = getCurrentMousePosition(Y);
        }
    }
    
    public void mouseGlide(int startingX, int startingY, int endpointX, int endpointY, int timeInMilis, int numberOfSteps) 
    {
        double distanceOverStepsX = (endpointX - startingX) / ((double) numberOfSteps);
        double distanceOverStepsY = (endpointY - startingY) / ((double) numberOfSteps);
        double timeOverSteps = timeInMilis / ((double) numberOfSteps);
        
        for (int step = 1; step <= numberOfSteps; step++) 
        {
            delay((int) timeOverSteps);
            mouseMove((int) (startingX + distanceOverStepsX * step), (int) (startingY + distanceOverStepsY * step));
        }
    } 
    
    public int getCurrentMousePosition(Coordinates coord)
    {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        
        int x = (int) b.getX();
        int y = (int) b.getY();
        
        if (coord.equals(X))
        {
            return x;
        }
        else if (coord.equals(Y))
        {
            return y;
        }
        return 0;
    }

    public void mouseClick(int timeInMilis)
    {
        mousePress(InputEvent.BUTTON1_MASK);
        delay(timeInMilis);
        mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    public void mouseRightClick(int timeInMilis)
    {
        mousePress(InputEvent.BUTTON3_MASK);
        delay(timeInMilis);
        mouseRelease(InputEvent.BUTTON3_MASK);
    }
    
     /** End mouse movement section. **/
    public void delay(Duration duration)
    {
        Random random = new Random();
        
        switch (duration) 
        {
        case SHORT: 
            delay(random.nextInt(400)+100);
            break;

        case MEDIUM: 
            delay(random.nextInt(1000)+500);
            break;

        case LONG:
            delay(random.nextInt(4000)+1000);
            break;
            
        default:
            System.out.println("Midweek days are so-so.");
            break;
        }
    }
    
}

