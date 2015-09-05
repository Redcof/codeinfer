/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeinfer.Inferists;

import codeinfer.LoaderRunner.Run;
import codeinfer.Super.INFO;
import codeinfer.PreProcessing.DataTypes;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import codeinfer.PreProcessing.Variable;
import codeinfer.RegEx.Expression;
import java.util.ArrayList;
public class IO_CinConvert {
    ArrayList<Variable> VARIABLE_LIST = new ArrayList<>();
    private static final String REQUIRED_IMPORTS = "import java.util.Scanner;\n"+
                                         "import java.io.InputStreamReader;\n";
    private static final String SCANNER_TEMPLATE = "Scanner cin=new Scanner(new InputStreamReader(System.in));\n";
    
    private String [][] SCANNER_SELECTER_FOR_TYPE = null;
    
    
    private ArrayList<String> srcList = new ArrayList();
    private SourcePreProcessor SPP = new SourcePreProcessor();
    
    public IO_CinConvert(SourcePreProcessor spp,ArrayList<String> src)
    {
        this.srcList = src;
        this.SPP = spp;
    }

    public ArrayList<String> doCinConvert() {
        DataTypes DT = new DataTypes();        
        this.SCANNER_SELECTER_FOR_TYPE = DT.getCUSTOMIZE_SCANNER_FOR_TYPE("cin");
        Util.log("Resolving cin ...",false);
        int i;
        
        boolean FirstCoutFound = false;
        ArrayList<String> temp = this.srcList;
        
        Util.log("Listing variable...",false);
        this.VARIABLE_LIST.clear();
        for( i = 0; i< srcList.size()-2;i++)
	{		
                if(DataTypes.isDataType(srcList.get(i),INFO.SOURCE_PREPROCESSOR))
		{
			
			this.VARIABLE_LIST.add(new Variable(srcList.get(i),srcList.get(i+2)));
                        Util.log(srcList.get(i)+"  "+srcList.get(i+2),false);
                        // Capture all the identifiers with their datatypes
		}
	}
        
        Util.log("All variables is listed.",false);
        
        for(i = 0 ;i < temp.size()-2;)
        {
            //Util.log(temp.get(i), false);
            
            if(temp.get(i).equals("cin")&&temp.get(i+1).equals(">")&&temp.get(i+2).equals(">"))
            {
                String t = temp.get(i)+" ["+i+"]";
                temp.remove(i);//cin
                Util.log("CIN remove", false);
                if(!FirstCoutFound)
                {
                    temp.add(i, SCANNER_TEMPLATE);
                    Util.log("First cin removed"+i,false);                
                    Util.log("Add scanner template", false);
                    if(!Run._IMPORTS[Run.CIN])
                    {
                        Util.SaveFile(INFO.IMPORTS_PATH,new StringBuffer(IO_CinConvert.REQUIRED_IMPORTS), true);
                        Util.log("Save `import` at "+INFO.IMPORTS_PATH, false);
                        Run._IMPORTS[Run.CIN] = true;
                    }
                    FirstCoutFound = true;
                    i++;
                }
                while(true)
                {                    
                    if(temp.get(i).equals(";"))
                    {
                        temp.remove(i);
                        break;
                    }
                    else if(temp.get(i).equals(">"))
                    {
                        temp.remove(i);//>
                    }
                    else{
                        String currentIdentifier = temp.get(i);//Variable
                        String currentType = this.getType(currentIdentifier);//Type
                        Util.log("Selected Type is ["+currentType+"]"+"Selected ID: ["+currentIdentifier+"]",false);
                        Util.log("Selecting Compatible scanner...",false);
                        
                        String scannerFix[] = this.selectCompatibleScannerFor(currentType);
                        temp.remove(i);
                        
                        temp.add(i, currentIdentifier+scannerFix[1]);//Add scanner and resolved strings
                        
                        Util.log("Resolved: "+currentIdentifier+scannerFix,false);
                        i++;
                    }
                
                }                
                Util.sopln(t);
            }
            else
            i++;           
        }
        srcList = this.convertCppTypeToJavaType();
        Util.log("Cin resolved.",false);
        return this.srcList;
    }

    private String getType(String currentIdentifier) {
        int j = 0;
        String tempType = new String();
        for(;j < this.VARIABLE_LIST.size();j++)
        {            
            if(this.VARIABLE_LIST.get(j).equalsName(currentIdentifier))//Checking in list
            {
                tempType = this.VARIABLE_LIST.get(j).returnType();//Deciding type
                break;
            }
        }
        return tempType;
    }

    private String[] selectCompatibleScannerFor(String type) {
        String []compatibleScanner = new String[2];
        for(String tempType[] : SCANNER_SELECTER_FOR_TYPE)
        {
            if(tempType[0].equals(type))
            {
                compatibleScanner[0] = tempType[2];//Compatible CPP to JAVA type
                compatibleScanner[1] = tempType[1];//Scanner template
                break;
            }
        }
        return compatibleScanner;
    }

private ArrayList<String> convertCppTypeToJavaType() {
        int i = 0;
        ArrayList<String> temp = this.srcList;
        for(;i<temp.size();i++)
        {
            String temptok = temp.get(i);
            if(temptok.contains(Expression.DELEIMITER_STR))
            {   
                String eqvJavaType = DataTypes.getEquivalentJavaPrimitiveType(temptok);
                temp.set(i, eqvJavaType);
            }
        }
        return temp;
    }
}