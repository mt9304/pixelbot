package main.java.com.timelessapps.javafxtemplate.app.supportingthreads;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;

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
    	/**
        //Exits only this key listener thread. Uncomment/modify key code as needed. 
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) 
        {
            try {GlobalScreen.unregisterNativeHook();} catch (NativeHookException e1) {e1.printStackTrace();}
        }
        **/
        //Pauses the routine/thread that this listener is supporting. 
        if (e.getKeyCode() == NativeKeyEvent.VC_F1) 
        {
            routine.pauseRunning();
        }
        
        //If routine is paused, starts the routine/thread that this listener is supporting. 
        if (e.getKeyCode() == NativeKeyEvent.VC_F2) 
        {
            routine.startRunning();
        }
        
        //This is the exit button for routine. When the routine stops, this thread should also stop, so remember to look into implementing if needed.  
        if (e.getKeyCode() == NativeKeyEvent.VC_F3) 
        {
            routine.stopRunning();
            try {GlobalScreen.unregisterNativeHook();} catch (NativeHookException e1) {e1.printStackTrace();}
            //Old listener doesn't completely exit.. 
        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) 
    {
        
    }

    public void nativeKeyTyped(NativeKeyEvent e) 
    {
        
    }
}