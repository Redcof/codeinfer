package Codeinfer.Test;

import Codeinfer.Inferists.IO_CoutConvert;
import Codeinfer.Inferists.ShowIt;
import codeinfer.PreProcessing.Util;
import java.util.ArrayList;


public class _1CoutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> srcList = new ArrayList<String>();
		//System.out.println("cout<<\"Hello World\"<<i;");
		//srcList.add("Hel");
		srcList.add("int");
                srcList.add("in");
                srcList.add(";");
                srcList.add("cout");
		srcList.add("<");
		srcList.add("<");
		srcList.add("\"");
		srcList.add("hello");
		srcList.add("world");
		srcList.add("\"");
		srcList.add("<");
		srcList.add("<");
		srcList.add("i");
		srcList.add("<");
		srcList.add("<");
		srcList.add("j");
		srcList.add(";");
                srcList.add("int");
                srcList.add("in");
                srcList.add(";");
		Util.show(srcList, "", "");
		IO_CoutConvert cout = new IO_CoutConvert();
		srcList = cout.doCoutConvert(srcList);
		for(int i = 0 ;i < srcList.size(); i++)
		{
			System.out.print(srcList.get(i));
		}
               /* 
                String goo = new String("Hello World cout");
                int i = 0;
             
                for(  ; i < srcList.size() ; i++)
                {
                    System.out.println(i+"  Cout have Found at: "+srcList.get(i).indexOf("cout"));
                    
                }
           */
                
	}

}
