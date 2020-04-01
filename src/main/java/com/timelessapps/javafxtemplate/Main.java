/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.timelessapps.javafxtemplate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.timelessapps.javafxtemplate.controllers.contentarea.LogsPageController;
import main.java.com.timelessapps.javafxtemplate.helpers.coords.RSCoordinates;

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
        	if (!args[0].isEmpty())
        	{
        		System.out.println("starting");
        		RSCoordinates rsc = new RSCoordinates();
        		int y = rsc.getOffsetY();
        		int x = rsc.getOffsetX(y);
        		System.out.println("X: " + x  + " " + "Y: " + y);
        		return;
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
