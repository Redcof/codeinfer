/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import codeinfer.LoaderRunner.Loader;
import codeinfer.LoaderRunner.Run;

/**
 *
 * @author soumen
 */
public class Codeinfer extends Run{
    public static void main(String aa[])
    {
        double wait = 00;
        try{
            Thread.sleep((long)wait);
        } catch (InterruptedException ex){
            System.out.println(ex);
        }
        Run.HOME = System.getProperty("user.dir");//  ...\Codeinfer
        Run.run(Loader.loadSource("C:\\Users\\soumen\\Documents\\NetBeansProjects\\Codeinfer\\src\\codeinfer\\PreProcessing\\CppProg.cpp"));
        
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
    }
}

/*
public static String createRegex(String s) {
    StringBuilder b = new StringBuilder();
    for(int i=0; i<s.length(); ++i) {
        char ch = s.charAt(i);
        if ("\\.^$|?*+[]{}()".indexOf(ch) != -1)
            b.append('\\').append(ch);
        else if (Character.isLetter(ch))
            b.append("[A-Za-z]");
        else if (Character.isDigit(ch))
            b.append("\\d");
        else
            b.append(ch);
    }
    return b.toString();
}
*/