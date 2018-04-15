package com.timelessapps.javafxtemplate.services;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class RobotService extends Robot
{
    public RobotService() throws AWTException
    {
        //super();
    }
    
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
}