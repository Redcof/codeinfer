/**
 * Contains final static Expression class which contains all essential reguler expressions 
 * @author soumen
 */
package codeinfer.RegEx;
/**
 * This class Contains all essential Regular Expressions.
 * @author soumen
 * 
 * @since 10-10-2014
 */
//public static final String  = new String("");
abstract public class Expression 
{
	public static final String OR = new String("|");
	public static final String PIPE = new String("\\|");

	public static final String IF = new String("?");
	public static final String QUESTION_MARK = new String("\\?");

	public static final String START_OF_A_LINE = new String("^");
	public static final String POWER = new String("\\^");

	public static final String END_OF_A_LINE = new String("$");
	public static final String DOLLER = new String("\\$");

	public static final String BRACE_OPEN = new String("\\(");
	public static final String BRACE_CLOSE = new String("\\)");

	public static final String CURLY_BRACE_OPEN = new String("\\{");
	public static final String CURLY_BRACE_CLOSE = new String("\\}");

	public static final String BOX_OPEN = new String("\\[");
	public static final String BOX_CLOSE = new String("\\]");

	public static final String AT_LEAST_ONE_OR_ANY_COMBINATION = new String("+");
	public static final String PLUS = new String("\\+");

	public static final String ANY_COMBINATION = new String("*");
	public static final String ANY_COMBINATION_OF(String Expression){ return "("+Expression+")*"; }
	public static final String ASTRIC = new String("\\*");

	public static final String ONE_PRINTABLE_CHARACTER = new String(".");
	public static final String DOT = new String("\\.");

	public static final String DOUBLE_INVERTED_COMMA = new String("\"");
	
	public static final String ESCAPE = new String("\\");

	public static final String ANY_CHARACTER_COMBINATION = new String(ANY_COMBINATION_OF(ONE_PRINTABLE_CHARACTER));

	public static final String ONE_SMALL_LETTER = new String(ONE_CHARACTER_OUTOF("a-z"));
	public static final String ONE_CAPITAL_LETTER = new String(ONE_CHARACTER_OUTOF("A-Z"));
	public static final String ONE_ALPHABATE = new String(ONE_CHARACTER_OUTOF("a-zA-Z"));

	public static final String ONE_DIGIT = new String("\\d");
	public static final String INTEGER = new String(ANY_COMBINATION_OF(ONE_DIGIT));
	public static final String FLOAT = new String(ANY_COMBINATION_OF(ONE_DIGIT)+DOT+ANY_COMBINATION_OF(ONE_DIGIT));
	public static final String NUMBER = INTEGER + OR + FLOAT;
	
	public static final String ONE_NON_DIGIT = new String("\\D");
	public static final String NON_DIGITS = new String(ANY_COMBINATION_OF(ONE_NON_DIGIT));
	
	
	public static final String IDENTIFIER = new String("([a-zA-Z_][a-zA-Z_0-9]*)");
	/**
	 * Capture CPP Strings<br/>,
	 * "WELLCOME TO CODINFER"
	 * 
	 */
	public static final String CPP_STRING = new String("\"(?:[^\\\\\"]|\\\\.|\\\\|\\n)*\"");
	public static final String ONE_WORD_CHARACTER = new String("\\w");
	public static final String WORD = new String("\\w+");

	public static final String ONE_NON_WORD_CHARACTER = new String("\\W");
	public static final String NON_WORD = new String("\\W+");
	/**
	 * 	Any Non printable character<br/>
	 * Tab, Line Feed, Space
	 * 
	 */
	public static final String ONE_NON_PRINTABLE_CHARACTER = new String("\\s|\\n");
	/**
	 * 
	 */
	public static final String CPP_COMMENT = new String("(/\\*(\\s*|\n*|.)*\\*/|//.*)");
	public static final String WHITE_SPACE_CHARACTERS = new String("(\\s|\\n)*");
	/**
	 * Capture any User Define Data Type<br/>
	 * e.g. Class, Union, Enumeration, Structure
	 * 
	 */
	public static final String CPP_UDT = new String("(class|union|struct|enum|typedef class|typedef union|typedef struct|typedef enum)(\\s*|\n*)*([a-z_][a-zA-Z_0-9]*|)((\\s*|\n*)*|(/\\*(\\s*|\n*|.)*\\*/|//.*))*(\\x7B(\\s*|\n*|.*|\\})*\\x7D(\\s*|\n*)*)(/\\*(\\s*|\n*|.)*\\*/|//.*)*(.*|\n*|\\s*)*(?!=;)");
//	public static final String CPP_UDT = new String("(class|union|struct|enum|typedef class|typedef union|typedef struct|typedef enum)"
//													+WHITE_SPACE_CHARACTERS
//													+IDENTIFIER
//													+WHITE_SPACE_CHARACTERS
//													+OR
//													+CPP_COMMENT
//													+WHITE_SPACE_CHARACTERS
//													+"\\x7B");
	
