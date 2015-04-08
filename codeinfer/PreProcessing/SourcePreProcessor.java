package Codeinfer.PreProcessing;
import Codeinfer.RegEx.Expression;
import codeinfer.PreProcessing.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Receives a CPP File path as String, and tokenize it into ArrayList using completeTokenizer().<br/>
 * Example:<br/>
 * #include &lt;iostream.h&gt;<br/>
 * int main()
 * {<br/>
 * &nbsp;&nbsp;&nbsp;cout<<"Wellcome to codeinfer";<br/>	
 * &nbsp;&nbsp;&nbsp;return 0;<br/>
 * }<br/>
 * Result:<br/><br/>
 * <b><u>#</u></b>
 * include
 * <b><u>&lt;</u></b>
 * iostream
 * <b><u>.</u></b>
 * h
 * <b><u>&gt;</u></b><br/>
 * int
 * <b><u> </u></b>
 * main
 * <b><u>(</u></b>
 * )
 * <b><u>{</u></b><br/>
 * &nbsp;&nbsp;&nbsp;
 * <b><u>cout</u></b>
 * &lt;
 * <b><u>&lt</u></b>
 * "Wellcome to codeinfer"
 * <b><u>;</u></b><br/>
 * &nbsp;&nbsp;&nbsp;
 * <b><u>return</u></b>
 * 0
 * <b><u>;</u></b><br/>
 * } 
 * 
 * @author soumen
 *
 */
public class SourcePreProcessor {
	 	/************************************
		 * 																					  *
		 * 		FIELDS 										  *
		 * 																					  *
		 ************************************/
		/**
		 * Contains CPP source file as ArrayList
		 */
		private ArrayList<String> srcList=new ArrayList();
		/**
		 * Contains CPP KEYWORDS as ArrayList
		 */
		public ArrayList<String> keyWordsList=new ArrayList();
		/**
		 * Contains all class names
		 */
		private ArrayList<String> classNamesList=new ArrayList();
		/**
		 * Contains all structure names
		 */
		private ArrayList<String> structureNamesList=new ArrayList();
		/**
		 * Contains all union names
		 */
		private ArrayList<String> unionNamesList=new ArrayList();
		/**
		 * Contains all enumerations names
		 */
		private ArrayList<String> enumNamesList=new ArrayList();
		/**
		 * Contains all CPP-Strings
		 */
		ArrayList<String> stringTokens = new ArrayList();
		/**
		 * Contains source CPP File Name 
		 */
	    private String FileName = new String();
	    /**
	     * Contains source file as SrtingBuffer object
	     */
	    private StringBuffer SourceCodeBuffer = new StringBuffer();
	    /**
	     * Contains CPP source file path
	     */
	    private String FilePath = new String();
	    /**
	     * Says whether CPP source contains main or not
	     */
	    private boolean containMain = false;
	    /**
	     * Says whether CPP source contains any class(es)
	     */
	    private boolean containClass = false;
	    /**
	     * Contains total numbers of classes
	     */
	    private int countClass = 0;
	    /**
	     * Says whether CPP source contains any Structure(s)
	     */
	    private boolean containStructure = false;
	    /**
	     * Contains total numbers of Structure
	     */
	    private int countStructure = 0;
	    /**
	     * Says whether CPP source contains any Union(s)
	     */
	    private boolean containUnion = false;
	    /**
	     * Contains total numbers of Unions
	     */
	    private int countUnion = 0;
	    /**
	     * Says whether CPP source contains any Enumeration(s)
	     */
	    private boolean containEnum = false;
	    /**
	     * Contains total numbers of Enumerations
	     */
	    private int countEnum = 0;
	    /*********************************************
	     * 																					  *
	     * 			GETTER/SETTER    							  *
	     * 																					  *
	     *************************************************/
            /**
             * 
             * @return 
             */
	     public ArrayList<String> getStructureNamesList() {
			return structureNamesList;
		}


		public ArrayList<String> getUnionNamesList() {
			return unionNamesList;
		}


		public ArrayList<String> getEnumNamesList() {
			return enumNamesList;
		}


		public String getFilePath() {
			return FilePath;
		}


		public boolean isContainMain() {
			return containMain;
		}


		public boolean isContainClass() {
			return containClass;
		}


