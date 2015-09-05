/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinfer.Inferists;

import codeinfer.PreProcessing.DataTypes;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import codeinfer.Super.INFO;
import java.util.ArrayList;

/**
 *
 * @author Shubham
 */
public class CLASS_ResolveClassStructUnion {
    private  ArrayList<String> srcList;
    private SourcePreProcessor SPP;

    public CLASS_ResolveClassStructUnion(SourcePreProcessor spp,ArrayList<String> src) {
        this.srcList = src;
        this.SPP = spp;
    }
    
    
    public ArrayList<String> doResolveClassStructUnion()
    {        
        ArrayList<String> src = this.getSrcList();
        String FirstWord=new String();
        FirstWord=src.get(0);
        
        boolean ClassFound=false;
        boolean PublicFound=false;
        boolean PrivateFound=false;
        boolean ProtectedFound=false;
        boolean DefaultControl=false;
        
        String temp=new String();
        String DefaultAccessSpecifier=new String();
        String AccessSpecifier=new String();
      
        int CountStruct=0;
        String TempAccessSpecifier[]=new String[100];
      
        int i = 0;
            for( ; i < src.size()-1 ; i++)
            {
                temp=src.get(i);
              Util.sopln(temp+i);
                 if(ClassFound && DefaultControl &&
                         !(PublicFound || PrivateFound || ProtectedFound) && (DataTypes.isDataType(temp, this.SPP) || temp.equals("class") || temp.equals("struct") || temp.equals("union")))
                    {
                      AccessSpecifier=DefaultAccessSpecifier;
                      
                    }             
                
                 if(temp.equals("class") && (src.get(i+1).equals("{") || src.get(i+2).equals("{") || src.get(i+3).equals("{")))
                    {
                        ClassFound=true;
                        DefaultControl=true;
                         CountStruct++;
                    TempAccessSpecifier[CountStruct]=AccessSpecifier;
                        DefaultAccessSpecifier="\nprivate ";
                    }
                 
                 if((temp.equals("struct") || temp.equals("union")) && (src.get(i+1).equals("{") || src.get(i+2).equals("{") || src.get(i+3).equals("{")))
                {
                    ClassFound=true;
                    DefaultControl=true;
                    CountStruct++;
                    TempAccessSpecifier[CountStruct]=AccessSpecifier;
                   DefaultAccessSpecifier="\npublic ";
                    src.remove(i);temp.equals("class");
                    src.add(i, "class");
                }   
               
                     if(FirstWord.equals("class") || FirstWord.equals("struct") || FirstWord.equals("union"))
                    {
                        FirstWord="nothing";
                        
                    }
                    
                     else if(ClassFound && !PublicFound && temp.equals("public") && src.get(i+1).equals(":"))
                    {
                        PublicFound=true;
                        PrivateFound=false;
                        ProtectedFound=false;
                        AccessSpecifier="\npublic ";
                        src.remove(i+1);
                        src.remove(i);
                        i--;
                        continue;
                                            
                    }
                                      
                                       
                    else if(ClassFound && !PrivateFound && temp.equals("private") && src.get(i+1).equals(":"))
                    {
                        PrivateFound=true;
                        ProtectedFound=false;
                        PublicFound=false;
                        AccessSpecifier="\nprivate ";
                        src.remove(i+1);
                        src.remove(i);
                        i--;
                        continue;
                    }
                                
                    else if(ClassFound && !ProtectedFound && temp.equals("protected") && src.get(i+1).equals(":"))
                    {
                        ProtectedFound=true;
                        PublicFound=false;
                        PrivateFound=false;
                        AccessSpecifier="\nprotected ";
                        src.remove(i+1);
                        src.remove(i);
                        i--;
                        continue;
                    }
                 
                    else if(ClassFound && (src.get(i-1).equals("\n") || src.get(i-1).equals(" ") || src.get(i-1).equals("\t") || src.get(i-1).equals(";")) && (DataTypes.isDataType(temp, this.SPP) || temp.equals("class") || temp.equals("struct") || temp.equals("union")))
                    {
                     
                        src.add(i, AccessSpecifier);
                        if(temp.equals("struct") || temp.equals("class") || temp.equals("union"))
                        {
                            PublicFound=false;
                            PrivateFound=false;
                            ProtectedFound=false;
                          
                        }
                     i++;
                     continue;
                        
                    }
                    
                
                    else if(temp.equals("}") && src.get(i+1).equals(";") && ClassFound)
                    {
                      //  System.out.println(TempAccessSpecifier[CountStruct]);
                        AccessSpecifier=TempAccessSpecifier[CountStruct];
                        CountStruct--;
                        DefaultControl=false;
                     
                        src.remove(i+1);
                        PublicFound=false;
                            PrivateFound=false;
                            ProtectedFound=false;
                    }
                    
            }
        return src;
}

    public ArrayList<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(ArrayList<String> srcList) {
        this.srcList = srcList;
    }
}