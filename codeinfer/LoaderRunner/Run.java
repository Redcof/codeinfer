/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codeinfer.LoaderRunner;

import codeinfer.Inferists.CLASS_ResolveClassStructUnion;
import codeinfer.Super.INFO;
import codeinfer.Inferists.CLASS_ResolveMain;
import codeinfer.Inferists.HEADER_PreProcessorDirectiveRemove;
import codeinfer.Inferists.IO_CoutConvert;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.Inferists.IO_CinConvert;
import codeinfer.Inferists.Message;
import codeinfer.PreProcessing.DataTypes;
import codeinfer.PreProcessing.Util;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author soumen
 */
public class Run {
    private static ArrayList<String> KeyWordList ;
    private static ArrayList<String> CppSource;
    private static ArrayList<String> CPPStringList;
    private static StringBuffer CppSourceBuffer = new StringBuffer();
    private static SourcePreProcessor SPP = new SourcePreProcessor();
    public static String DOCUMENT_ROOT_;
    public static String SELECTED_FILE_PART_NAME;
    public static String SELECTED_FILE_PART_OUTPUT_JAVA;
    
    public static final int CIN = 0;
    public static final int FILE_R = 1;    
    public static boolean[] _IMPORTS = {false,false};
	protected static void run(){            
            KeyWordList = INFO.KEYWORDS;
            ///////////////INIT - s/////////////////////
            SPP = new SourcePreProcessor(SELECTED_FILE_PART_NAME);
            CppSourceBuffer  = Util.nullTrimBuffer(new StringBuffer(SPP.getSourceCodeBuffer()));
            SPP.setSourceCodeBuffer(CppSourceBuffer);           
            
            SPP.tokenizer();
            CppSource = SPP.getSrcList();                      
            //////////////INIT - e//////////////////////
            
            //////////////DATATYPES - s/////////////////
            
            //////////////DATATYPES - s/////////////////

            
            boolean thisIsMain = false;
            
            
        
            
            CppSourceBuffer = SPP.getSourceCodeBuffer();
             
            
            //step 1
            IO_CoutConvert IO_COUT = new IO_CoutConvert();
            //CppSourceBuffer = IO_COUT.patchCPPStrings(CppSourceBuffer,INFO.STRINGS_LIST);
            CppSource = IO_COUT.doCoutConvert(CppSource);
            
            
            //step 2
            IO_CinConvert IO_CIN = new IO_CinConvert(SPP, CppSource);
            CppSource = IO_CIN.doCinConvert();
            
            //step 3
            CLASS_ResolveClassStructUnion x = new CLASS_ResolveClassStructUnion(SPP,CppSource);
            CppSource = x.doResolveClassStructUnion();
            
            //step 4
            if(SPP.IsContainMain())
            {
                Util.log("Going to resolve main...", false);
                INFO.MAIN_FILE_NAME = SPP.getFileName()+".java";
                INFO.EXISTS_MAIN = true;
                
                CLASS_ResolveMain RM = new CLASS_ResolveMain();
                CppSource = RM.doResolveMain(CppSource);//Resolving Main
                HEADER_PreProcessorDirectiveRemove HPR_MAIN = new HEADER_PreProcessorDirectiveRemove(SPP.arrayListToStringBuffer(CppSource));
                CppSourceBuffer = HPR_MAIN.getSource();
                thisIsMain = true;                
            }
            else
            {
                Util.log(SPP.getFileName()+" Dosen't contain main",false);
            }
            
            
            
            
            //step last
            if(!thisIsMain){
                HEADER_PreProcessorDirectiveRemove HPR = new HEADER_PreProcessorDirectiveRemove(SPP.arrayListToStringBuffer(CppSource));
                HPR.CaptureRemovePreProcessors();
                //HPR.resolvePreProcessors();                
                CppSourceBuffer = HPR.getSource();                
            }
            
    Util.SaveFile(Run.SELECTED_FILE_PART_OUTPUT_JAVA, new StringBuffer(Util.nullTrimBuffer(CppSourceBuffer)),false);
    }        
}