		public int getCountClass() {
			return countClass;
		}


		public boolean isContainStructure() {
			return containStructure;
		}


		public int getCountStructure() {
			return countStructure;
		}


		public boolean isContainUnion() {
			return containUnion;
		}


		public int getCountUnion() {
			return countUnion;
		}


		public boolean isContainEnum() {
			return containEnum;
		}


		public int getCountEnum() {
			return countEnum;
		}


		public void setStructureNamesList(ArrayList<String> structureNamesList) {
			this.structureNamesList = structureNamesList;
		}


		public void setUnionNamesList(ArrayList<String> unionNamesList) {
			this.unionNamesList = unionNamesList;
		}


		public void setEnumNamesList(ArrayList<String> enumNamesList) {
			this.enumNamesList = enumNamesList;
		}


		public void setFileName(String fileName) {
			FileName = fileName;
		}


		public void setFilePath(String filePath) {
			FilePath = filePath;
		}


		public void setContainMain(boolean containMain) {
			this.containMain = containMain;
		}


		public void setContainClass(boolean containClass) {
			this.containClass = containClass;
		}


		public void setCountClass(int countClass) {
			this.countClass = countClass;
		}


		public void setContainStructure(boolean containStructure) {
			this.containStructure = containStructure;
		}


		public void setCountStructure(int countStructure) {
			this.countStructure = countStructure;
		}


		public void setContainUnion(boolean containUnion) {
			this.containUnion = containUnion;
		}


		public void setCountUnions(int countUnions) {
			this.countUnion = countUnions;
		}


		public void setContainEnum(boolean containEnum) {
			this.containEnum = containEnum;
		}


		public void setCountEnum(int countEnum) {
			this.countEnum = countEnum;
		}
	    
		public ArrayList<String> getSrcList() {
			return srcList;
		}

	/**
	 * 
	 * @param srcList
	 */
		protected void setSrcList(ArrayList<String> srcList) {
			this.srcList = srcList;
		}


		public ArrayList<String> getClassNamesList() {
			return classNamesList;
		}


		public void setClassNamesList(ArrayList<String> classNamesList) {
			this.classNamesList = classNamesList;
		}
		/*
		 * 
		 * 	CONSTRUCTORS
		 * 
		 * 
		 */
		public SourcePreProcessor() {
			
		}
                /**
                 * 
                 * @param filePath the secondary storage file path    * 
                 *
                 */
                public SourcePreProcessor(String filePath){
                    this.FilePath = filePath;
                    this.keyWordsList = KeyWords.getKeyWordsList();
                    this.SourceCodeBuffer.append(this.fileToString());                    
                    this.tokenizer();
                    //this.createListOf_CPP_Class_Structure_Union_Enum();
                }
     
