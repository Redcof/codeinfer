package Codeinfer.PreProcessing;

import java.util.ArrayList;

public class ClassNames {


public ArrayList<String> createClassName(ArrayList<String> srcList){
	ArrayList<String> className = new ArrayList<String>();	
	for(int i =0; i<srcList.size();i++)
		if(srcList.get(i).equals("class"))
			className.add(srcList.get(i+1));
	
	
	return className;
}

}
