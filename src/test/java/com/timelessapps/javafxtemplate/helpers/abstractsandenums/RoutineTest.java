package test.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums;

import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.GlobalKeyListener;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Routine;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class RoutineTest 
{


    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {
	//routine.stopRunning();
    }

    @Test
    public void testWaitIfPaused() throws InterruptedException 
    {
	Routine routine = new Routine();
	routine.setDaemon(true);
	routine.start();
	routine.pauseRunning();
	
	Thread.sleep(500);
	String result = routine.getState().toString();
	assertEquals("WAITING", result);
    }

    @Test
    public void testPauseRunning()
    {
	Routine routine = new Routine();
	routine.setDaemon(true);
	routine.start();

	routine.pauseRunning();
	Boolean result = routine.getPaused();
	assertEquals(true, result);
    }

    @Test
    public void testStartRunning() throws InterruptedException 
    {
	Routine routine = new Routine();
	routine.setDaemon(true);
	routine.start();

	routine.pauseRunning();
	Thread.sleep(500);
	routine.startRunning();
	Boolean result = routine.getRunning();
	assertEquals(true, result);
    }

    //Tests if routine stops after stopping while it is in wait(). 
    @Test
    public void testStopRunning_fromWaitingStatus() throws InterruptedException 
    {
	Routine routine = new Routine();
	routine.setDaemon(true);
	routine.start();

	routine.pauseRunning();
	Thread.sleep(500);
	routine.stopRunning();
	Thread.sleep(500);
	Boolean result = routine.getRunning();
	System.out.println("AfterA: " + routine.getState());
	assertEquals(false, result);
    }
    
    //Tests if routine stops after stopping it while it is still running. 
    @Test
    public void testStopRunning_fromTimedWaitingStatus() throws InterruptedException 
    {
	Routine routine = new Routine();
	routine.setDaemon(true);
	routine.start();

	routine.stopRunning();
	Thread.sleep(500);
	Boolean result = routine.getRunning();
	System.out.println("AfterB: " + routine.getState());
	assertEquals(false, result);
    }

}
