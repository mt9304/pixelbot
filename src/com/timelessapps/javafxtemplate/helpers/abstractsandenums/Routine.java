package com.timelessapps.javafxtemplate.helpers.abstractsandenums;

public class Routine extends Thread
{
    private volatile boolean running = true; 
    
    public Routine()
    {
        
    }
     
    public void waitIfNotRunning() throws InterruptedException
    {
        synchronized (this)
        {
            if (!running)
            {
                wait();
            }
        }
    }
    
    public void stopRunning()
    {
        running = false;
    }
    
    public void startRunning()
    {
        running = true;
        synchronized (this)
        {
            notify();
        }
    }
}
