package Codeinfer.PreProcessing;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Codeinfer.RegEx.Expression;

public class CharacterSeparator {
	private String incommingStringArray  = new String();
	private ArrayList<String> tokenizedIndexedString = new ArrayList<String>();
	private int LENGTH_OF_INDEXED_STRING_ARRAY;

	public ArrayList<String> getArrayList() {
		return this.tokenizedIndexedString;
	}

	public int getLENGTH_OF_INDEXED_STRING_ARRAY() {
		return this.LENGTH_OF_INDEXED_STRING_ARRAY;
	}

	public CharacterSeparator(String indexedString)
	{
		this.incommingStringArray = indexedString;
		this.separator();
	}

	public void separator(){
		int i = 0,lengthOfStringArray = this.incommingStringArray.length(),w = 0;
		//while(i < lengthOfStringArray){
//		boolean SMALL_LETTER = this.incommingStringArray .charAt(i) >= 'a' && this.incommingStringArray .charAt(i) <= 'z';
//		boolean CAPITAL_LETTER = this.incommingStringArray .charAt(i) >= 'A' && this.incommingStringArray .charAt(i) <= 'Z';
//		boolean NUMBER = this.incommingStringArray .charAt(i) >= '0' && this.incommingStringArray .charAt(i) <= '9';
//		boolean UNDERSCORE = this.incommingStringArray .charAt(i) == '_';
//		boolean EXCEPT_LETTER_OR_NUMBER = !(SMALL_LETTER || CAPITAL_LETTER || NUMBER);

//			if(i < lengthOfStringArray && EXCEPT_LETTER_OR_NUMBER){
//				String temp = new String();
//				temp += this.incommingStringArray .charAt(i);
//				this.tokenizedIndexedString.add(w++,temp);
//				i++;
//			}
//			else{
//				String temp2 = new String();
//				do
//				{
//				temp2 += this.incommingStringArray .charAt(i);
//				i++;
//				}
//				while( i < lengthOfStringArray && ( SMALL_LETTER || CAPITAL_LETTER || NUMBER || UNDERSCORE ));
//				this.tokenizedIndexedString.add(w++,temp2);
//			}
			//}
			StringBuffer sbf = new StringBuffer();//Used for dynamic string replacement,Pattern is:  `Expression.CODEINFER_REPLACE_STRING`
		    sbf.append(this.incommingStringArray);//Initialize the buffer with `FileToString()`
		    
		    String identifiers = Expression.IDENTIFIER;
		    String otherChar = "[0-9 \\t\\r\\n\\f\\cK\\cH\\x20\\x21\\x23\\x25\\x26\\x27\\x28\\x29\\x2A\\x2B\\x2C\\x2D\\x2E\\x2F\\x3A\\x3B\\x3C\\x3D\\x3E\\x3F\\x40\\x5B\\x5C\\x5D\\x5E\\x60\\x7B\\x7C\\x7D\\x7E]";
			String pattern = new String(identifiers+"|"+otherChar);
		    
			Matcher matcher = Pattern.compile(pattern).matcher(sbf);
			/*REPLACE ALL STRINGS WITH  `i+Expression.CODEINFER_REPLACE_STRING` pattern*/
			i = 0;
			while(matcher.find())
			{
				this.tokenizedIndexedString.add(matcher.group());
				i++;
			}
			
		
		this.LENGTH_OF_INDEXED_STRING_ARRAY=i;
	}
}
