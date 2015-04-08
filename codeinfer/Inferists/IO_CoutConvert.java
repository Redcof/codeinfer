//works fine 30-10-2014 
//But issue in string containing semi colon (;)
package Codeinfer.Inferists;

import codeinfer.PreProcessing.Util;
import java.util.ArrayList;
import Codeinfer.RegEx.Expression;

public class IO_CoutConvert {
	ArrayList<String> sop;	
	ShowIt s = new ShowIt();
       int quote = 0 , insideQuote = 0;
	public ArrayList<String> doCoutConvert(ArrayList<String> srcList){
		 String ResolvedCout = new String("System.out.print(");
		 int StartPosition = 0;
		 int size = srcList.size();
                 int startIndex = 0;
                 if(startIndex == size)
                     return srcList;
                 else{
		 for(; startIndex <= srcList.size()-2;){
                    if(srcList.get(startIndex).equals("cout") && 
                            srcList.get(startIndex+1).equals("<") && 
                            srcList.get(startIndex+2).equals("<")){
                        srcList.remove(startIndex);//cout
			srcList.remove(startIndex);//<
			srcList.remove(startIndex);//<
			StartPosition = startIndex;//point at string or variable
                        while(!srcList.get(startIndex).equals(";")){
                            if(srcList.get(startIndex).equals("<") && 
                                srcList.get(startIndex+1).equals("<")){
				srcList.remove(startIndex);//<
				srcList.remove(startIndex);//<
				ResolvedCout+="+";
                            }
                            else{
				ResolvedCout+=srcList.get(startIndex);
                                srcList.remove(startIndex);//variable or string
                            }
			}
                        ResolvedCout+=")";
                        srcList.add(StartPosition,ResolvedCout);//add System.out.print(...); to list
		}
                else startIndex++;
                //if one cout is resolved then reset it
                if(srcList.get(startIndex).equals(";")){
                    ResolvedCout = "System.out.print(";                             
                }
            } 
        }            
	return srcList;
    }
        
        public StringBuffer doCoutConvert(StringBuffer sourceBuffer,ArrayList<String> strings)
        {
            String STR_RX = Expression.CODEINFER_REPLACE_CPP_STRING;
            Util.message(STR_RX);
            sourceBuffer = new StringBuffer(sourceBuffer.toString().trim());
            String temp = sourceBuffer.toString();
            int i =0;
            for(;i<strings.size();i++)
            {
                temp = temp.replace(STR_RX+i, strings.get(i));
            }
            return new StringBuffer(temp);
        }
}