package codeinfer.LoaderRunner;

import codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;
public class Loader {
	public static SourcePreProcessor loadSource(String Path)
	{
                Util.log("LOADER START",false);            
                String FilePath = Path;
                
		SourcePreProcessor SourceFile;
		SourceFile = new SourcePreProcessor(FilePath);
                
                Util.log("LOADER END",false);                
                return  SourceFile;
	}
}
