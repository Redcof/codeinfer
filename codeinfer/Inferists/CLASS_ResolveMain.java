//Working fine 31.10.2014
//steel no issue detected
package codeinfer.Inferists;

import codeinfer.PreProcessing.Util;
import codeinfer.Super.INFO;
import java.util.ArrayList;

public class CLASS_ResolveMain{
	
	public static final String MainTemplate = "public class "+INFO.INPUT_FILE_NAME_ONLY+"{\npublic static void main(String args[])\n";
        
	public CLASS_ResolveMain(){}
        
	public  ArrayList<String> doResolveMain(ArrayList<String> srcList){
            Util.log("Resolving main()...",false);
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
        Util.log("main() Resolved.",false);
	return srcList;
        }
        
//        public  ArrayList<String> doResolveMain(StringBuffer srcList)
//        {
//            
//        }
}