	public static final String ANY_PRINTABLE_CHARACTER_COMBINATION = new String(ANY_COMBINATION_OF(ONE_NON_PRINTABLE_CHARACTER));
	/**
	 * This is using to replace all CPP_STRINGS to 031504050914060518 (ASCII encoded CODEINFER)
	 * A = 01
	 * B = 02
	 * C = 03
	 * 	.
	 * 	.
	 * 	.
	 * Z = 26
	 */
	public static final String CODEINFER_REPLACE_CPP_STRING = new String("031504050914060518");
	/**
	 * This is using to split all source code,which resides in a Sting object,
	 * into multiple parts to store them into ArrayList
	 */
	public static final String CODEINFER_STRING_SPLIT = new String("031504050914060518([0-9]+)");
	/**
	 * Creates a custom Regular Expression Group
	 * @param Expression
	 * @return String
	 * 
	 * 
	 */
	public static final String ONE_CHARACTER_OUTOF(String Expression){
		return "["+Expression+"]";
	}
	/**
	 * #define Expression<br/>
	 * Capture 5 groups<br/>
	 * <br/>
	 * <span style='color: orangered'>#define[ +|\t+]+((\S+)\((\S*|\s*|,)\))[ +|\t+]+(\S+)</span>
	*<br/>
	*<h3>
	*Capture groups</h3><br/>
	*<span style='color:blue'><b>
	*GROUP 0:</b> #define MAX(a,b) ((a)>(b)?(a):(b))</span><br/>
	*<b>
	*GROUP 1:</b>	MAX(a,b)<br/>
	*<span style='color:blue'><b>
	*GROUP 2:</b> MAX</span><br/>
	*<b>
	*GROUP 3:</b>a,b<br/>
	*<span style='color:blue'><b>
	*GROUP 4:</b> ((a)>(b)?(a):(b))</span><br/>
	*
	**/
	public static final String HASH_DEFINE_FUNCTION = new String("#define[ +|\\t+]+((\\S+)\\((\\S*|\\s*|,)\\))[ +|\\t+]+(\\S+)");
	//public static final String HASH_DEFINE_FUNCTION = new String("#define[ +|\\t+]+(([a-zA-Z0-9_]+)(\\x28(.+)\\x29))( +|\\t+)+(.*)\\b");
        //Failed to capture last brace close [ ) ]
	//public static final String HASH_DEFINE_FUNCTION = new String("#define[ +|\\t+]+(([a-zA-Z0-9_]+)[ *|\\t*]*(\\((.*)\\)))[ +|\\t+]+(.*)\b");
	/**
	 * #define Expression<br/>
	 * Capture three groups<br/>
	 * <br/>
	 * <span style='{color:orangered}'>#define[ +|\t+]+([a-zA-Z0-9_]+)[ +|\t+]+(.+)\b</span><br/>
	 * <br/>Example: 
	 * #define MAX 20
	 * <h3>Captured Groups</h3>
	 * <span style='{color:orangered}'><b>GROUP 0: </b> #define MAX 20</span>
	 * <br/>
	 * GROUP 1:<span style='{color:green}'> MAX</span>
	 * <br/>
	 * GROUP 2:<span style='{color:blue}'> 20</span>
	 * 
	 */
	public static final String HASH_DEFINE_CONSTANT = new String("#define[ +|\\t+]+([a-zA-Z0-9_]+)[ +|\\t+]+(.+)\\b");
	/**
         * #include regular expression<br/>
         * #include( *|\t*)("|\<( *|\t*))(\S+\.h|)(( *|\t*)>|")
         */
        public static final String HASH_INCLUDE = new String("#include( *|\\t*)(\"|\\<( *|\\t*))(\\S+\\.h|)(( *|\\t*)>|\")");
	
        public static final String DELEIMITER_STR = "$#"; 
        public static final String DELEIMITER_REX = "\\$#"; 
        
	public static final String GROUP_OF(String Expression){return BRACE_OPEN+Expression+BRACE_CLOSE;}
	
	public static final String LENGTH(int Length){return "{"+Length+"}";}
	public static final String LENGTH_AT_LEAST(int Length){return "{"+Length+",}";}
	public static final String LENGTH_MIN_MAX(int minLength,int maxLength){return "{"+minLength+","+maxLength+"}";}
	
	public static final String VERY_RIGHT_PART_SHOULD_CONTAIN_THIS(String Expression){return "(?="+Expression+")";}
	//POSITIVE LOOKAHEAD
	public static final String VERY_RIGHT_PART_SHOULD_NOT_CONTAIN_THIS(String Expression){return "(?!"+Expression+")";}
	//NEGATIVE LOOAHEAD
	
	public static final String VERY_LEFT_PART_SHOULD_CONTAIN_THIS(String Expression){return "(?<="+Expression+")";}
	//POSITIVE LOOK BEHIND
	public static final String VERY_LEFT_PART_SHOULD_NOT_CONTAIN_THIS(String Expression){return "(?<!"+Expression+")";}
	//NEGATIVE LOOK BEHIND
	
	public static final String ANYONE_ONE_OUT_OF(String...Expressions)
	{
		String temp = new String(BRACE_OPEN);
		int i = 0;
		for(;i < Expressions.length -1 ;i++)
			temp += Expressions[i] + OR;
		temp += Expressions[i]+BRACE_CLOSE;
		return temp;
	}
}