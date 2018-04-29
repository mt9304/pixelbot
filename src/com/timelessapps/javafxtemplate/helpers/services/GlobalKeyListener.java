package com.timelessapps.javafxtemplate.helpers.services;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener 
{
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
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) 
    {
        
    }

    public void nativeKeyTyped(NativeKeyEvent e) 
    {
        
    }
}