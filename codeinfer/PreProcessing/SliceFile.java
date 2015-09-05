
package codeinfer.PreProcessing;

import codeinfer.Super.INFO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author soumen
 */
public final class SliceFile {
    private static String sourceFIle = new String();
    /**
     * Contains the prefix of Sliced file part name
     */
    public static final String SLICED_PART_NAME_PREFIX = "CODEINFER_SLICEPART_";
    
    private static int SLICED_TOTALPARTS = 0;

    public int getTotalParts() {
        return SLICED_TOTALPARTS;
    }
    
    public SliceFile(String sourceFilePath)
    {
        sourceFIle = sourceFilePath;
        this.sliceIt();        
    }
    
    public void sliceIt()
    {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(sourceFIle));
            
            int currentChar = '\0',
                    PART_NO = 0,
                    lastChar = '\0',
                    curlyBraceCount = 0;
            
            boolean FirstCurlyBraceEncounter = false,
                    multiLineCommentLock = false,
                    singleLineCommentLock = false,
                    inlineObjectLock = false;            
            String inThisScope = "";
            String objectName = "";
            try {
                while((currentChar = fin.read()) != -1)
                {
                    Matcher InlineObjects = Pattern.compile("[a-zA-Z_; \t]").matcher(currentChar+"");
            
                    if(!FirstCurlyBraceEncounter && currentChar == ';' && PART_NO>0)
                    {
                        continue;
                    }
                    else if(!FirstCurlyBraceEncounter && !inlineObjectLock && InlineObjects.find() && PART_NO>0)
                    {
                        if(currentChar == ' ')
                        {
                            
                        }
                        else if(currentChar == ';')
                        {
                            inlineObjectLock = true;
                        }
                        else
                        {
                            objectName += currentChar;
                        }
                    }
                    if(currentChar == '{' && curlyBraceCount == 0 && !FirstCurlyBraceEncounter && !multiLineCommentLock && !singleLineCommentLock)
                    {
                        curlyBraceCount = 1;
                        FirstCurlyBraceEncounter = true;
                    }
                    else if(currentChar == '{' && !multiLineCommentLock && !singleLineCommentLock)
                        curlyBraceCount++;
                    else if(currentChar == '}' && !multiLineCommentLock && !singleLineCommentLock)
                        curlyBraceCount--;
                    //Analize Comments start
                    if(lastChar == '/' && currentChar == '*' && !multiLineCommentLock)//multi line start
                        multiLineCommentLock = true;
                    
                    if (lastChar == '/' && currentChar == '/'&& !singleLineCommentLock)//single line start
                         singleLineCommentLock = true;
                    
                    if(lastChar == '*' && currentChar == '/' && multiLineCommentLock)//multiline end
                        multiLineCommentLock = false;
                    
                    if (singleLineCommentLock && currentChar == 13)//single line end
                        singleLineCommentLock = false;
                    //Analize Comments End
                    
                    inThisScope += (char) currentChar;//adding scope chars
                    
                    lastChar = currentChar;
                    //Saving a scope start
                    if(curlyBraceCount == 0 && currentChar == '}' && !multiLineCommentLock && !singleLineCommentLock)
                    {
                        String SCOPE_PATH = INFO.TEMPORARY_TEMP_DIRECTORY+INFO.DIRECTORY_SYMBOL+SLICED_PART_NAME_PREFIX+PART_NO+".cpp";
                        try{
                            
                            BufferedWriter fout = new BufferedWriter(new FileWriter(SCOPE_PATH));
                            fout.write(inThisScope.trim());
                            fout.close();                            
                            Util.log(PART_NO+". No Part "+SCOPE_PATH, false);
                            
                        }
                        catch(Exception ex)
                        {
                            Util.log("[SF-SI-E]Unable to slice: `"+SCOPE_PATH+".cpp` from `"+sourceFIle+"`",false);
                        }
                        PART_NO++;
                        inThisScope = "";//Flushing Scope
                        FirstCurlyBraceEncounter = false;
                        inlineObjectLock = false;
                    }
                    SLICED_TOTALPARTS = PART_NO;
                    
                }
            } catch (IOException ex) {
                Util.log("[SF-SI-IO]Unable to read: `"+sourceFIle+"` to slice up.",false);
                
            }
        } catch (FileNotFoundException ex) {
            Util.log("[SF-SI-FNF]Unable to open : `"+sourceFIle+"` to slice up.",false);
            
        }
        
    }
    
}
