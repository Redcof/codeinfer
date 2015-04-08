package codeinfer.LoaderRunner;

import Codeinfer.PreProcessing.SourcePreProcessor;
import codeinfer.PreProcessing.Util;



public class Loader {
	public static SourcePreProcessor loadSource(String Path)
	{
                Util.message("************************* loadSource start ***************************");
                String FilePath = new String(Path);
		SourcePreProcessor SourceFile = null;
		SourceFile = new SourcePreProcessor(FilePath);
                Util.message("************************* "
                        + "loadSource end ***************************");
                
                return  SourceFile;
	}
}
