package pemapmodder.utils.php.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P {
	// string functions
	public static String implode(String divider, String[] string){
		String ret="";
		for(int i=0; i<string.length; i++){
			ret+=string[0];
			if(i+1!=string.length)
				ret+=divider;
		}
		return ret;
	}
	
	// file
	public static String file_get_contents(String path) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
		String ret="";
		String line;
		while((line=br.readLine())!=null)
			ret+=(line+System.getProperty("line.seperator"));
		br.close();
		return ret;
	}
	public static void file_put_contents(String path, String content) throws IOException{
		file_put_contents(path, content, false);
	}
	public static void file_put_contents(String path, String content, boolean isAppend) throws IOException{
		File file=new File(path);
		if(!isAppend){
			file.delete();
			file.createNewFile();
		}
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(file));
		osw.write(content);
		osw.close();
	}
}
