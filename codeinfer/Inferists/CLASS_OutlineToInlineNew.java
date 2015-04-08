package Codeinfer.Inferists;

import codeinfer.PreProcessing.Util;
import java.util.ArrayList;





public class CLASS_OutlineToInlineNew {
	private ArrayList<String> outline=new ArrayList<String>();
	private int count=0;
	
	public ArrayList<String> getOutline() {
		return outline;
	}

	public int getCount() {
		return count;
	}
	
	public ArrayList<String> doOutlineToInline(ArrayList<String> srcList,ArrayList<String> className){
		int count=0;
		
		//show.show(srcList);
		Util.show("---------------------------------------------------------");
		//show.show(className);
		for(int j=0;j<className.size();j++)
		{
			ArrayList<String> functionName=new ArrayList<String>();//creating function list for each class
			for(int i=0;i<srcList.size()-2;)
			{
				//show.show(i+srcList.get(i)+className.get(j));
				//show.show(srcList.get(i)+"---\t"+className.get(j));
				//System.out.println(srcList.get(i)==className.get(j)&&srcList.get(i+1).equals(":")&&srcList.get(i+2).equals(":"));
				if(srcList.get(i).equals(className.get(j))&&srcList.get(i+1).equals(":")&&srcList.get(i+2).equals(":"))
				{
					//show.show("deleteF"+srcList.get(i));
					srcList.remove(i);
					//show.show("delete"+srcList.get(i));
					srcList.remove(i);
					//show.show("delete"+srcList.get(i));
					srcList.remove(i);
					int k=i;
					count = 0;
					//show.show(srcList.get(k));
					String br = new String();
					do
					{
						
						br = srcList.get(k);
						if(br.equals("("))
						{
							count++;
							functionName.add(br);
							srcList.remove(k);
						
						}
						else if(br.equals(")"))
						{
							count--;
							functionName.add(br);
							srcList.remove(k);
						
						}
						else
						{
							functionName.add(br);
							srcList.remove(k);
						
						}
					}
					while(count!=0&&!br.equals(")"));						
								
					//show.show("here Function start"+srcList.get(i));
					
					//***************{copy whole OUTLINE function}**************
					//***************as well as remove from the list************
					count = 0;
					String body = new String();
					do
					{
					
					body = srcList.get(i);
					
					if(i<srcList.size()&&body.equals("{"))
					{
						outline.add(body);//copy {
						//show.show("delete"+srcList.get(i));
						srcList.remove(i);//delete {
						//i++;
						count++;
					}
					else if(i<srcList.size()&&body.equals("}"))
					{
						outline.add(srcList.get(i));//copy }
						//show.show("delete"+srcList.get(i));
						srcList.remove(i);//delete }
						//i++;
						count--;
					}
					else if(i<srcList.size()&&count!=0)
					{
						outline.add(srcList.get(i));//cop
						//show.show("delete"+srcList.get(i));
						srcList.remove(i);//delete other code
						//i++;
					}
				
				}
				while(count!=0&&body.equals("}"));
			}
			else 
				i++;
		}
			
			
			for(int i=0;i<srcList.size();)
			{
			int k = 0,funcLen = 0;
			count =0;
			if(i<srcList.size()&&k<functionName.size()&&functionName.get(k).equals(srcList.get(i)))
			{
				String br =new String();
				do
				{
					br = srcList.get(i);
					if(k<functionName.size()&&functionName.get(k).equals(br))
					{
						k++;
						i++;
						funcLen++;
						if(br.equals("(")) count++;
						else if(br.equals(")")) count--;
					}
					else
						break;
				}
				while(count!=0&&!br.equals(")"));
				if(count==0)
				{
					srcList.remove(j);
					k=0;
					for(int x1=0;x1<=funcLen;x1++)
					{
						functionName.remove(k);
					}
					String body = new String();
					k=0;
					do
					{
						body = srcList.get(i);
					
					if(i<srcList.size()&&body.equals("{"))
					{
						srcList.add(i,body);
						outline.remove(k);
						count++;
						i++;
					}
					else if(i<srcList.size()&&body.equals("}"))
					{
						srcList.add(i,body);
						outline.remove(k);
						count--;
						i++;
					}
					else if(i<srcList.size()&&count!=0)
					{
						srcList.add(i,body);
						outline.remove(k);
						i++;
					}
					
					}
					while(count!=0&&!body.equals("}"));
				}
				
			}
			else
				i++;
		}
		
	
		}
		return srcList;
	}
}