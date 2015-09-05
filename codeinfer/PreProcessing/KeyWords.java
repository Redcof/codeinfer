package codeinfer.PreProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KeyWords {
	private static ArrayList<String> keyWords;
	
	public static ArrayList<String> getKeyWordsList()
	{
            try{
                @SuppressWarnings("resource")
		BufferedReader fileReader = new BufferedReader(new FileReader("src/codeinfer/PreProcessing/keyword.cpp"));//open file for reading
			String line = null;
                        KeyWords.keyWords = new ArrayList();
                        Util.log("Reading keywords....", false);
                            while((line=fileReader.readLine())!=null){
					keyWords.add(line);                                        
                            }
                            Util.log("Keywords.cpp file has read.",false);
		}
		catch(FileNotFoundException e)
		{
                    Util.log("[KWS-GKW-FNF] File not found.From KeyWords.java",false);
		}
		catch(IOException e)
		{
                    Util.log("[KWS-GKW-IO] Cannot Read the file.From KeyWords.java",false);
		}
		catch(Exception e)
		{
                    Util.log("[KWS-GKW-EX] Unknown Error.From KeyWords.java"+e,false);
		}
            Util.log("Keywords has read.", false);
            return keyWords;
            }
	}

