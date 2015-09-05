/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui;

import codeinfer.Super.INFO;
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
 * @author codubu
 */
public class CodeinferFUI extends Application {
    private double dragX = 0;
    private double dragY = 0;
    
    


    @Override
    public void start(Stage stage) throws Exception {
        INFO.DISABLE_LOCK();
        INFO.OS               = System.getProperty("os.name");
        INFO.DIRECTORY_SYMBOL = codeinfer.PreProcessing.Util.getDirectorySymbol(codeinfer.PreProcessing.Util.getOS());
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CodeinferFUI.fxml"));
        Parent root = loader.load();
        CodeinferFUIController controller = (CodeinferFUIController)loader.getController();
        controller.init(stage);
        //stage.setAlwaysOnTop(true);
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
    public static void main(String[] args) {
        Application.launch(CodeinferFUI.class);
    }
    
}
