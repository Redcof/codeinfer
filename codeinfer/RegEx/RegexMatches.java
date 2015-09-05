package codeinfer.RegEx;
import codeinfer.Inferists.ShowIt;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
final class StringTokens
{
	public int index;
	public String str;
	public StringTokens link;
	public int length;
	
	public StringTokens()
	{
		this.index = -1;
		this.str  = new String();
		this.link = null;
		this.length = 0;
		
	}
	public StringTokens(StringTokens tokenNew)
	{
		this.index = tokenNew.index;
		this.str = tokenNew.str;		
	}
	public StringTokens(int indexNew,String strNew)
	{
		this.index = indexNew;
		this.str = strNew;
	}
	public void add(StringTokens tokenNew)
	{
		this.index = tokenNew.index;
		this.str = tokenNew.str;
	}
	public void add(int indexNew,String strNew)
	{
		this.index = indexNew;
		this.str = strNew;
	}
}
public class RegexMatches
{
	public static String replace(String str,int start,int end)
	{
		String temp = new String();
		if(start<str.length() && end < str.length())
		for(int i = 0 ;i<str.length(); i++)
		{
			if(!(i>=start && i<=end))
			{
				temp += str.charAt(i);
			}
		}
		
		return temp;		
	}
	public static void main( String args[] ) throws IOException,FileNotFoundException, InterruptedException{

		ShowIt s1 = new ShowIt();
		SourcePreProcessor spp = new SourcePreProcessor("E:\\Personal\\JAVA\\codeinfer\\src\\PreProcessing\\CppProg.cpp");
    	ArrayList<String> stringTokens = new ArrayList<String>();
		int i = 0;
	    String source_array = spp.fileToString();
	    StringBuffer sbf = new StringBuffer();
	    sbf.append(source_array);
		String replacePattern = Expression.CODEINFER_REPLACE_CPP_STRING;
	    String pattern = Expression.CPP_STRING;
		Matcher matcher = Pattern.compile(pattern).matcher(source_array);
		StringBuffer sourceBuffer = new StringBuffer(), replaceBuffer = new StringBuffer();
		i = 0;
		while(matcher.find())
		{
			replaceBuffer.replace(0, replaceBuffer.length(), i+replacePattern); 
			stringTokens.add(matcher.group(0));
			matcher.appendReplacement(sourceBuffer, replaceBuffer.toString());
			i++;
		}
		matcher.appendTail(sourceBuffer);		
		String a[] = sourceBuffer.toString().split(Expression.CODEINFER_STRING_SPLIT);
		ArrayList<String> src = new ArrayList<String>();
		for(i = 0;i<a.length && i<stringTokens.size();i++)
		{
				src.add(a[i]);
				src.add(stringTokens.get(i));		
		}
		while(i<a.length){
			src.add(a[i]);
			i++;
		}
		while(i<stringTokens.size()){
			stringTokens.get(i);
			i++;
		}
		
		Util.show(src,"","");
	}
}