package Codeinfer.PreProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KeyWords {
	private static ArrayList<String> keyWords;
	
	public static ArrayList<String> getKeyWordsList()
	{
            System.out.println("********************** keywords.java start ***************************");
            try{
                    String KeyWordsFileHome = System.getProperty("user.dir");
                @SuppressWarnings("resource")
		BufferedReader fileReader = new BufferedReader(new FileReader(KeyWordsFileHome+"\\src\\codeinfer\\PreProcessing\\keyword.cpp"));//open file for reading
			String line = null;
                        KeyWords.keyWords = new ArrayList<String>();
                            while((line=fileReader.readLine())!=null){
					keyWords.add(line);
                            }
                            System.out.println("Keywords.cpp file has read.");
			}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.From KeyWords.java");
		}
		catch(IOException e)
		{
			System.out.println("Cannot Read the file.From KeyWords.java");
		}
		catch(Exception e)
		{
			System.out.println("Unknown Error.From KeyWords.java"+e);
		}
            System.out.println("********************** keywords.java end ***************************");
		return keyWords;
		}
	}

