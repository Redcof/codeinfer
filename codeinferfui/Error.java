/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Soumen
 */
public class Error  extends Application{
    private double dragX = 0;
    private double dragY = 0; 
    @Override
    public void start(Stage stage) throws Exception { 
        
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Error.fxml"));
        Parent root = loader.load();
        ErrorController controller = (ErrorController)loader.getController();
        controller.init(stage);
        stage.setAlwaysOnTop(true);
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>(){           
            @Override
            public void handle(MouseEvent event) {
                dragX = event.getScreenX() - stage.getX();
                dragY = event.getScreenY() - stage.getY();
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){           
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - dragX);
                stage.setY(event.getScreenY() - dragY);
            }
        });  
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
   

    
    
}
