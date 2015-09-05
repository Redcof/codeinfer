package codeinfer.PreProcessing;

import codeinfer.RegEx.Expression;
import java.util.ArrayList;

public class DataTypes {
	public static ArrayList<String> availableDataTypes = new ArrayList<>();
	/**
	 * Checks whether the <b style='color:red'>token</b> is data type <i><u> (PDT or UDT)</u></i> or not
	 * @param token as string.
	 * @param spp a SourcePreProcessor object
	 * @return true or false
	 */
	
        public static final boolean isDataType(String token,SourcePreProcessor spp)
	{
                availableDataTypes.clear();
		availableDataTypes.add("void");
		availableDataTypes.add("char");
		availableDataTypes.add("unsigned$#char");
		availableDataTypes.add("signed$#char");
		availableDataTypes.add("int");
		availableDataTypes.add("unsigned$#int");
		availableDataTypes.add("signed$#int");
		availableDataTypes.add("short$#int");
		availableDataTypes.add("unsigned$#short$#int");
		availableDataTypes.add("signed$#short$#int");
		availableDataTypes.add("long$#int");
		availableDataTypes.add("signed$#long$#int");
		availableDataTypes.add("unsigned$#long$#int");
		availableDataTypes.add("float");
		availableDataTypes.add("double");
		availableDataTypes.add("long$#double");
		availableDataTypes.add("wchar_t");/* NOT YET HANDELED*/
		availableDataTypes.add("signed");
		availableDataTypes.add("unsigned");
		availableDataTypes.add("register");/* NOT YET HANDELED*/
		availableDataTypes.add("short");
		availableDataTypes.add("long");
		availableDataTypes.add("static");/* NOT YET HANDELED*/
		availableDataTypes.add("extern");/* NOT YET HANDELED*/
                if(spp.isContainClass())
			availableDataTypes.addAll(spp.getClassNamesList());
		if(spp.isContainStructure())
			availableDataTypes.addAll(spp.getStructureNamesList());
		if(spp.isContainEnum())	
			availableDataTypes.addAll(spp.getEnumNamesList());
		if(spp.isContainUnion())
			availableDataTypes.addAll(spp.getUnionNamesList());		
		return availableDataTypes.contains(token);	
	}
        
    public static final boolean isPartOfPrimitiveType(String token){
            String types[] = {
                "char",
		"void",
		"unsigned",
		"signed",
		"int",
		"short",
		"long",
		"float",
		"double",
		"wchar_t",/* NOT YET HANDELED*/
		"register",/* NOT YET HANDELED*/
		"static",/* NOT YET HANDELED*/
		"extern"/* NOT YET HANDELED*/  
            };
            for(String temp:types)
            {
                if(temp.equals(token))
                    return true;
            }
            return false;
        }

    public static ArrayList<String> processPrimitiveType(ArrayList<String> srcList){
        int i = 0;
        Util.log("Processing primitive data types...",false);        
        for(; i<srcList.size()-4;i++)
        {
            String tok = srcList.get(i);              
            if(DataTypes.isPartOfPrimitiveType(srcList.get(i)) && 
               !srcList.get(i+1).equals(Expression.DELEIMITER_STR) &&
               DataTypes.isPartOfPrimitiveType(srcList.get(i+2)) &&
               !srcList.get(i+3).equals(Expression.DELEIMITER_STR) &&
               DataTypes.isPartOfPrimitiveType(srcList.get(i+4)))
            {
                String a = srcList.get(i);
                String b = srcList.get(i+2);
                String c = srcList.get(i+4);
                Util.log(srcList.get(i)+Expression.DELEIMITER_STR+srcList.get(i+2)+Expression.DELEIMITER_STR+srcList.get(i+4),false);
                srcList.add(i,srcList.get(i)+Expression.DELEIMITER_STR+srcList.get(i+2)+Expression.DELEIMITER_STR+srcList.get(i+4));
                
                
                srcList.set(i,DataTypes.getEquivalentJavaPrimitiveType(srcList.get(i)));
                
                
                srcList.remove(i+1);//type
                srcList.remove(i+1);//space
                srcList.remove(i+1);//type
                srcList.remove(i+1);//space
                srcList.remove(i+1);//type
            }
            else if(DataTypes.isPartOfPrimitiveType(srcList.get(i)) &&
                    !srcList.get(i+1).equals(Expression.DELEIMITER_STR) &&
                    DataTypes.isPartOfPrimitiveType(srcList.get(i+2)))
            {
                
                
                Util.log(srcList.get(i)+Expression.DELEIMITER_STR+srcList.get(i+2),false);                
                srcList.add(i,srcList.get(i)+Expression.DELEIMITER_STR +srcList.get(i+2));
                
                
                srcList.set(i,DataTypes.getEquivalentJavaPrimitiveType(srcList.get(i)));
                
                
                srcList.remove(i+1);//type
                srcList.remove(i+1);//space
                srcList.remove(i+1);//type
            }            
        }
        return srcList;
        
    }
    
