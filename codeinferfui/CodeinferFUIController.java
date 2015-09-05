/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui;

import codeinfer.Super.INFO;
import codeinfer.starter.Codeinfer;
import codeinferfui.project.NewProject;
import java.awt.Desktop;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soumen
 */
public class CodeinferFUIController implements Initializable {
    public static boolean invoked = false;
    private Stage stage;
    private ProjectBundle PB = new ProjectBundle();
    @FXML private HTMLEditor consoleArea;
    @FXML private Hyperlink openOutputHP;
    @FXML private Label ProjectName;
    private String OUTPUT_FILE_PATH;
    private boolean OPEN_STATUS = false;
    private boolean INFERED = false;
    @FXML
    public void openNewProjectFUI()
    {
        if(INFO.isLocked()) return;
        NewProject NP = new NewProject();
        try {            
            INFO.ENABLE_LOCK();
            NP.start(new Stage());                           
            
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void exit()
    {
        if(!INFO.isLocked())
            System.exit(0);
    }
    
    @FXML
    public void openCodeinfer()
    {
       if(INFO.isLocked())
           return;
       INFO.ENABLE_LOCK();
        FileChooser fileChooser  = new FileChooser();
       fileChooser.setTitle("Browse Codeinfer File...");
       fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Codeinfer Project File", "*.codeinfer")               
       );
       File inputCodeinferFile = fileChooser.showOpenDialog(stage);
       if(inputCodeinferFile != null)
       {    
           String path = inputCodeinferFile.getAbsolutePath();
           Util.sopln(path);
           Util.show("THIS .codeinfr FILE SELECTED: "+path);
           PB = new ProjectBundle(PB.readBundle(path));
           Util.sopln(PB.toString());
           if(new File(PB.getCPP_FILE()).exists())
           {
               String text = Util.readFile(PB.getCPP_FILE());
               consoleArea.setHtmlText(Util.htmlEncode(Util.nullTrimBuffer(new StringBuffer(text)).toString()));                 
               openOutputHP.setText("");
               ProjectName.setText(PB.getPROJECT_NAME());
               OPEN_STATUS = true;
           }
           else
           {
               INFO.ERROR = "Source file has been moved!!!";
               Error e = new Error();
               try {
                   e.start(new Stage());
               } catch (Exception ex) {
                   Logger.getLogger(CodeinferFUIController.class.getName()).log(Level.SEVERE, null, ex);
               }
               Util.show("Source File Has Moved !!!");
           }
       }
       INFO.DISABLE_LOCK();
    }
    @FXML public void infer()
    {
        if(!OPEN_STATUS) return;
        INFO.ENABLE_LOCK();
        Codeinfer codeinfer;
        codeinfer = new Codeinfer(PB);
        OUTPUT_FILE_PATH = INFO.OUTPUT_FILE_NAME;
        INFO.ERROR= "Please wait!!! Its going on...";
        openOutputHP.setText(INFO.INPUT_FILE_NAME_ONLY+".java");        
        INFO.DISABLE_LOCK();
        OPEN_STATUS = false;
        INFERED = true;
    }
    @FXML
    public void openCpp()
    {
        if(INFO.isLocked()) return;
        INFO.ERROR = "C++ Reference not found.";
        open("src/Help/C++.pdf");
    }
    
    @FXML
    public void openJava()
    {
        if(INFO.isLocked()) return;
        INFO.ERROR = "JAVA Reference not found";
        open("src/Help/JAVA.pdf");
    }
    
    @FXML
    public void openAbout()
    {
        if(INFO.isLocked()) return;
        INFO.ERROR = "About file hot found.";
        About about = new About();
        try {
            about.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CodeinferFUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void openOutPut()
    {
        
        Util.sopln("This file will open: "+OUTPUT_FILE_PATH);
        open(OUTPUT_FILE_PATH);
        INFERED = false;
    }
    
    public void open(String path)
    {
        try {
            if(!new File(path).exists())
            {
                Error e = new  Error();
                e.start(new Stage());
            }
            Desktop.getDesktop().open(new File(path));
        } catch (Exception ex) {
            
        }
    }
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void init(Stage myStage) {
        this.stage = myStage;
    }
}
