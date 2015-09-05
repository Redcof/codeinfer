package codeinfer.Inferists;
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
		Util.log("Resolving outline to inline...",false);
		Util.log("CLASS LIST",false);
                        Util.show(className, "[", "]");
                for(int j=0;j<className.size();j++)
		{
			ArrayList<String> functionName=new ArrayList<>();//creating function list for each class
			for(int i=0;i<srcList.size()-2;)
			{
				if(srcList.get(i).equals(className.get(j))&&srcList.get(i+1).equals(":")&&srcList.get(i+2).equals(":"))
				{
					srcList.remove(i);
					srcList.remove(i);
					srcList.remove(i);
					int k=i;
					count = 0;
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
					Util.show(functionName,"[[","]]");			
					//("here Function start"+srcList.get(i));
					
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
                                                Util.sopln(br);
						srcList.remove(i);//delete {
						count++;
					}
					else if(i<srcList.size()&&body.equals("}"))
					{
						outline.add(srcList.get(i));//copy }
						Util.sopln(br);
						srcList.remove(i);//delete }
						count--;
					}
					else if(i<srcList.size())
					{
						outline.add(srcList.get(i));//cop
						Util.sopln(br);
						srcList.remove(i);//delete other code						
					}				
				}
				while(count!=0&&body.equals("}"));
                                        Util.show(outline,"[[[","]]]");			
					
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
                                        if(functionName.size()>0)
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