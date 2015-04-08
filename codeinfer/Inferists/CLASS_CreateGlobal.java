package Codeinfer.Inferists;
import java.util.ArrayList;

import Codeinfer.PreProcessing.DataType;
import Codeinfer.PreProcessing.SourcePreProcessor;

class CLASS_CreateGlobal{

    private ArrayList<String> template = new ArrayList<String>();
    private String customClassName = new String("CODEINFER_GlobalVar");
    int i, count =0,startpos;
    public ArrayList<String> doCreateGlobal(ArrayList<String> srcList,SourcePreProcessor spp){
        template.add("public class "+customClassName+"{\n public ");
        for(i=0;i<srcList.size();i++)
        {
            if(srcList.get(i).equals("{"))
              count ++;
            else if(srcList.get(i).equals("}"))
              count --;
            else if(count == 0 && (DataType.isDataType(srcList.get(i), spp)))
             { 
              startpos=i;
              do
              {
                template.add(srcList.get(i));
                srcList.remove(i);
              
              }while(!srcList.get(i).equals(";"));
            }
  
  }
  for(int k = 0; k < template.size();k++)
  {
  while(template.isEmpty())
    { 
      
      srcList.add(startpos++,template.get(k));
    }
  }
return srcList;
  }
  }