    public static ArrayList<String> rewritePrimitiveDataTypes(ArrayList<String> srcList){
        ArrayList<String> tempSrc = srcList;
        int i = 0;
        String lastTok = new String();
        for(;i<tempSrc.size();)
        {
            String tok = tempSrc.get(i);
            Util.sopln("["+tok+"]");
            if((lastTok.equals(" ") ||
                    lastTok.equals(":") ||
                    lastTok.equals("\t") ||
                    lastTok.equals("{") ||
                    lastTok.equals("}")||
                    lastTok.equals("\n")||
                    lastTok.equals(";")) && DataTypes.isDataType(tok,new SourcePreProcessor()) && !DataTypes.functionStart(tempSrc,i))
            {
                Util.sopln("CONVERTING ["+tok+"]");
                String Type = tok;
                i += 1;
                boolean iterate = true;
                while(iterate)
                {
                    if(tok.equals(";"))
                    {
                        Util.show("LOOP WILL TERMINATE.");
                        i++;
                        iterate = false;
                    }
                    else if(tok.equals(","))
                    {
                        Util.show("CAUGHT "+tok+" >>>> "+Type);
                        tempSrc.set(i,";");
                        tempSrc.add(i+1,Type);
                        tempSrc.add(i+2," ");
                        i += 4;
                        tok = tempSrc.get(i);                        
                    }
                    else if(tok.equals("\t") || tok.equals(" "))
                    {
                        i++;
                    }
                    else
                    {
                        i++;
                        tok = tempSrc.get(i);
                    }                    
                }                
            }
            else
            {
                lastTok = tok;
                i++;
            }
        }
        return tempSrc;
    }
    
    public static final String [][] SCANNER_FOR_TYPE = {
                                {"int","=consoleReader.nextInt();\n","int"},
                                {"void","","void"},
                                {"const","","final"},
                                {"float","=consoleReader.nextFloat();\n","float"},
                                {"char","=consoleReader.next().charAt(0);\n","char"},
                                {"double","consoleReader.nextDouble();\n","double"},
                                {"unsigned$#char","=consoleReader.next=consoleReader.next().charAt(0);\n","char"},
                                {"signed$#char","=consoleReader.nextInt();\n","char"},
                                {"unsigned$#int","=consoleReader.nextLong();\n","long"},
                                {"unsigned$#float","=consoleReader.nextDouble();\n","double"},
                                {"unsigned$#double","=consoleReader.nextDouble();\n","double"},
                                {"signed$#int","=consoleReader.nextShort();\n","short"},
                                {"short$#int","=consoleReader.nextShort();\n","short"},
                                {"unsigned$#short$#int","=consoleReader.nextLong();\n","long"},
                                {"signed$#short$#int","=consoleReader.nextShort();\n","short"},
                                {"long$#int","=consoleReader.nextInt();\n","int"},
                                {"signed$#long$#int","=consoleReader.nextInt();\n","int"},
                                {"unsigned$#long$#int","=consoleReader.nextLong();\n","long"},
                                {"long$#double","=consoleReader.nextDouble();\n","double"},
                            };

    private static boolean functionStart(ArrayList<String> tempSrc, int index) {        
       return (index < tempSrc.size() - 3 && tempSrc.get(index+3).equals("("));            
    }    

    public String[][] getCUSTOMIZE_SCANNER_FOR_TYPE(String SCANNER_OBJECT) {
        String [][] CUSTOMIZE_SCANNER_FOR_TYPE = {
                                {"int","="+SCANNER_OBJECT+".nextInt();\n","int"},
                                {"void","","void"},
                                {"float","="+SCANNER_OBJECT+".nextFloat();\n","float"},
                                {"char","="+SCANNER_OBJECT+".next().charAt(0);\n","char"},
                                {"double","="+SCANNER_OBJECT+".nextDouble();\n","double"},
                                {"unsigned$#char","="+SCANNER_OBJECT+".next=consoleReader.next().charAt(0);\n","char"},
                                {"signed$#char","="+SCANNER_OBJECT+".nextInt();\n","char"},
                                {"unsigned$#int","="+SCANNER_OBJECT+".nextLong();\n","long"},
                                {"unsigned$#float","="+SCANNER_OBJECT+".nextDouble();\n","double"},
                                {"unsigned$#double","="+SCANNER_OBJECT+".nextDouble();\n","double"},
                                {"signed$#int","="+SCANNER_OBJECT+".nextShort();\n","short"},
                                {"short$#int","="+SCANNER_OBJECT+".nextShort();\n","short"},
                                {"unsigned$#short$#int","="+SCANNER_OBJECT+".nextLong();\n","long"},
                                {"signed$#short$#int","="+SCANNER_OBJECT+".nextShort();\n","short"},
                                {"long$#int","="+SCANNER_OBJECT+".nextInt();\n","int"},
                                {"signed$#long$#int","="+SCANNER_OBJECT+".nextInt();\n","int"},
                                {"unsigned$#long$#int","="+SCANNER_OBJECT+".nextLong();\n","long"},
                                {"long$#double","="+SCANNER_OBJECT+".nextDouble();\n","double"},
                            };
        return CUSTOMIZE_SCANNER_FOR_TYPE;
    }
    
    public static final String getEquivalentJavaPrimitiveType(String cppPrimitiveType){
        int i = 0 ;
        String temp = new String();
        Util.sopln(cppPrimitiveType+" is comming to convert.");
        for(;i<DataTypes.SCANNER_FOR_TYPE.length;i++)
        {
            Util.sopln(DataTypes.SCANNER_FOR_TYPE[i][0]);
            if(DataTypes.SCANNER_FOR_TYPE[i][0].equals(cppPrimitiveType))
            {
                temp = DataTypes.SCANNER_FOR_TYPE[i][2];
                Util.sopln("JAVA Eqv primitive: ["+temp+"] for ["+cppPrimitiveType+"]");
                break;
            }
        }
        return temp;
    }  
    
}
