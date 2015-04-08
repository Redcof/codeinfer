/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Codeinfer.Test;

import java.util.ArrayList;

/**
 *
 * @author Soumen
 */
public class ArrayListTest {
    public static void main(String args[]){
        ArrayList<String> arr = new ArrayList<String>();        
        arr.add("A");
        arr.add("B_int");
        arr.add("C_main");
        arr.add("D");
        arr.add("E");
        arr.add("F");
        arr.add("G");
        int i = 0;
         for( ; i < arr.size() ; i++)
        {
            System.out.println("Index: "+i+"  Data: "+arr.get(i));
            
        }
         System.out.println("*****************************************");
        for( i=0; i < arr.size() - 1 ; i++)
        {
            if(arr.get(i).equals("C_main"))
            {
                arr.remove(i);
                if(arr.get(i-1).equals("B_int"))
                {
                    arr.remove(i-1);
                }
                arr.add(i,"Hello");
            }
       }
         System.out.println("*****************************************");
        
         for( i=0; i < arr.size() ; i++)
        {
            System.out.println("Index: "+i+"  Data: "+arr.get(i));
            
        }
        
    }
}
