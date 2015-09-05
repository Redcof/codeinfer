/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui.project;

import codeinfer.Super.INFO;
import codeinferfui.ProjectBundle;
import codeinferfui.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soumen
 */
public class NewProjectController implements Initializable {

    private Stage stage = new Stage();
    private File lastSelected = new File("/");
    private String LastCPP = "LastCPP";
    private String LastOutput = "LasrOP";
    private boolean EXIT_STATUS[] = {false,false,false};
    private int PROJECT_NAME = 0;
    private int CPP_FILE = 1;
    private int OUTPUT_DIR = 2;
    private ProjectBundle PB = new ProjectBundle();
    
    @FXML private TextField browseCPPinput;
    @FXML private TextField browseOutputDir;
    @FXML private TextField projectName;
    @FXML private Label ErrorMessageLabel;
    @FXML
    public void close()
    {     
        INFO.DISABLE_LOCK();
        this.stage.close();
    }
    
    @FXML
    public void browseCppFile()
    {
       File lastDir = new File(LastCPP);
       if(lastDir.exists())
       {
           try {
               BufferedReader br  = new BufferedReader(new FileReader(LastCPP));
               if(!br.readLine().isEmpty())
               {
                   this.lastSelected = new File(br.readLine());
               }
               else 
                   this.lastSelected = new File("/");
           } catch (Exception ex) {
               Util.SaveFile(LastCPP, "/", false);
               this.lastSelected = new File("/");
           } 
       }
       
       FileChooser fileChooser  = new FileChooser();
       fileChooser.setTitle("Browse C++ File...");       
       //fileChooser.setInitialDirectory(lastSelected);
       fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("C++ Source Code", "*.CPP")               
       );
       File inputCppFile = fileChooser.showOpenDialog(stage);
       if(inputCppFile != null)
       {
           browseCPPinput.setText(inputCppFile.getAbsolutePath());
           Util.SaveFile(LastCPP, inputCppFile.getParent(), false);
           PB.setCPP_FILE(inputCppFile.getAbsolutePath());
           Util.sopln(inputCppFile.getAbsolutePath());
           this.EXIT_STATUS[CPP_FILE] = true;
       }
    }    
    
   @FXML
    public void browseOutputDir()
    {
       File lastDir = new File(LastOutput);
       if(lastDir.exists())
       {
           try {
               BufferedReader br  = new BufferedReader(new FileReader(lastDir));
               this.lastSelected = new File(br.readLine());
           } catch (Exception ex) {
               Util.SaveFile(LastOutput, "/", false);
               this.lastSelected = new File("/");
           } 
       }
       DirectoryChooser dirChooser = new DirectoryChooser();
       dirChooser.setTitle("Browse Output Directory ...");
      
       dirChooser.setInitialDirectory(lastSelected);       
       
       File outputDir = dirChooser.showDialog(stage);
       
       if(outputDir != null)
       {
           browseOutputDir.setText(outputDir.getAbsolutePath());
           Util.SaveFile(LastOutput, outputDir.getAbsolutePath(), false);
           PB.setOUTPUT_DIR(outputDir.getAbsolutePath());
            Util.sopln(outputDir.getAbsolutePath());
           this.EXIT_STATUS[OUTPUT_DIR] = true;
       }       
    }
    
   @FXML
    public void clearInputFields()
    {
        browseOutputDir.setText("");
        browseCPPinput.setText("");
        projectName.setText("");
        ErrorMessageLabel.setText("");
        this.EXIT_STATUS[CPP_FILE] = false;
        this.EXIT_STATUS[OUTPUT_DIR] = false;
        this.EXIT_STATUS[PROJECT_NAME] = false;
        
    }
    
    @FXML 
    public void checkProjectName()
    {
        Matcher mat = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_\\- ]{0,100}$").matcher(projectName.getText());
        if(mat.matches())
        {
           this.EXIT_STATUS[PROJECT_NAME] = true;
           PB.setPROJECT_NAME(mat.group(0));
           
           Util.sopln("PROJECT NAME: "+mat.group(0));
           ErrorMessageLabel.setText("");
        }
        else
        {
            StringBuffer sbf = new StringBuffer(projectName.getText());
            projectName.setText(sbf.deleteCharAt(sbf.length()-1).toString());
            projectName.positionCaret(projectName.getLength());
            ErrorMessageLabel.setText("1-100 Characters(Number Alphabate - _ Space)");
        }
        
    }
    @FXML
    public void setProjectBundle()
    {
        if(this.EXIT_STATUS[PROJECT_NAME] && this.EXIT_STATUS[OUTPUT_DIR] && this.EXIT_STATUS[CPP_FILE]){
            ErrorMessageLabel.setText(PB.saveBundle());
            if(PB.SUCCESS_STATUS)
            {
               INFO.DISABLE_LOCK();
                this.stage.close();
                Util.sopln(PB.toString());
            }
        }
        else
        {
            ErrorMessageLabel.setText("All Fileds Required !!!");
        }
    }    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void init(Stage myStage) {
        this.stage = myStage;
    }
    
}
