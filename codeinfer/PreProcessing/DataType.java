package Codeinfer.PreProcessing;

import java.util.ArrayList;

public class DataType {
	public static ArrayList<String> availableDataTypes = new ArrayList<String>();
	/**
	 * Checks whether the <b style='color:red'>token</b> is data type <i><u> (PDT or UDT)</u></i> or not
	 * @param token as string
	 * @param spp a SourcePreProcessor object
	 * @return true or false
	 */
	public static boolean isDataType(String token,SourcePreProcessor spp)
	{
		availableDataTypes.add("char");
		availableDataTypes.add("unsigned char");
		availableDataTypes.add("signed char");
		availableDataTypes.add("int");
		availableDataTypes.add("unsigned int");
		availableDataTypes.add("signed int");
		availableDataTypes.add("short int");
		availableDataTypes.add("unsigned short int");
		availableDataTypes.add("signed short int");
		availableDataTypes.add("long int");
		availableDataTypes.add("signed long int");
		availableDataTypes.add("unsigned long int");
		availableDataTypes.add("float");
		availableDataTypes.add("double");
		availableDataTypes.add("long double");
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
	
}
