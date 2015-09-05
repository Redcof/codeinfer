/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui;

import codeinfer.Super.INFO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soumen
 */
public class ErrorController implements Initializable {
    private Stage stage;
@FXML private Label ErrorMessageLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
            private void back()
            {
                INFO.DISABLE_LOCK();
                this.stage.close();
            }
            @FXML 
            private void close()
            {
                this.back();
            }
    void init(Stage myStage) {        
        this.ErrorMessageLabel.setText(INFO.ERROR);
        this.stage = myStage;
    }
    
}
