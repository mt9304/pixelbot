package com.timelessapps.javafxtemplate.helpers.abstractsandenums;

public class Routine extends Thread
{
    protected volatile boolean running = true; 
    protected volatile boolean paused = false; 
     
    public void waitIfNotRunning() throws InterruptedException
    {
        synchronized (this)
        {
            if (paused)
            {
                System.out.println("Pausing. ");
                wait();
            }
        }
    }
    
    public void pauseRunning()
    {
        paused = true;
    }
    
    public void startRunning()
    {
        if (this.getState().equals(Thread.State.WAITING))
        {
            System.out.println("Resuming. ");
            synchronized (this)
            {
                    paused = false;
                    notify();
            }
        }
    }
    
    public void stopRunning()
    {
        System.out.println("Exiting. ");
        running = false;
    }
}
