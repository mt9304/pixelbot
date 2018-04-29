package com.timelessapps.javafxtemplate.helpers.threadsandroutines;

import com.timelessapps.javafxtemplate.helpers.services.GlobalKeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class GlobalKeyListenerThread extends Thread
{
    private Thread thread;
    
    public GlobalKeyListenerThread(Thread thread)
    {
        this.thread = thread;
    }
    
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

        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(thread));
    }
}
