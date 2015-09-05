package codeinfer.PreProcessing;

import codeinfer.Inferists.Message;
import codeinfer.LoaderRunner.Run;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import codeinfer.RegEx.Expression;
import codeinfer.Super.INFO;
import java.io.File;

public class CharacterSeparator {
	private String incommingStringArray = new String();
	private ArrayList<String> tokenizedListedCode;
	private int LENGTH_OF_INDEXED_STRING_ARRAY;

	public ArrayList<String> getArrayList() {
		return this.tokenizedListedCode;
	}

	public int getLENGTH_OF_INDEXED_STRING_ARRAY() {
		return this.LENGTH_OF_INDEXED_STRING_ARRAY;
	}

	public CharacterSeparator(String indexedString)
	{
		this.incommingStringArray = indexedString;
                this.tokenizedListedCode = new ArrayList<String>();
                this.LENGTH_OF_INDEXED_STRING_ARRAY = 0;		
	}

        /**
         * @deprecated 
         * @return 
         */
	public ArrayList<String> separators(){
		int i = 0;
                while(i < this.incommingStringArray.length()){
		boolean SMALL_LETTER = this.incommingStringArray .charAt(i) >= 'a' && this.incommingStringArray .charAt(i) <= 'z';
		boolean CAPITAL_LETTER = this.incommingStringArray .charAt(i) >= 'A' && this.incommingStringArray .charAt(i) <= 'Z';
		boolean NUMBER = this.incommingStringArray .charAt(i) >= '0' && this.incommingStringArray .charAt(i) <= '9';
		boolean UNDERSCORE = this.incommingStringArray .charAt(i) == '_';
		boolean EXCEPT_LETTER_OR_NUMBER = !(SMALL_LETTER || CAPITAL_LETTER || NUMBER);
                if(i < this.incommingStringArray.length() && EXCEPT_LETTER_OR_NUMBER){
				String temp = new String();
				temp += this.incommingStringArray .charAt(i);
				this.tokenizedListedCode.add(temp);
				i++;
			}
			else{
				String temp2 = new String();
				do
				{
                                    temp2 += this.incommingStringArray .charAt(i);
                                    i++;
				}
				while( i < this.incommingStringArray.length() && ( SMALL_LETTER || CAPITAL_LETTER || NUMBER || UNDERSCORE ));
				this.tokenizedListedCode.add(temp2);
			}
			}
			
                        this.LENGTH_OF_INDEXED_STRING_ARRAY=i;
                        return this.tokenizedListedCode;
	}
        
        public ArrayList<String> separator(){
		int i = 0;
               StringBuffer sbf = new StringBuffer(Util.eatWhiteSpace(this.incommingStringArray.toString()));
                //Used for dynamic string replacement,Pattern is:  `Expression.CODEINFER_REPLACE_STRING`
                
                //Message.show(sbf.toString(),new File(Run.SELECTED_FILE_PART_NAME).getName());
		String identifiers = Expression.IDENTIFIER;
		String otherChar = "[\\t\\r\\n\\f\\cK\\cH\\x20\\x21\\x23\\x25\\x26\\x27\\x28\\x29"+
                            "\\x2A\\x2B\\x2C\\x2D\\x2E\\x2F\\x3A\\x3B\\x3C\\x3D\\x3E\\x3F\\x40\\x5B\\x5C\\x5D"+
                            "\\x5E\\x60\\x7B\\x7C\\x7D\\x7E]";
		
		String pattern = new String(identifiers+"|"+otherChar+"|"+"[0-9]*");
		    
		Matcher matcher = Pattern.compile(pattern).matcher(sbf);
                /*REPLACE ALL STRINGS WITH  `i+Expression.CODEINFER_REPLACE_STRING` pattern*/
		i = 0;
		while(matcher.find())
		{
                   String tok = matcher.group(0);
                   if(tok.equals("\n"))
                       Util.sopln("[\\n]");
                   else if(tok.equals(" "))
                       Util.sopln("[\\S]");
                   else if(tok.equals("\t"))
                      Util.sopln("[\\t]");
                   else
                    Util.sopln("["+tok+"]");
                   if(!tok.equals("null"))
                   {
                       this.tokenizedListedCode.add(tok);                    
                    i++;
                   }
		}
                this.LENGTH_OF_INDEXED_STRING_ARRAY=i;
                if(this.tokenizedListedCode.get(0).indexOf("null") == 0)
                {
                    SourcePreProcessor spp = new SourcePreProcessor();
                    String tok = spp.captureAndReplace_REGEX(new StringBuffer(this.tokenizedListedCode.get(0)),"null","").toString();
                    this.tokenizedListedCode.set(0, tok);
                }
                return this.tokenizedListedCode;
	}
}
