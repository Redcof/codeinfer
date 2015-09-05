package codeinfer.Super;


import codeinfer.PreProcessing.ClassObjectList;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.StructureObjectList;
import codeinfer.PreProcessing.UnionObjectList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soumen
 */
public final class INFO {
    public static SourcePreProcessor SOURCE_PREPROCESSOR;
    public static ArrayList<String> CPP_SOURCE_AR;
    public static String INPUT_FILE;
    public static String INPUT_FILE_NAME_ONLY;
    public static String INPUT_FILE_NAME;
    public static String WORKING_FILE;
    public static String DOCUMENT_ROOT;
    public static String OUTPUT_DIRECTORY;
    public static String DIRECTORY_SYMBOL;
    public static String TEMPORARY_TEMP_DIRECTORY;
    public static String[] SLICED_FILES_NAMES;
    public static ArrayList<String> KEYWORDS;
    public static String IMPORTS_PATH ;//It will contains all imports   
    public static ArrayList<ClassObjectList> CLASS_OBJECTS;
    public static ArrayList<StructureObjectList> STRUCTURE_OBJECTS;
    public static ArrayList<UnionObjectList> UNION_OBJECTS;
    public static String OS;
    public static String TEMPORARY_OUTPUT_DIRECTORY;
    public static StringBuffer CPP_SOURCE_SB;
    public static String MAIN_FILE_NAME;
    public static String GLOBAL_CLASS_FILE;
    public static boolean GLOBAL_AVAILABLE;
    public static int PARTS;
    public static boolean EXISTS_MAIN = false;
    public static ArrayList<String> STRINGS_LIST;
    public static String PROJECT_ROOT;
    public static String OUTPUT_FILE_NAME;
    public static final String LOCK_FILE = "lock";
    public static String ERROR;
    
    public static final void ENABLE_LOCK()
    {
        try {
            FileWriter fw = new FileWriter(INFO.LOCK_FILE);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(INFO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static final void DISABLE_LOCK()
    {
        if(INFO.isLocked())
            new File(INFO.LOCK_FILE).delete();
    }
    
    public static boolean isLocked()
    {
        return new File(INFO.LOCK_FILE).exists();
    }
       
}
