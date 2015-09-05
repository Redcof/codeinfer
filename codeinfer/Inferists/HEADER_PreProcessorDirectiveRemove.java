package codeinfer.Inferists;

import codeinfer.LoaderRunner.Run;
import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
import codeinfer.RegEx.Expression;
import java.util.ArrayList;
import codeinfer.Super.INFO;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class KeyValuePair{
	private String REGEX = new String();
	private String DEFINATION = new String();
        private String ARGUMENTS[] = null;

    KeyValuePair(String key,String value,String []args) {
        this.REGEX = key;
        this.DEFINATION = value;
        this.ARGUMENTS = args;
    }
    
    KeyValuePair(String key,String value) {
        this.REGEX = key;
        this.DEFINATION = value;
    }
    
	public String getREGEX() {
		return REGEX;
	}
        
	public String getDEFINATION() {
		return DEFINATION;
	}
        
	public void setREGEX(String kEY) {
		this.REGEX = kEY;
	}
        
	public void setDEFINATION(String vALUE) {
		this.DEFINATION = vALUE;
	}
        
        public String[] getARGUMENTS() {
            return ARGUMENTS;
        }

        public String getSeparateArguments(){
            int i = 0;
            String args = null;
            while(i < this.ARGUMENTS.length)
            {
                args += " "+this.ARGUMENTS[i++];
            }
            return args;
        }

        public void setARGUMENTS(String[] ARGUMENTS) {
            this.ARGUMENTS = ARGUMENTS;
        }

        @Override
        public String toString() {
            return "REGEX: "+this.getREGEX()+"\nDEFINATION: "+this.getDEFINATION()+"\nARGUMENTS: "+this.getSeparateArguments(); //To change body of generated methods, choose Tools | Templates.
        }
}

public class HEADER_PreProcessorDirectiveRemove {
        private ArrayList<String> KeyWordList = new ArrayList();
        private ArrayList<String> src = new ArrayList();        
        private ArrayList<KeyValuePair> HASH_DEFINE_CONSTANT_LIST = new ArrayList();
        private ArrayList<KeyValuePair> HASH_INCLUDE_LIST = new ArrayList();
        private ArrayList<KeyValuePair> HASH_DEFINE_FUNCTIONS_LIST = new ArrayList();
        private StringBuffer sourceBuffer = new StringBuffer();
		
        
        public boolean isKeyWord(String Token) {
                return KeyWordList.contains(Token);
        }
        public HEADER_PreProcessorDirectiveRemove(ArrayList<String> source){
        	this.src = source;
        }
        
        public HEADER_PreProcessorDirectiveRemove(StringBuffer source){
            this.sourceBuffer = source;
            
        }
       
        /**
         * @param spp SourcePreProcessor &nbsp;
         * @return ArrayList without any preprocessor directive<br/><br/>
         * Only #include is resolved
         */
//	public ArrayList<String> removePreProcessorDirective(SourcePreProcessor spp){
//		ArrayList<String> preProcessor = new ArrayList<String>();
//                this.KeyWordList = KeyWords.getKeyWordsList();
//		preProcessor = src;
//		int i = 0;
//		for(;i < preProcessor.size();)
//		{
//			if(preProcessor.get(i).equals("#"))
//			{
//				int j = i;
//				
//				String remove = new String(preProcessor.get(j));
//				while(j<preProcessor.size())
//				{
//					remove = preProcessor.get(j);					
//					if(remove.equals(">") || remove.equals("main") || DataType.isDataType(remove, spp))
//						//break loop if > or main or keyword found
//						break;
//					preProcessor.remove(j);
//				}
//				
//				if(remove.equals(">"))
//				{
//					preProcessor.remove(j);
//				}
//			}
//			else
//			{
//				i++;
//			}
//		}
//		return preProcessor;
//	}
	
	/**
	 * Resolve #define using Regular expression
	 * @param void
	 * @return void<br/>
	 * 
	 */
        
	public void CaptureRemovePreProcessors(){	
            
		//#include remove
                String includePattern = Expression.HASH_INCLUDE;
		Matcher includeMatcher = Pattern.compile(includePattern).matcher(this.sourceBuffer);
		while(includeMatcher.find()){
			this.HASH_INCLUDE_LIST.add(new KeyValuePair("include", includeMatcher.group(4)));//creating list
                        this.sourceBuffer = new StringBuffer(this.sourceBuffer.toString().replace(includeMatcher.group(0),""));//remove hash defines
		}
                //#define MAX 5 resolve and remove
                String constantPattern = Expression.HASH_DEFINE_CONSTANT;
		Matcher constantMatcher = Pattern.compile(constantPattern).matcher(this.sourceBuffer);
		
		while(constantMatcher.find()){
			this.HASH_DEFINE_CONSTANT_LIST.add(new KeyValuePair(
                                "\\b"+constantMatcher.group(1)+"\\b",//capture key
                                constantMatcher.group(2)) //capture value
                        );//creating list
                        this.sourceBuffer = new StringBuffer(this.sourceBuffer.toString().replace(constantMatcher.group(0),""));//remove hash defines
		}
		
                //#define ADD(a,b) ((a)+(b)) resolve and remove
		String funcPattern = Expression.HASH_DEFINE_FUNCTION;
		Matcher funcMatcher = Pattern.compile(funcPattern).matcher(this.sourceBuffer);
		int i =0;
                SourcePreProcessor spp = new SourcePreProcessor();
		while(funcMatcher.find()){
                    Util.sopln(funcMatcher.group(2));
                    this.HASH_DEFINE_FUNCTIONS_LIST.add(new KeyValuePair(
                                        "((\\b"+funcMatcher.group(2)+"\\b)\\((\\S*|\\s*|,)\\))",//capture key
                                        funcMatcher.group(4), //Capture DFN
                                        spp.argumentTokenizer(funcMatcher.group(3))//capture arguments
                        ));//creating list
                        this.sourceBuffer = new StringBuffer(this.sourceBuffer.toString().replace(funcMatcher.group(0),""));//remove hash defines
		}
	}
        /**
         * Capture all macrows from files and replace them using macrow defn 
         */
        public void resolvePreProcessors(){
            
            int i = 0;
            while(i < this.HASH_DEFINE_FUNCTIONS_LIST.size())
                {
                  
                    Matcher useFuncMatcher = Pattern.compile(this.HASH_DEFINE_FUNCTIONS_LIST.get(i).getREGEX()).matcher(this.sourceBuffer);//match all #functions
                    while(useFuncMatcher.find()){
                     
                       //CODE TODO
                       //Capture And Replace the DEFINE Usages
                       
                       
                       
                    }
                    
                    i++;
                }
        }

        public StringBuffer getSource() {
            return this.sourceBuffer;
        }

        public ArrayList<String> getKeyWordList() {
            return KeyWordList;
        }

        public ArrayList<String> getSrc() {
            return src;
        }

        /**
        *
        * @return
        */
        public ArrayList<KeyValuePair> getHASH_DEFINE_CONSTANT_LIST() {
            return HASH_DEFINE_CONSTANT_LIST;
        }

        public ArrayList<KeyValuePair> getHASH_INCLUDE_LIST() {
            return HASH_INCLUDE_LIST;
        }

        public ArrayList<KeyValuePair> getHASH_DEFINE_FUNCTIONS_LIST() {
            return HASH_DEFINE_FUNCTIONS_LIST;
        }

        public StringBuffer getSourceBuffer() {
        return sourceBuffer;
    }
        
        

}
