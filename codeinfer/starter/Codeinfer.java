package codeinfer.starter;

//~--- non-JDK imports --------------------------------------------------------

import codeinfer.Inferists.CLASS_GlobalResolve;
import codeinfer.LoaderRunner.Run;
import codeinfer.PreProcessing.DataTypes;
import codeinfer.PreProcessing.KeyWords;

import codeinfer.PreProcessing.SliceFile;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import codeinfer.RegEx.Expression;
import codeinfer.Super.INFO;
import codeinferfui.ProjectBundle;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author soumen
 */
enum OS { Windows, Other }

public class Codeinfer extends Run {
    private static String    InputDIR;
    private static String    OutputDIR;
    private static SliceFile SF;
    private ProjectBundle PB;
    public Codeinfer(ProjectBundle Codeinfer) {
        PB = new ProjectBundle(Codeinfer);
        if (Init()){
            Infer();
            ResolveGlobal();
            Combine();
            RestoreStrings();
            CleanUP();
        }
        
    }

    private static void ResolveGlobal() {
        CLASS_GlobalResolve CGR = new CLASS_GlobalResolve(INFO.SLICED_FILES_NAMES);
        ArrayList<String> globals = CGR.doResolveGlobals();
        
        INFO.GLOBAL_AVAILABLE = CGR.getResolvedStatus();
        if (INFO.GLOBAL_AVAILABLE) {
            INFO.GLOBAL_CLASS_FILE = INFO.TEMPORARY_OUTPUT_DIRECTORY+INFO.DIRECTORY_SYMBOL + CLASS_GlobalResolve.GLOBAL_CLASS+".java";
            INFO.PARTS += 1;
            Util.SaveFile(INFO.GLOBAL_CLASS_FILE,globals, false);
        }
        
    }

