package main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums;
public class Routine extends Thread
{
    protected volatile boolean running = true; 
    protected volatile boolean paused = false; 
    
    //
    public void run()
    {
	//For testing. 
	while(running)
	{
	    try 
	    {
		waitIfPaused();
		System.out.println("Running blank routine. ");
		Thread.sleep(100);
		waitIfPaused();
	    } catch (InterruptedException e) {e.printStackTrace();}
	}
    }
    
    public void checkIfPausedOrStopped() throws InterruptedException
    {
	waitIfPaused();
	if (!running)
	{
		//Insert functions that you want ran here after thread exits, such as enableStartButton(); 
		//This is because the functions won't finish running the rest of the method (even finally blocks) if the routine exits while in wait() status. 
	}
    }
     
    public void waitIfPaused() throws InterruptedException
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
        //Need to wake up thread again to run the enable start button before the routine exits. 
        synchronized (this)
        {
        	notify();
        }
    }
    
    public boolean getRunning()
    {
	return running;
    }
    
    public boolean getPaused()
    {
	return paused;
    }
}
