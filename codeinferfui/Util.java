/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codeinferfui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author soumen
 */
public final class Util {
    public static final String INFERING = "Output/INFERING";
    
    
        /**
	 * Display the whole content of the ArrayList
	 * @param arrayListObject
	 */
	
    public static void show(ArrayList<String> arrayListObject,String dlim1,String dlim2)
	{
		int i;
		for(i=0;i<arrayListObject.size();i++)
		{
			System.out.print(dlim1+arrayListObject.get(i)+dlim2);
		}
		System.out.println("\n"+i+" TOKENS HAS READ");
	}
	/**
	 * Display the whole content of the string
	 * @param str
	 */
	
    public static void show(String str)
	{
		System.out.println(str);
	}
	/**
	 * Display the whole content of the string array
	 * @param strArr
	 */
	
	
    public static void show(String []strArr)
	{
		int i;
		for(i = 0;i < strArr.length; i++)
                    System.out.print(strArr[i]);
		System.out.println("\n"+i+" INDICES HAS READ");
	}
        
        
        
    public static void sopln(String msg)
        {
            System.out.println(msg);
        }
        
        
    public static boolean SaveFile(String path,StringBuffer Content,boolean appened)
        {
            boolean success;
            try {
                if(appened)
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path,appened));
                    out.write(Content.toString());
                    out.flush();
                    out.close();                    
                    return true;
                }
                else
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path));
                    out.write(Content.toString());
                    out.flush();
                    out.close();                                        
                    return true;
                }
            } catch (IOException ex) {
                Util.sopln("[U-SFsbuf-IO]Error in IO "+path);
                return  false;
            }
        }
    
    public static boolean SaveFile(String path,String Content,boolean appened)
        {
            
            try {
                if(appened)
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path,appened));
                    out.write(Content);
                    out.flush();
                    out.close();                    
                    return true;
                }
                else
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path));
                    out.write(Content);
                    out.flush();
                    out.close();
                    return true;
                }
            } catch (IOException ex) {
                Util.sopln("[U-SFsbuf-IO]Error in IO "+path);
                return false;
            }
        }
    
    public static boolean SaveFile(String path,ArrayList<String> src,boolean appened)
        {
            try {
                if(appened)
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path,appened));
                    int i = 0;
                    for(;i < src.size();i++)
                        out.write(src.get(i));
                    out.close();
                    return true;
                }
                else
                {
                    BufferedWriter out = new BufferedWriter(new FileWriter(path));
                    int i = 0;
                    for(;i < src.size();i++)
                        out.write(src.get(i));
                    out.close();
                    return true;
                }
            } catch (IOException ex) {
                Util.sopln("[U-SFarrlist-IO]Error in IO "+path);
                return true;
            }
        }
        
        
    public static StringBuffer nullTrimBuffer(StringBuffer buf)
        {
            buf = new StringBuffer(buf.toString().trim());
            Util.log("Removing 'null' form BOF and EOF...",false);
            if(buf.indexOf("null") == 0)
            {
                buf.delete(buf.indexOf("null", 0),4);
            }
            
            if(buf.lastIndexOf("null") == buf.length()-4)
            {    
                buf.delete(buf.lastIndexOf("null"),buf.lastIndexOf("null") + 4);
            }
            buf = new StringBuffer(buf.toString().trim());
            Util.log("Removed 'null' form BOF and EOF",false);
            return buf;
            
        }
        private static final String[][] HTML ={
            {"<","&lt;"},
            {">","&gt;"},
            {" ","&nbsp;"},
            {"\n","<br/>"}
        };
        public static String htmlCode(char tok)
        {
            int i = 0;
            for(;i<HTML.length;i++)
            {
                if(HTML[i][0].equals(tok+""))
                {
                    return HTML[i][1];
                }
            }
            return tok+"";
        }
       public static String htmlEncode(String text)
       {
           int k = 0;
           String temp = "<html><head><style>*{font-family:monospace}</style></head><body>";
           for(;k<text.length();k++)
           {
               Util.show(text.charAt(k)+"");
               if(text.charAt(k) == '<' || text.charAt(k) == '>' || text.charAt(k) == ' ' || text.charAt(k) == '\n')
               {
                   temp += Util.htmlCode(text.charAt(k));
               }
               else
                   temp += text.charAt(k);
           }
           return temp+"</body></html>";
       }
    public static void clearConsole()
        {
            try {
            Runtime.getRuntime().exec("cls");
            } catch (IOException ex) {
                Util.sopln("\nError to clear the console.");
            }
        }
        
        
    public static void mergeFiles(String outPutFileName,String []FileNameList)
        {
            int i = 0;
            BufferedWriter flushOutputFile = null;
            BufferedWriter masterOut = null;
            try {
                flushOutputFile = new BufferedWriter(new FileWriter(outPutFileName));
                flushOutputFile.write("");
                flushOutputFile.close();
                masterOut = new BufferedWriter(new FileWriter(outPutFileName,true));
            } catch (IOException ex) {
                Util.sopln("[U-MF-IO1]Error in open `"+outPutFileName+"`");
            }
            for( ; i < FileNameList.length;i++)
            {
                try {
                    BufferedReader sourceFiles = new BufferedReader(new FileReader(FileNameList[i]));
                    String temp = null;
                    try {
                        while((temp = sourceFiles.readLine()) != null)
                        {
                            masterOut.write(temp+"\n");
                        }
                    } catch (IOException ex) {
                        Util.sopln("[U-MF-IO2]Error in read  `"+FileNameList[i]+"` to write to `"+outPutFileName+"`");
                    }
                } catch (FileNotFoundException ex) {
                    Util.sopln("[U-MF-FNF]Error in open `"+FileNameList[i]+"` to write to `"+outPutFileName+"`");
                }
            }
        try {
            masterOut.close();
        } catch (IOException ex) {
            Util.sopln("[U-MF-IO3]Error in save `"+outPutFileName+"`");
        }
        }
    
    public static void log(String message,boolean startOrEnd)
    {
        File f = new File("Codeinfer.log");
        BufferedWriter bw;
        Util.sopln(message);
        DateFormat dtFrmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dt = new Date();
        
        try {
                bw = new BufferedWriter(new FileWriter(f,true));
                if(!startOrEnd)
                    bw.write("\n"+dtFrmt.format(dt)+" ::: "+message);
                else
                {
                    bw.write("\n================================================================\n"
                            + message+"\n"
                            + "===============================================================\n");
                }
                bw.close();
            } catch (IOException ex) {
                Util.sopln("Error to log >> "+message);
            }
    }

    public static String extructFileNameOnly(String FileName) {
        String name = "";        
            String temp = FileName;
            
            int i = 0;
            for(;i<temp.length();i++)
            {
                if(temp.charAt(i) == '.')
                    break;
                name += temp.charAt(i);
            }
        
        return name;
    }

    public static boolean createDirectory(String OutputDIR) {
        File fout = new File(OutputDIR);
        if(!fout.exists())
        {
            if(!fout.mkdirs())
            {
                Util.log("[U-CD-CCD]Cannot Create "+OutputDIR,false);
                return false;
            }
        }
        return true;
    }

    /**
         * 
         * Remove white spaces
         * @param src
         * 
         * 
         */
        public static String eatWhiteSpace(String src)
        {
            String tempString = new String(src.trim());
            String New = new String();
            String lastChar = new String();
            String nextChar = new String();
            String Char = new String();
            boolean HashFound=false;
            Util.log("Eating white spaces...",false);
            int i = 0;
            for( ; i < tempString.length()-1 ; i++)
            {
                Char = tempString.charAt(i) + "";
                nextChar = tempString.charAt(i+1) + "";
                
                if(Char.equals("#"))
                {
                    HashFound=true;
                }
                
                if((Char.equals(" ") || Char.equals("\t")) && HashFound && 
                        !("~`!#%^&*+=-:;'<>?./{}\\$".contains(lastChar) || 
                        "~`!#%^&*+=-:;'<>?./{}\\$".contains(nextChar) || 
                        nextChar.equals(" ") || 
                        nextChar.equals("\t") || 
                        nextChar.equals("\n") || 
                        lastChar.equals("\n") || 
                        lastChar.equals("\t") || 
                        lastChar.equals(" ")))
                {
                    New += Char;
                }
                else if((Char.equals(" ") || Char.equals("\t")) && !(
                        "~`!#%^&*+=-:;'<>?./\\{]}()$".contains(lastChar) ||
                        "~`!#%^&*+=-:;'<>?./\\{]}()$".contains(nextChar) ||
                        nextChar.equals(" ") ||
                        nextChar.equals("\t") ||
                        nextChar.equals("\n") ||
                        lastChar.equals("\n") || 
                        lastChar.equals("\t") || 
                        lastChar.equals(" ")))
                {
                    New += Char;
                }
               
                else if(Char.equals("\n") && (";{}".contains(lastChar) || HashFound) && !nextChar.equals("\n") )
                {
                     New += Char;
                     if(HashFound)
                         HashFound=false;
                }
                else if(!(Char.equals(" ") || Char.equals("\t") || Char.equals("\n")))
                {
                    New += Char;
                }
               
                if(!(Char.equals(" ")||Char.equals("\t")||Char.equals("\n")))
                    lastChar = Char;
            }
            Util.log("White space eated.",false);
            
            return New+tempString.charAt(i);
        }

    public static String readFile(String cpp_file) {
        String source_array = null;
        
    	try{    		
    @SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(new FileReader(cpp_file));
    	    String line;
            do
    	    {
    	    	line = null;
    	    	line = in.readLine();
    	    	source_array += line+"\n";    	    	
    	    }while(line != null);
            }
            catch(NoSuchFileException e)
            { 
                return null;
            }
	        catch(IOException e)
	        { 
                    return null;
	        }
	        catch(Exception e)
	        {	            
                    return null;	            
	        }        
        return source_array;
    }
        
    public enum OS{
        Windows,Other
    }
    public static OS getOS()
    {
        OS oses = null;
        if(System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))
        {
            return oses.Windows;
        }
        else
        {
            return oses.Other;
        }
    }
    
    public static String getDirectorySymbol(OS os)
    {
        switch(os)
        {
            case Windows:
                return "\\";
            default:
                return "/";
        }
    }
}
