/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Codeinfer.Test;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Soumen
 */
public class Basic {
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        FileReader file = new FileReader("C:\\My Computer\\ECLIPSE AND NETBEAN\\Codeinfer\\src\\PreProcessing\\CppProg.cpp");
        BufferedReader in = new BufferedReader(file);
        String line = new String();
       String all = new String();
        while((line = in.readLine()) != null)
            all += line;
        System.out.print(all+"\n");
    }
}
