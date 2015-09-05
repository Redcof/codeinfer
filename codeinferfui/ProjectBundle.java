/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui;
import codeinfer.Super.INFO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.stage.Stage;

/**
 *
 * @author Soumen
 */
public class ProjectBundle implements Serializable{
    private String CPP_FILE;
    private String OUTPUT_DIR;
    private String PROJECT_NAME;
    public static final String EXTENTION = ".codeinfer";
    public boolean SUCCESS_STATUS;

    public ProjectBundle(String projectName, String cppFile, String outputDir) {
        this.PROJECT_NAME = projectName;
        this.CPP_FILE = cppFile;
        this.OUTPUT_DIR = outputDir;
        this.SUCCESS_STATUS = false;
    }
    
    public ProjectBundle() {
        this.CPP_FILE = new String();
        this.OUTPUT_DIR = new String();
        this.PROJECT_NAME = new String();
        this.SUCCESS_STATUS = false;
    }
    
    public ProjectBundle(ProjectBundle pb)
    {
        this.CPP_FILE = pb.CPP_FILE;
        this.OUTPUT_DIR = pb.OUTPUT_DIR;
        this.PROJECT_NAME = pb.PROJECT_NAME;
        this.SUCCESS_STATUS = false;
    }
    public String getCPP_FILE() {
        return CPP_FILE;
    }

    public void setCPP_FILE(String CPP_FILE) {
        this.CPP_FILE = CPP_FILE;
    }

    public String getOUTPUT_DIR() {
        return OUTPUT_DIR;
    }

    public void setOUTPUT_DIR(String OUTPUT_DIR) {
        this.OUTPUT_DIR = OUTPUT_DIR;
    }

    
    public String getPath()
    {
        return this.OUTPUT_DIR+INFO.DIRECTORY_SYMBOL+this.PROJECT_NAME;
    }
    public String getBundilName()
    {
        return this.OUTPUT_DIR+INFO.DIRECTORY_SYMBOL+this.PROJECT_NAME+INFO.DIRECTORY_SYMBOL+ProjectBundle.EXTENTION;
    }
    public String saveBundle()
    {
        FileOutputStream FOS;ObjectOutputStream OOS;
        try {
            if(Util.createDirectory(this.getPath())){
            FOS = new FileOutputStream(this.getBundilName());
            OOS = new ObjectOutputStream(FOS);
            ProjectBundle pb = new ProjectBundle(PROJECT_NAME, CPP_FILE, OUTPUT_DIR);
            OOS.writeObject(pb);
            OOS.flush();
            OOS.close();
            this.SUCCESS_STATUS = true;
            return "Saved!!!";
            }
            else
            {
                this.SUCCESS_STATUS = false;
                INFO.ERROR = "Unable to save!!!";
                Error e=  new Error();
                e.start(new Stage());
                return "Unable to save!!!";
                
            }
        } catch (Exception ex) {
            this.SUCCESS_STATUS = false;
            return ex.toString();
        }
       
    }
    
    public ProjectBundle readBundle(String path)
    {
        FileInputStream FIS;
        ObjectInputStream OIS;
        ProjectBundle PB = new ProjectBundle();
        try {
           FIS = new FileInputStream(path); 
            OIS = new ObjectInputStream(FIS);
            PB = (ProjectBundle)OIS.readObject();
            OIS.close();
            FIS.close();
        } catch (Exception ex) {
            PB = new ProjectBundle();
        }
        return PB;
    }

    public String getPROJECT_NAME() {
        return PROJECT_NAME;
    }

    public void setPROJECT_NAME(String PROJECT_NAME) {
        this.PROJECT_NAME = PROJECT_NAME;
    }
    @Override
    public String toString()
    {
        return "Project Name: "+this.PROJECT_NAME+"\nC++ File: "+this.CPP_FILE+"\nOutput Directory: "+this.OUTPUT_DIR;
    }
}
