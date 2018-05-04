package main.java.com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.com.timelessapps.javafxtemplate.Main;
import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;

public class MainController implements Initializable
{
    private CustomSceneHelper sceneHelper; 
    
    @FXML
    private AnchorPane mainWindow;
    
    //Changes color of window borders when focused. 
    @FXML
    private void toggleBorderColor(Boolean focused)
    {
        if (focused)
        {
            mainWindow.getStyleClass().add("purpleBorder");
            mainWindow.getStyleClass().remove("defaultBorder");
        }
        else if (!focused)
        {
            mainWindow.getStyleClass().remove("purpleBorder");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //For changing border color when focussing and unfocussing. Note that it starts with focused by default, even if program was not focussed when first opening. Functions starts working when first clicking into the program and then out. 
        //https://stackoverflow.com/questions/24038988/event-when-window-stage-lost-focus , https://docs.oracle.com/javafx/2/api/javafx/beans/value/ObservableBooleanValue.html#get()
        Stage stage = Main.getMainStage();
        stage.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
            {
                //ObservableValue ov will be true if window is focused, false if not focused (clicking another window). 
                Boolean focused = ov.getValue();
                toggleBorderColor(focused);
            }
        });
    }    
}
