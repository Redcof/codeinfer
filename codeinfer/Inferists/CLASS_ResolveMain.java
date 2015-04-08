//Working fine 31.10.2014
//steel no issue detected
package Codeinfer.Inferists;

import java.util.ArrayList;

public class CLASS_ResolveMain {
	
	String MainTemplate = new String();
	public CLASS_ResolveMain(String FileName){
            if(FileName.isEmpty())
                FileName = "Demo";
            
            MainTemplate = " class " + FileName + " {\n public static void main(String args[])\n";
	}
	public  ArrayList<String> doResolveMain(ArrayList<String> srcList){            
	for(int i=0;i < srcList.size(); i++){           
            if(srcList.get(i).equals("main")){
                srcList.remove(i);//main                        
                if(srcList.get(i-2).equals("int") || srcList.get(i-2).equals("void"))
                    srcList.remove(i-2);//int or void
                srcList.add(i-1,this.MainTemplate);
                for( ; i < srcList.size();)
                    if(srcList.get(i).equals("{"))
                        break;
                    else
                        srcList.remove(i);//(.....)
                
            }
	}
        srcList.add("}");
	return srcList;
        }
        
//        public  ArrayList<String> doResolveMain(StringBuffer srcList)
//        {
//            
//        }
}