    private boolean Init() {
        Util.sopln("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        INFO.OS               = System.getProperty("os.name");
        INFO.DIRECTORY_SYMBOL = Util.getDirectorySymbol(Util.getOS());
        
        double wait = 0000;         
        try {
            Thread.sleep((long) wait);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        
        InputDIR = PB.getCPP_FILE();
        OutputDIR = PB.getOUTPUT_DIR()+INFO.DIRECTORY_SYMBOL+PB.getPROJECT_NAME();
        INFO.INPUT_FILE = InputDIR;
        INFO.OUTPUT_DIRECTORY = OutputDIR;
        INFO.INPUT_FILE_NAME = new File(INFO.INPUT_FILE).getName();
        INFO.INPUT_FILE_NAME_ONLY = Util.extructFileNameOnly(new File(INFO.INPUT_FILE).getName());
        INFO.OUTPUT_FILE_NAME = INFO.OUTPUT_DIRECTORY+INFO.DIRECTORY_SYMBOL+INFO.INPUT_FILE_NAME_ONLY+".java";
        INFO.PROJECT_ROOT = System.getProperty("user.dir");
        INFO.DOCUMENT_ROOT = new File(INFO.INPUT_FILE).getParent();
        
        
        INFO.TEMPORARY_TEMP_DIRECTORY = INFO.OUTPUT_DIRECTORY + INFO.DIRECTORY_SYMBOL + "TEMP";//Contains all sliced cpp files
        INFO.TEMPORARY_OUTPUT_DIRECTORY = INFO.OUTPUT_DIRECTORY + INFO.DIRECTORY_SYMBOL + "OUTPUT";//Contains all Infered java files
        INFO.IMPORTS_PATH = INFO.TEMPORARY_OUTPUT_DIRECTORY+INFO.DIRECTORY_SYMBOL+"imports.codeinfer.unique";
        INFO.WORKING_FILE= INFO.TEMPORARY_OUTPUT_DIRECTORY+"/W" + INFO.INPUT_FILE_NAME;//This file will go to infer
        
        boolean returnValue = true;
        
        Util.log("Input Directory: " + new File(INFO.INPUT_FILE).getParent(), false);
        Util.log("Output Directory: " + INFO.OUTPUT_DIRECTORY, false);
        Util.log("File name: " + new File(INFO.INPUT_FILE).getName(), true);
        Util.log("Working file name: "+INFO.WORKING_FILE,false);
        Util.log("Document root: " + INFO.DOCUMENT_ROOT, false);
        Util.log("Operating System: " + INFO.OS, false);
        
        Util.log("Calling KeyWords...", false);
        INFO.KEYWORDS = KeyWords.getKeyWordsList();
        Util.log("Keyword Listed.", false);
                    
        

        File TEMPORARY_PROCESS_DIR = new File(INFO.TEMPORARY_TEMP_DIRECTORY);
        

        if (!TEMPORARY_PROCESS_DIR.exists()) {
            if (!TEMPORARY_PROCESS_DIR.mkdirs()) {
                Util.log("[CODEINFER-MAIN-CCTD]Cannot Create temporary " + INFO.TEMPORARY_TEMP_DIRECTORY, false);
                returnValue = false;
            }
        }

        if (Util.createDirectory(INFO.OUTPUT_DIRECTORY)) {
            if (Util.createDirectory(INFO.TEMPORARY_OUTPUT_DIRECTORY)) {
                returnValue = true;
            }
        }
        
        Util.SaveFile(INFO.IMPORTS_PATH, "", false);    // Empty Up INFO.IMPOERTS_PATH
        
        INFO.SOURCE_PREPROCESSOR = new SourcePreProcessor(InputDIR);
        StringBuffer sbf = INFO.SOURCE_PREPROCESSOR.getSourceCodeBuffer();
        StringBuffer sbfstrR = INFO.SOURCE_PREPROCESSOR.stringCapture_Replace(sbf);//it will capture anad replace all strings and return StringBuffer
        INFO.STRINGS_LIST = INFO.SOURCE_PREPROCESSOR.getStringTokens();
        INFO.SOURCE_PREPROCESSOR.setSourceCodeBuffer(sbfstrR);       
        
        INFO.SOURCE_PREPROCESSOR.tokenizer();
        
        INFO.SOURCE_PREPROCESSOR.setSrcList(DataTypes.rewritePrimitiveDataTypes(DataTypes.processPrimitiveType(INFO.SOURCE_PREPROCESSOR.getSrcList())));
        
        INFO.SOURCE_PREPROCESSOR.setSourceCodeBuffer(INFO.SOURCE_PREPROCESSOR.arrayListToStringBuffer(INFO.SOURCE_PREPROCESSOR.getSrcList()));
        
        INFO.CPP_SOURCE_SB  = INFO.SOURCE_PREPROCESSOR.getSourceCodeBuffer();
        
        Util.SaveFile(INFO.WORKING_FILE, Util.nullTrimBuffer(INFO.CPP_SOURCE_SB), false);
//Eating all white spaces and save the file to process further
        
        INFO.SOURCE_PREPROCESSOR = new SourcePreProcessor(INFO.WORKING_FILE);        
//        //INFO.SOURCE_PREPROCESSOR.createListOf_CPP_Class_Structure_Union_Enum();
        return returnValue;
    }

    private static void Infer() {
        File fin = new File(InputDIR);
        int  i   = 0;
        Util.log("Callig SliceFile... " + INFO.WORKING_FILE, false);
        SF = new SliceFile(INFO.WORKING_FILE);
        INFO.SLICED_FILES_NAMES = SlicedFilesNames();
        INFO.PARTS = SF.getTotalParts();
        Util.log("Source file Sliced Up.", false);

        ////////////////// Start /////////////////////////

        while (i < SF.getTotalParts()) {
            Run.SELECTED_FILE_PART_NAME = 
            INFO.TEMPORARY_TEMP_DIRECTORY + INFO.DIRECTORY_SYMBOL + SliceFile.SLICED_PART_NAME_PREFIX + i + ".cpp";
            Run.SELECTED_FILE_PART_OUTPUT_JAVA = INFO.TEMPORARY_OUTPUT_DIRECTORY + INFO.DIRECTORY_SYMBOL + SliceFile.SLICED_PART_NAME_PREFIX + i + ".cpp.java";
            Util.log("\n\nSelected part to resolve `" + Run.SELECTED_FILE_PART_NAME + "`", false);
            Run.run();
            i++;
        }
        
        ////////////////// end /////////////////////////
    }

    public static void Combine() {
        Util.log("Strart combining...",false);
        
        if(INFO.PARTS == 0)
        {
            Util.log("Nothing to combine!!!",false);
            return;
        }
        String SlicedFilesPathList[] = new String[INFO.PARTS+1];
        
        SlicedFilesPathList[0] = INFO.IMPORTS_PATH;
        int    i                     = 1;

        while (i < SlicedFilesPathList.length) {
            
                SlicedFilesPathList[i] = INFO.TEMPORARY_OUTPUT_DIRECTORY + INFO.DIRECTORY_SYMBOL
                                         + SliceFile.SLICED_PART_NAME_PREFIX + (i-1) + ".cpp.java";
            Util.sopln(SlicedFilesPathList[i]);
            i++;
        }
        if(INFO.GLOBAL_AVAILABLE)
        {
            i--;
            SlicedFilesPathList[i] = INFO.GLOBAL_CLASS_FILE;
            Util.sopln(SlicedFilesPathList[i]);
        }
        Util.mergeFiles(INFO.OUTPUT_FILE_NAME,SlicedFilesPathList);
    }

    public static String[] SlicedFilesNames() {
        String SlicedFilesPathList[] = new String[SF.getTotalParts()];
        int    i                     = 0;

        while (i < SlicedFilesPathList.length) {
            SlicedFilesPathList[i] = INFO.TEMPORARY_OUTPUT_DIRECTORY + INFO.DIRECTORY_SYMBOL + SliceFile.SLICED_PART_NAME_PREFIX + i + ".cpp.java";
            i++;
        }

        return SlicedFilesPathList;
    }

    private static void ResolveOutlineToInline() {

    //CLASS_OutlineToInline COI = new CLASS_OutlineToInline();
    //CLASS_OutlineToInlineNew COI = new CLASS_OutlineToInlineNew();
    //INFO.CPP_SOURCE_AR = COI.doOutlineToInline(INFO.CPP_SOURCE_AR, INFO.SOURCE_PREPROCESSOR.getClassNamesList());
    //Util.SaveFile(INFO.WORKING_FILE, INFO.CPP_SOURCE_AR, false);
    }

    private static void CleanUP() {
        File OUT = new File(INFO.TEMPORARY_OUTPUT_DIRECTORY);
        String[]OUT_FILE_LIST = OUT.list();
        for(String outputDirs: OUT_FILE_LIST){
            File currentFile = new File(OUT.getPath(),outputDirs);
            Util.log(currentFile.getAbsolutePath()+" file is deleted.", false);
            currentFile.delete();
        }
        OUT.deleteOnExit();
        Util.log(OUT.getAbsolutePath()+" dir is deleted.", false);
        
        File TEMP = new File(INFO.TEMPORARY_TEMP_DIRECTORY);
        String[]entries1 = TEMP.list();
        for(String s: entries1){
            File currentFile = new File(TEMP.getPath(),s);
            Util.log(currentFile.getAbsolutePath()+" file is deleted.", false);            
            currentFile.delete();
        }
        TEMP.deleteOnExit();
        Util.log(TEMP.getAbsolutePath()+" dir is deleted.", false);
    }

    private static void RestoreStrings() {
        Util.show(INFO.STRINGS_LIST.toString());
        SourcePreProcessor spp = new SourcePreProcessor(INFO.OUTPUT_FILE_NAME);
        String exceptStrings[] = spp.getSourceCodeBuffer().toString().split(Expression.CODEINFER_STRING_SPLIT);
        String finalCode = new String();
        int i = 0;
        do
        {
            finalCode += exceptStrings[i]+INFO.STRINGS_LIST.get(i);
            i++;
        }while(i<INFO.STRINGS_LIST.size());
        finalCode += exceptStrings[i];
        Util.sopln(Util.nullTrimBuffer(new StringBuffer(finalCode)).toString());
        Util.SaveFile(INFO.OUTPUT_FILE_NAME, Util.nullTrimBuffer(new StringBuffer(finalCode)), false);
    }
}

