package Codeinfer.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileToArrayList {
    public static void main(String aa[]) throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("E:\\Personals\\Codeinfer\\src\\PreProcessing\\keyword.cpp"));
        String s;
        String arrayListTemplate = new String("\tthis.keywords.add(\"");
        String arraylist = new String();
        while((s = in.readLine()) != null)
        {
           arraylist += arrayListTemplate+s+"\");\n";
        }
        System.out.println(arraylist);
    }
}
