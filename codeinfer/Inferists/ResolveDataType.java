/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinfer.Inferists;

import java.util.ArrayList;

/**
 *
 * @author codubu
 */
public class ResolveDataType {
    private ArrayList<String> srcList;

    public ResolveDataType(ArrayList<String> srcList) {
        this.srcList = srcList;
    }

    public ArrayList<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(ArrayList<String> srcList) {
        this.srcList = srcList;
    }
    
   public ArrayList<String> doResolveDataType()
   {
       int i = 0;
       for(; i < this.srcList.size();i++)
       {
           if(this.srcList.get(i).equals(srcList))
               ;
       }
       
       
       return this.srcList;
   }
}
