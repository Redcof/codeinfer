/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codeinfer.LoaderRunner;

import Codeinfer.Inferists.CLASS_ResolveMain;
import Codeinfer.Inferists.HEADER_PreProcessorDirectiveRemove;
import Codeinfer.Inferists.IO_CoutConvert;
import Codeinfer.PreProcessing.KeyWords;
import Codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import java.util.ArrayList;

/**
 *
 * @author soumen
 */
public class Run {
    private static ArrayList<String> KeyWordList ;
	private static ArrayList<String> CppSource;
        private static ArrayList<String> StringList;
	private static StringBuffer CppSourceBuffer = new StringBuffer();
	private static SourcePreProcessor SPP = new SourcePreProcessor();
        public static String HOME;
	protected static void run(SourcePreProcessor spp){
            KeyWordList = KeyWords.getKeyWordsList();		
            SPP = spp;
            CppSource = SPP.getSrcList();
            Util.SaveFile("Output/Parsed_String_list.java", CppSource);
            
            StringList = SPP.getStringTokens();
            
            CppSourceBuffer = SPP.getSourceCodeBuffer();
            
            Util.SaveFile("Output/Parsed_String_Buffer.cpp", CppSourceBuffer);
            
            //step 1
            IO_CoutConvert IO_COUT = new IO_CoutConvert();
            CppSourceBuffer = IO_COUT.doCoutConvert(CppSourceBuffer,StringList);
            CppSource = IO_COUT.doCoutConvert(CppSource);
            
            Util.SaveFile("Output/COUT_LIST.java", CppSource);
            
            CLASS_ResolveMain RM = new CLASS_ResolveMain(SPP.getFileName());
            CppSource = RM.doResolveMain(CppSource);
            //step last
            HEADER_PreProcessorDirectiveRemove HPR = new HEADER_PreProcessorDirectiveRemove(SPP.arrayListToStringBuffer(CppSource));
            CppSourceBuffer = HPR.getSource();
            Util.SaveFile("Output/HEADER_REMOVE_BUFFER.java", CppSourceBuffer);
            Util.SaveFile("Output/HEADER_REMOVE_LIST.java", CppSource);
            
		
		
		
	
            
            Util.message("*********************************"
                    + "  Final SOurce Code ***************************************");
            //Util.show(CppSource,"","");
            
            
            
            Util.SaveFile("Output/"+SPP.getFileName()+".java", new StringBuffer(CppSourceBuffer.toString().trim()));
            
            
}
            
}
