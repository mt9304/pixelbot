package com.timelessapps.javafxtemplate.helpers.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener 
{
    private Thread threadToActionOn;
    
    public GlobalKeyListener(Thread threadToActionOn)
    {
        this.threadToActionOn = threadToActionOn;
    }
    
    public void nativeKeyPressed(NativeKeyEvent e) 
    {
        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) 
        {
            try {GlobalScreen.unregisterNativeHook();} catch (NativeHookException e1) {e1.printStackTrace();}
        }
        
        if (e.getKeyCode() == NativeKeyEvent.VC_F1) 
        {
            System.out.println("Pressed");
            System.out.println(threadToActionOn.getState());
            try
            {
                synchronized(threadToActionOn)
                {
                    threadToActionOn.wait();
                }
            } 
            catch (InterruptedException ex) {Logger.getLogger(GlobalKeyListener.class.getName()).log(Level.SEVERE, null, ex);}
            System.out.println(threadToActionOn.getState());
        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) 
    {
        
    }

    public void nativeKeyTyped(NativeKeyEvent e) 
    {
        
    }
}