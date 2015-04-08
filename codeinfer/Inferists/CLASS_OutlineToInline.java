package Codeinfer.Inferists;

import java.util.ArrayList;

public class CLASS_OutlineToInline {
	public ArrayList<String> outline=new ArrayList<String>();
	private int count=0;
	
	public void doOutlineToInline(ArrayList<String> srcList,ArrayList<String> className){
		for(int j=0;j<className.size();j++)
		{
			ArrayList<String> functionName=new ArrayList<String>();
			ArrayList<String> outline=new ArrayList<String>();
			for(int i=0;i<srcList.size()-2;)
				{
				//check over here about for loop range(Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 17, Size: 2)	
				if((srcList.get(i)==className.get(i)&&srcList.get(i+1).equals(":")&&srcList.get(i+2).equals(":")))
					{
						
						int k=i+3;
						do{
							functionName.add(srcList.get(k));
							k++;
						}
						while(!srcList.get(k).equals(")"));						
					}
					while(!srcList.get(i).equals("{")){
						i++;
					}
					if(srcList.get(i).equals("{"))
					{
						outline.add(srcList.get(i));
						i++;
						count++;
					}
					else if(srcList.get(i).equals("}"))
					{
						outline.add(srcList.get(i));
						i++;
						count--;
					}
					else if(count!=0)
					{
						outline.add(srcList.get(i));
					}
				}
			int functionNameLength=functionName.size();
			int x=0;
			for(int i=0;i<srcList.size();i++)
			{
				if(functionName.get(0).equals(srcList.get(i)))
				{
					
					int z=i;
					x=0;
					do
					{						
						if(functionName.get(x).equals(srcList.get(z)))
						{
							x++;
							z++;
						}
						else
							break;
					}
					while(x<functionNameLength);					
				}					
			}
			if(x==functionNameLength-1)
			{
				for(int i=0;i<outline.size();i++)
				{
					srcList.add(i,outline.get(i));
				}
				
			}
			
		}
		
		}
	

}
