/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinfer.PreProcessing;

/**
 *
 * @author soumen
 */
public class ErrorCode {
    public static final String IO ="IO";//IOException
    public static final String FNF ="FNF";//File Not Found
    public static final String AIOB = "AIOB";//Array INdex Out Of Bound Exception
    public static final String CCTD = "CCTD";//Cannot Create Temporary Directory
    public static final String CCOD = "CCOD";//Cannot Create Output Directory
    public static final String CSF = "CSF";//Cannot Slice File
    public static final String CCD = "CCD";//Cannot Create Directory
    
    public static final String[][] ERROR_CODES={
        {"IO","IOException"},
        {"FNF","NoSuchFileFoundException"},
        {"AIOB","ArrayIndexOutOfBound"},
        {"CCTD","Cannot Crate Temporary Directory"},
        {"CCOD","Cannot Create Output Directory"},
        {"CSF","Cannot Slice File"},
        {"CCD","Cannot Create Directory"},
        {"CODEINFER","Codeinfer"},
        {"RUN","RUN"},
        {"MAIN","MAIN"},
        {"U","Util"},
        {"MF","MergeFile"}
        
    };
    
    
    
    public static final void ErrorFormat()
    {
        Util.sopln("Example: Error Code[SPP-tok-IO]");
        Util.sopln("Classname-functionName-Error");
    }
    
    
    
    public static final void errorCodeDescription(String errCode)
    {
        switch(errCode)
        {
            case "IO-01":
                Util.sopln("IOException");
                break;
        }
    }
    
    
    
}
