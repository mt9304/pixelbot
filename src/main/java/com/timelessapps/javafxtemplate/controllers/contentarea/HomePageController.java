package main.java.com.timelessapps.javafxtemplate.controllers.contentarea;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.GrandExchangeRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.HighAlchRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.HumidifyRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MainBotRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MeleeRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.DC_PatrolRoutine;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.SplashRoutine;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.GlobalKeyListener;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class HomePageController implements Initializable {
	LoggingService log = new LoggingService();
	RobotService bot;
	private static boolean hasStarted = false;

	public HomePageController() {

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (!hasStarted) {
			log.appendToEventLogsFile("ApplicationStarted. ");
			log.appendToApplicationLogsFile("ApplicationStarted. ");
		}
		hasStarted = true;
	}

	//For NMZ
	@FXML
	public void startApplication(MouseEvent event) throws InterruptedException, AWTException {
		MainBotRoutine mainBotRoutine = new MainBotRoutine();
		mainBotRoutine.setDaemon(true);
		mainBotRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(mainBotRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}

	@FXML
	public void startAlchRoutine(MouseEvent event) throws InterruptedException, AWTException {
		HighAlchRoutine highAlchRoutine = new HighAlchRoutine();
		highAlchRoutine.setDaemon(true);
		highAlchRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(highAlchRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}

	@FXML
	public void startSplashRoutine(MouseEvent event) throws InterruptedException, AWTException {
		SplashRoutine splashRoutine = new SplashRoutine();
		splashRoutine.setDaemon(true);
		splashRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(splashRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}
	
	@FXML
	public void startHumidifyRoutine(MouseEvent event) throws InterruptedException, AWTException {
		HumidifyRoutine humidifyRoutine = new HumidifyRoutine();
		humidifyRoutine.setDaemon(true);
		humidifyRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(humidifyRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}

	@FXML
	public void startGrandExchangeRoutine(MouseEvent event) throws InterruptedException, AWTException {
		GrandExchangeRoutine grandExchangeRoutine = new GrandExchangeRoutine("");
		grandExchangeRoutine.setDaemon(true);
		grandExchangeRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(grandExchangeRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}
	
	@FXML
	public void startMeleeRoutine(MouseEvent event) throws InterruptedException, AWTException {
		MeleeRoutine meleeRoutine = new MeleeRoutine("");
		meleeRoutine.setDaemon(true);
		meleeRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(meleeRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}
        
        @FXML
	public void startPatrolRoutine(MouseEvent event) throws InterruptedException, AWTException {
		DC_PatrolRoutine patrolRoutine = new DC_PatrolRoutine();
		patrolRoutine.setDaemon(true);
		patrolRoutine.start();

		GlobalKeyListener globalKeyListener = new GlobalKeyListener(patrolRoutine);
		globalKeyListener.setDaemon(true);
		globalKeyListener.start();

		// botRoutine.join();
		// System.out.println("Remember to re-active button. ");
	}
}
