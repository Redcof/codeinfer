/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codeinfer.PreProcessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author soumen
 */
public final class Util {
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
        
        
        public static void message(String msg)
        {
            System.out.println(msg);
        }
        
        public static void SaveFile(String path,StringBuffer Content)
        {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(path));
                out.write(Content.toString());
                out.close();
                Util.message("Content save at "+path);
            } catch (IOException ex) {
                Util.message("Cannot abale to save at "+path);
            }
        }
        public static void SaveFile(String path,ArrayList<String> src)
        {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(path));
                int i = 0;
                for(;i < src.size();i++)
                    out.write(src.get(i));
                out.close();
                Util.message("Content save at "+path);
            } catch (IOException ex) {
                Util.message("Cannot abale to save at "+path);
            }
        }
        
        public static StringBuffer nullTrimBuffer(StringBuffer buf)
        {
            buf = new StringBuffer(buf.toString().trim());
            buf.delete(0,4);
            buf.delete(buf.lastIndexOf("null"),buf.lastIndexOf("null")+4);
            buf = new StringBuffer(buf.toString().trim());
            Util.SaveFile("Output/nullReplaced.cpp", buf);
            return buf;
        }
        
	
}
