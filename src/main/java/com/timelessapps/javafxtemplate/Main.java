/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.timelessapps.javafxtemplate;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.GrandExchangeRoutine;
import main.java.com.timelessapps.javafxtemplate.app.supportingthreads.GlobalKeyListener;
import main.java.com.timelessapps.javafxtemplate.controllers.contentarea.LogsPageController;
import main.java.com.timelessapps.javafxtemplate.helpers.OCR.RSImageReader;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;
import main.java.com.timelessapps.javafxtemplate.helpers.services.RobotService;

public class Main extends Application
{
    private static Stage stage;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.initStyle(StageStyle.UNDECORATED);
        setPrimaryStage(stage);
        setPrimaryScene(scene);
        Parent root = FXMLLoader.load(getClass().getResource("view/fxml/alwaysdisplayed/Main.fxml"));

        scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();  
    }
    
    //If no argument, launch with JavaFX UI, else just do the routine. 
    public static void main(String[] args)
    {
    	try 
    	{
        	if (args[0].equals("ge"))
        	{
        		
        		System.out.println("starting");
        		GrandExchangeRoutine grandExchangeRoutine = new GrandExchangeRoutine(args[1]);
        		grandExchangeRoutine.setDaemon(true);
        		grandExchangeRoutine.start();

        		GlobalKeyListener globalKeyListener = new GlobalKeyListener(grandExchangeRoutine);
        		globalKeyListener.setDaemon(true);
        		globalKeyListener.start();
        		
        	}
    	}
    	catch (Exception e)
    	{
    		launch(args);
    	}
    }

    private void setPrimaryStage(Stage stage) 
    {
        Main.stage = stage;
    }
    
    public static Stage getMainStage() 
    {
        return Main.stage;
    }
    
    private void setPrimaryScene(Scene scene)
    {
        Main.scene = scene;
    }
    
    public static Scene getMainScene()
    {
        return Main.scene;
    }
}
