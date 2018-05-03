package com.timelessapps.javafxtemplate.helpers.threadsandroutines;

import com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener extends Thread implements NativeKeyListener 
{
    private Routine routine;
    
    //When keys are detected, issue commands to the routine. 
    public GlobalKeyListener(Routine routine)
    {
        this.routine = routine;
    }
    
    //Starts listening. 
    public void run()
    {
        try 
        {
            GlobalScreen.registerNativeHook();
            //Disables the excessive logging. 
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);
        }
        catch (NativeHookException ex) {System.err.println("There was a problem registering the native hook.");System.err.println(ex.getMessage());System.exit(1);}

        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(routine));
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
            routine.stopRunning();
            System.out.println("Stopped Running");
        }
        
        if (e.getKeyCode() == NativeKeyEvent.VC_F2) 
        {
            System.out.println("Pressed");
            routine.startRunning();
            System.out.println("Started Running");
        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) 
    {
        
    }

    public void nativeKeyTyped(NativeKeyEvent e) 
    {
        
    }
}