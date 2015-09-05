package codeinfer.Inferists;

import java.util.ArrayList;

public class DATA_CharArrayConvert {
private int startpos;
private int i;

public ArrayList<String> srcList = new ArrayList<String>();

public ArrayList<String> doCharArrayConvert(ArrayList<String> srcList)
{	
	String word = null; int k;
	for(i=0;i<srcList.size()-2;)
	{
		if(srcList.get(i).equals("char") && srcList.get(i+2).equals("[") )
		{
			srcList.remove(i);//char
			srcList.remove(i);//name
			srcList.remove(i);//[
			startpos = i;
			/*
			for( k=i;!(srcList.get(k).equals("{") ||srcList.get(k).equals("\"") || srcList.get(i).equals(";")) ;k++)
			{
				srcList.remove(i);
				
			}
			*/
			String del = new String();
			do
			{
				del = srcList.get(i);
				srcList.remove(i);			
			}
			while(!(del.equals("{") ||del.equals("\"") || del.equals(";")));
			/*	upto this 
			 	char a[]={
				char a[5]={
				char a[]="
				char a[5]="
				char a[5];
				
				will be removed
			
			*/
			if(del.equals("{"))
			{
				do
				{
					if(srcList.get(i).equals("'") && srcList.get(i+2).equals("'"))
					{                      		//'A','
						srcList.remove(i);		//A','
						word+=srcList.get(i);	//A','
						srcList.remove(i);		//','
						srcList.remove(i);		//,'
						srcList.remove(i);		//'				
						//'
					}
						
				}
				while(!srcList.get(i).equals("}"));
			}
			
			else if(del.equals("\""))
			{	//"ABC";
				srcList.remove(i);
				word += srcList.get(i);
				srcList.remove(i);//ABC";
				srcList.remove(i);//";
				srcList.remove(i);//;
				
			}
			else if (del.equals(";"))
			{
				word="";
			}
			
		}
		else i++;
	}
		srcList.add(i,word);
		return srcList;
	}
}
