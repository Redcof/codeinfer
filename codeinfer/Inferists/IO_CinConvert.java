package Codeinfer.Inferists;

import java.util.ArrayList;

public class IO_CinConvert {
private  String in_t ;
private  String floa_t;
private  String doubl_e;
private  String strin_g;
private  String scannerTemplate;


private ArrayList<TypeVar> variables= new ArrayList();
class TypeVar{
    private String Type;
    private String Name;
    
    public TypeVar(String type,String name)
    {
        this.Name = name;
        this.Type = type;
    }
}
IO_CinConvert(){
	in_t = "=sc.nextInt();";
	floa_t = "=sc.nextFloat();";
	doubl_e = "=sc.nextDouble();";
	strin_g = "=sc.nextInt();";
        scannerTemplate = "java.util.Scanner sc = new java.util.Scanner(new java.io.InputStreamReader(System.in));";
        
	
}
public ArrayList<String> doCinConvert(ArrayList<String> srcList)
{
	
	for(int i = 0; i< srcList.size()-1;i++)
	{
		if(srcList.get(i).equals("int") || srcList.get(i).equals("float") || srcList.get(i).equals("double") || srcList.get(i).equals("char"))
		{
			
			variables.add(new TypeVar(srcList.get(i+1),srcList.get(i+1)));
		}
	}// got all the variables with their datatypes
	int count = 0;
	for(int i=0; i<srcList.size()-2;i++)
	{
		while(!srcList.get(i).equals(";"))
		{
			if(srcList.get(i).equals("cin") && srcList.get(i+1).equals(">") && srcList.get(i+2).equals(">") && count == 0 )
			{
				count ++;
				srcList.remove(i);//cin
                                srcList.remove(i);//>
                                srcList.remove(i);//>
                                
                                srcList.add(i,scannerTemplate);
                                
			}
			else if(srcList.get(i).equals("cin") && srcList.get(i+1).equals(">") && srcList.get(i+2).equals(">") && count > 1){
				count ++ ;
				srcList.remove(i);//cin
                                srcList.remove(i);//>
                                srcList.remove(i);//> 
			}
			
		}
	}
	return srcList;
}
}
