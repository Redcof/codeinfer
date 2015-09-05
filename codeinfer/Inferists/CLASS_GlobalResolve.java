package codeinfer.Inferists;
import java.util.ArrayList;

import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import codeinfer.Super.INFO;

public class CLASS_GlobalResolve{

    public static final String GLOBAL_CLASS =INFO.INPUT_FILE_NAME_ONLY+"Globals";
    private ArrayList<String> ResolvedGlobal;
    private ArrayList<String> GLOBAL_IDENTIFIERS = new ArrayList<String>();
    
    private boolean GLOBAL_RESOLVABLE_STRATUS = false;
    private boolean RESOLVABLE = false;
    private String[] SlicedFiles;
    
    public CLASS_GlobalResolve(String []SliFi)
    {
        this.SlicedFiles = SliFi;
    }
    
    public ArrayList<String> doResolveGlobals(){        
        String []tempList = this.SlicedFiles;
        int  i = 0;
        ResolvedGlobal = new ArrayList<>();
        for(; i < tempList.length;i++)
        {   
            SourcePreProcessor spp1 = new SourcePreProcessor(tempList[i]);
            
            spp1.tokenizer();
            ArrayList<String> tokList = spp1.getSrcList();            
            String tok = new String();
            if(tokList.size()>0)
            {    
                tok = tokList.get(0);            
            
                if(!tok.contains("class") && !tok.contains("struct") && !tok.contains("union") && !this.RESOLVABLE)
                {
                    this.RESOLVABLE = true;                
                }
            }
            if(this.RESOLVABLE && !spp1.getSrcList().get(0).contains("class") && !spp1.getSrcList().get(0).contains("struct")&& !spp1.getSrcList().get(0).contains("union"))
            {
                if(spp1.getSrcList().contains("main"))
                    continue;
                GLOBAL_IDENTIFIERS.add(spp1.getSrcList().get(2));
                Util.sopln("GLOBAL MEMBERS:    #######################      "+spp1.getSrcList().get(2));
                this.ResolvedGlobal.add("\npublic static ");
                this.ResolvedGlobal.addAll(spp1.getSrcList());
                Util.SaveFile(tempList[i],"",false);
            }
            
        }
        this.ResolvedGlobal.add("\n}\n");
        this.ResolvedGlobal.add(0,"class "+CLASS_GlobalResolve.GLOBAL_CLASS+"{\n");
        if(RESOLVABLE)
            this.GLOBAL_RESOLVABLE_STRATUS = true;
        return this.ResolvedGlobal;
    }
    
    public boolean getResolvedStatus()
    {
        if(!this.GLOBAL_RESOLVABLE_STRATUS)
            Util.log("Nothing to resolved as global.", false);
        return this.GLOBAL_RESOLVABLE_STRATUS;        
    }

    public ArrayList<String> replaceGlobals() {
        int i =0,k = 0;
        while(i<this.ResolvedGlobal.size() && k<this.GLOBAL_IDENTIFIERS.size())
        {
            if(this.GLOBAL_IDENTIFIERS.get(k).equals(this.ResolvedGlobal.get(i)))
            {
                this.ResolvedGlobal.set(i, CLASS_GlobalResolve.GLOBAL_CLASS+this.ResolvedGlobal.get(i));
                k++;
            }
            i++;
        }
        return this.ResolvedGlobal;
    }
    
    public static boolean isGlobal(ArrayList<String> tokList)
    {
        if(tokList.size()>0)
        {    
            String tok = tokList.get(0);            
            
            if(!tok.contains("class") && !tok.contains("struct") && !tok.contains("union"))
            {
                return false;                
            }
        }
        return false;
    }
  }