    /**
     * 
     *						MEMBER METHODS 
     * 
     */
    /**


	 * Convert file stream to String of default object.
	 * @return String
	 * @since 11-03-2015
	 * 
	 */
    public final String fileToString()
    {
    	String source_array = null;
    	try{
    		FileReader sourceFile = new FileReader(this.FilePath);
    		@SuppressWarnings("resource")
            BufferedReader in = new BufferedReader(sourceFile);
    	    String line;
    		do
    	    {
    	    	line = null;
    	    	line = in.readLine();
    	    	source_array += line+"\n";    	    	
    	    }while(line != null);
            }
            catch(NoSuchFileException e)
            {
                System.out.println("File Not Found @"+this.FilePath);
            }
	        catch(IOException e)
	        {
	            System.out.println("IO Error @"+this.FilePath);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e+"\nUnknown Error when to read @"+this.FilePath);
	            
	        }
		
            
			return source_array;
        }
    

    public String getFileName() {
        return FileName = this.FilePath.substring(this.FilePath.lastIndexOf('\\')+1, this.FilePath.lastIndexOf('.'));
    }

    public boolean IsContainMain() {
        return containMain;
    }
    
    
    /**
     * To tokenize the source code and  update the this.srcList 
     * 
     * 
     * 
     */
    public final  void tokenizer()
    {
    	int i = 0;
    	ArrayList<String> src = new ArrayList();
    	StringBuffer sourceBuffer = new StringBuffer();
        
    	sourceBuffer = stringCapture_Replace();//call string parser
        
        this.SourceCodeBuffer = sourceBuffer; //update source code buffer with parsed(CPP String) code
    	Util.SaveFile("Output/TOKENIZE_SourceCodeBuffer.java", sourceBuffer);
        String a[] = sourceBuffer.toString().split(Expression.CODEINFER_STRING_SPLIT);
		
		for(i = 0;i<stringTokens.size();i++)
		{
                    CharacterSeparator charSeptr = new CharacterSeparator(a[i]);//Initialize with `non CPP String` text
                    Util.SaveFile("Output/"+i+"TOKENIZE.java", new StringBuffer(a[i]));
                    src.addAll(charSeptr.getArrayList());//Store the tokens from Character Separator
                    src.add(stringTokens.get(i));//Store `CPP Strings` after above
                    Util.SaveFile("Output/"+i+i+"TOKENIZE.java", src);
                    
		}
		while(i<a.length){
                    CharacterSeparator charSeptr = new CharacterSeparator(a[i]);//Initialize with `non CPP String` text
                    src.addAll(charSeptr.getArrayList());//Store the tokens
                    i++;
		}		
		this.setSrcList(src);
                Util.SaveFile("Output/TOKENIZE.java", this.srcList);
		
    }
    
    /**
     * Captures Strings and replace with <span style='color:3AA381;font-weight:bold'>
     * Expression.CODEINFER_REPLACE_CPP_STRING</span><span style='color:3085AD;font-weight:bold'> <u>(031504050914060518)</u> </span><br>
     * in source buffer.<br>
     * <span style='color:3AA381;font-weight:bold'>031504050914060518</span> this will 
     * <span style='color:3AA381;font-weight:bold'>increase</span> for each string from start to EOF
     * Example:<br/><br/>
	 * #include < iostream.h ><br/>
	 * int main()<br/>
	 * {<br/>
	 * 		cout<<"Wellcome to codeinfer";	<br/>
	 * 		cout<<"Thankyou";
	 * 		return 0; <br/>
	 * }<br/><br/><br/>
	 * Result:<br/><br/>
	 * #include< iostream.h ><br/>
	 * int main()<br/>
	 * {
	 * cout<<0315040509140605180;<br/>
	 * cout<<0315040509140605181;<br/>
	 * return 0;<br/>
	 * }
	 *
     * 
     * 
     */
    public final StringBuffer stringCapture_Replace()
    {
    	
	    StringBuffer sourceBuffer = new StringBuffer();
	    sourceBuffer.append(this.fileToString());
		//Initialize the buffer with `FileToString()`
	    
	    String replacePatternRX = Expression.CODEINFER_REPLACE_CPP_STRING;
	    String patternRX = Expression.CPP_STRING;
            
		this.stringTokens = this.captureThose(patternRX, sourceBuffer);//Contains all `C++ Strings`		
		
		sourceBuffer = this.captureAndDynamicReplaceString(sourceBuffer, patternRX, replacePatternRX);		
		//Contains all `non CPP String` tokens excluding duly Expression.CODEINFER_STRING_SPLIT		
		return sourceBuffer;
    }
    
    
    /**
     * Create class,structure,union,enumeration list if any in CPP program
     * <br/>
     * But Union and Enumeration is not executed
     */
    public final void createListOf_CPP_Class_Structure_Union_Enum()
    {
    	ArrayList<String> tempSrcList = this.getSrcList();
    	ArrayList<String> tempClassList = new ArrayList();
    	ArrayList<String> tempStructureList = new ArrayList();
    	ArrayList<String> tempUnionList = new ArrayList();
    	ArrayList<String> tempEnumList = new ArrayList();
    	int i;
    	for(i=0;i < tempSrcList.size()-2;i++)
    	{
    		System.out.println(tempSrcList.get(i));
    		if(tempSrcList.get(i).equals("class"))
    		{
    			System.out.println(tempSrcList.get(i));
    			tempClassList.add(tempSrcList.get(i+2));
    			this.containClass = true;
    			++this.countClass;
    		}
    		else if(tempSrcList.get(i).equals("struct"))
    		{
    			System.out.println(tempSrcList.get(i));
    			tempStructureList.add(tempSrcList.get(i+2));
    			this.containStructure = true;
    			++this.countStructure;
    		}
    		if(tempSrcList.get(i).equals("union"))
    		{
    			System.out.println(tempSrcList.get(i));
    			tempUnionList.add(tempSrcList.get(i+2));
    			this.containUnion = true;
    			++this.countUnion;
    		}
    		if(tempSrcList.get(i).equals("enum"))
    		{
    			System.out.println(tempSrcList.get(i));
    			tempEnumList.add(tempSrcList.get(i+2));
    			this.containEnum = true;
    			++this.countEnum;
    		}
    	}
    	this.setClassNamesList(tempClassList);
    	this.setStructureNamesList(tempStructureList);
    	this.setEnumNamesList(tempEnumList);
    	this.setUnionNamesList(tempUnionList);
    	
    }

    /**
     *  To Capture a particular <span style='{color:#11A187;text-decoration:underline}'>patternRX</span> into a 
     *  <span style='{color:#11A187}'>StringBuffer</span> using <span style='{color:#11A187}'>regular expression</span>
     *  @param sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     *  @param REGEX <span style='{color:#11A187}'>String</span>
     *  @return TokenList <span style='{color:#11A187}'>ArrayList&lt;String&gt;</span>
     */
    public final ArrayList<String> captureThose(String REGEX,StringBuffer sourceBuffer)
    {
    	String pattern = REGEX;
    	ArrayList<String> stringTokens = new ArrayList();
		
	    Matcher matcher = Pattern.compile(pattern).matcher(sourceBuffer);
	    
	    StringBuffer srcBuffer = new StringBuffer();
		
		while(matcher.find())
		{
			stringTokens.add(matcher.group(0));//ADDING STRINGS INTO THIS LIST			
		}
		matcher.appendTail(srcBuffer);
    	return stringTokens;
    }
    
    /**
     *  To capture and replace with <span style='{color:#11A187;text-decoration:underline}'> expression</span> patternRX into a 
  <span style='{color:#11A187}'>source buffer</span> using <span style='{color:#11A187}'>Regular Expression</span>
     *  @param sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     *  @param REGEX <span style='{color:#11A187}'>String</span>
     *  @param replaceString <span style='{color:#11A187}'>String</span>
     *  @return sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     */

    public StringBuffer captureAndReplace_REGEX(StringBuffer sourceBuffer,String REGEX,String replaceString)
    {
    	String replacePattern = replaceString;
	    String pattern = REGEX;
		
	    Matcher matcher = Pattern.compile(pattern).matcher(sourceBuffer);
	    StringBuffer replaceBuffer = new StringBuffer();
		while(matcher.find())
		{
			replaceBuffer.replace(0, replaceBuffer.length(), replacePattern);
			matcher.appendReplacement(sourceBuffer, replaceBuffer.toString());
		}
		matcher.appendTail(sourceBuffer);
		return sourceBuffer;
    	
    }
   
   

    /**
     * To capture and replace with <span style='{color:#11A187;text-decoration:underline}'> expression</span> patternRX into a 
  <span style='{color:#11A187}'>source buffer</span> using <span style='{color:#11A187}'>Regular Expression</span>
     * @param sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     * @param REGEX <span style='{color:#11A187}'>String</span>
     * @param replaceString <span style='{color:#11A187}'>String</span>
     * @return sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     */
    public StringBuffer captureAndReplace_STRING(StringBuffer sourceBuffer,String REGEX,String replaceString)
    {
    	String replacePattern = replaceString;
	    String pattern = REGEX;
		
	    Matcher matcher = Pattern.compile(pattern).matcher(sourceBuffer);
	    StringBuffer replaceBuffer = new StringBuffer();
		while(matcher.find())
		{
			replaceBuffer.replace(0, replaceBuffer.length(), replacePattern);
			matcher.appendReplacement(sourceBuffer, replaceBuffer.toString());
		}
		matcher.appendTail(sourceBuffer);                
		return sourceBuffer;
    	
    }
    

    /**
     * To capture and replace with <span style='{color:#11A187;text-decoration:underline}'>dynamic expression</span> patternRX into a 
  <span style='{color:#11A187}'>source buffer</span> using <span style='{color:#11A187}'>Regular Expression</span>
     * @param sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     * @param REGEX <span style='{color:#11A187}'>String</span>
     * @param replaceString <span style='{color:#11A187}'>String</span>
     * @return sourceBuffer <span style='{color:#11A187}'>StringBuffer</span>
     */
    public StringBuffer captureAndDynamicReplaceString(StringBuffer sourceBuffer,String REGEX,String replaceString)
    {
    	 codeinfer.PreProcessing.Util.SaveFile("Output/BEFORE Parsed_String.cpp", sourceBuffer);
        String replacePattern = replaceString;
	String pattern = REGEX;
	Matcher matcher = Pattern.compile(pattern).matcher(sourceBuffer);
	StringBuffer replaceBuffer = new StringBuffer();
        StringBuffer temp = new StringBuffer();
	int i = 0;
        while(matcher.find())
	{
            replaceBuffer.replace(0, replaceBuffer.length(), replacePattern+i);
            matcher.appendReplacement(temp , replaceBuffer.toString());
            i++;
        }
	matcher.appendTail(temp);
         codeinfer.PreProcessing.Util.SaveFile("Output/AFTER Parsed_String.cpp", temp);
        return codeinfer.PreProcessing.Util.nullTrimBuffer(temp);
    }

    public StringBuffer arrayListToStringBuffer(ArrayList<String> src)
    {
        int i = 0;
        StringBuffer temp = new StringBuffer();
        for(;i<src.size();i++)
        {
            temp.append(src.get(i));
        }
        return temp;
    }
    /**
     * Tokenize the function arguments
     * @param args
     * @return 
     */
        public String[] argumentTokenizer(String args)
        {
            codeinfer.PreProcessing.Util.message("*********************************"
                    + "  argumentTokenizer start ***************************************");
		
            int i = 0;
            
            boolean invalidComma = true;
            int braceCounter = 0;
            StringBuffer arg = new StringBuffer();
            ArrayList<String> toksArgs = new ArrayList();
            codeinfer.PreProcessing.Util.message("ARGS: "+args);
            for(; i < args.length();i++)
            {
                System.out.println("READ: "+args.charAt(i));
                if(args.charAt(i) == '(' || args.charAt(i) == ')')
                {   
                    
                    if(braceCounter == 0)
                        braceCounter = 1;
                    else if(args.charAt(i) == '(')
                        braceCounter ++;
                    else if(args.charAt(i) == ')')
                        braceCounter --;
                }
                else if(args.charAt(i) == ' '||args.charAt(i) == '\t')
                {
                    
                }
                else if(args.charAt(i) == ',' && braceCounter == 0)
                {
                    toksArgs.add(arg.toString());                    
                    System.out.println("\tCOMMA (,) SKIPPED");
                    System.out.println("\tADDED NEW: "+arg);
                    invalidComma = false;
                    arg = new StringBuffer();
                    System.out.println("\tBUFFER CLEARED "+arg);
                }
                
                
                if(invalidComma)
                {
                    arg.append(args.charAt(i));
                    System.out.println("\tGoing To Add: "+arg);                    
                }
                else{
                    invalidComma = true;
                }
                
            }
            codeinfer.PreProcessing.Util.message("Last: "+arg);
            toksArgs.add(arg.toString());
            String argTok[] = new String[toksArgs.size()];
            for(i = 0 ; i < toksArgs.size() ; i++)
            {
                argTok[i] = toksArgs.get(i);
                System.out.println("{"+argTok[i]+"}");
            }
            codeinfer.PreProcessing.Util.message("*********************************"
                    + "  argumentTokenizer end ***************************************");
	
            return argTok;
        }
        
        
        public void eatWhiteSpace()
        {
            
        }
	public ArrayList<String> getStringTokens() {
		return stringTokens;
	}


	public StringBuffer getSourceCodeBuffer() {
		return SourceCodeBuffer;
	}


	public void setStringTokens(ArrayList<String> stringTokens) {
		this.stringTokens = stringTokens;
	}


	public void setSourceCodeBuffer(StringBuffer sourceCodeBuffer) {
		SourceCodeBuffer = sourceCodeBuffer;
	}


	public void setCountUnion(int countUnion) {
		this.countUnion = countUnion;
	}

}
