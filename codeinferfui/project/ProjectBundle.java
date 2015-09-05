/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinferfui.project;
import codeinfer.Super.INFO;
import codeinferfui.Util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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

    private ProjectBundle(String projectName, String cppFile, String outputDir) {
        this.PROJECT_NAME = projectName;
        this.CPP_FILE = cppFile;
        this.OUTPUT_DIR = outputDir;
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

    public ProjectBundle() {
        this.CPP_FILE = new String();
        this.OUTPUT_DIR = new String();
        this.PROJECT_NAME = new String();
        this.SUCCESS_STATUS = false;
    }
    public String getPath()
    {
        return this.OUTPUT_DIR+INFO.DIRECTORY_SYMBOL+this.PROJECT_NAME;
    }
    public String getBundilName()
    {
        return this.OUTPUT_DIR+INFO.DIRECTORY_SYMBOL+this.PROJECT_NAME+INFO.DIRECTORY_SYMBOL+this.PROJECT_NAME+ProjectBundle.EXTENTION;
    }
    public String saveBundle()
    {
        FileOutputStream FOS;ObjectOutputStream OOS;
        try {
            if(Util.createDirectory(this.getPath())){
            FOS = new FileOutputStream(this.getBundilName());
            OOS = new ObjectOutputStream(FOS);
            ProjectBundle pb = new ProjectBundle(CPP_FILE, CPP_FILE, CPP_FILE);
            OOS.writeObject(pb);
            OOS.flush();
            OOS.close();
            this.SUCCESS_STATUS = true;
            return "Saved!!!";
            }
            else
            {
                this.SUCCESS_STATUS = false;
                return "Unable to save.";
            }
        } catch (Exception ex) {
            this.SUCCESS_STATUS = false;
            return ex.toString();
        }
       
    }
    
    public ProjectBundle readBundle(String path)
    {
        FileInputStream FIS;ObjectInputStream OIS;
        ProjectBundle PB;
        try {
           FIS = new FileInputStream(path); 
            OIS = new ObjectInputStream(FIS);
            PB = (ProjectBundle)OIS.readObject();
        } catch (Exception ex) {
            PB = null;
        }
        return PB;
    }

    public String getPROJECT_NAME() {
        return PROJECT_NAME;
    }

    public void setPROJECT_NAME(String PROJECT_NAME) {
        this.PROJECT_NAME = PROJECT_NAME;
    }
    
}
