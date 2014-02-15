package pemapmodder.spidermine.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyReader extends FileInputStream {

	public MyReader(File f) throws FileNotFoundException {
		super(f);
	}
	public String readLength(int length) throws IOException{
		String o="";
		for(int i=0;i<length;i++)
			o+=(char)read();
		return o;
	}
	public String readAll() throws NullPointerException, IOException, IndexOutOfBoundsException{
		String ret="";
		for(int c=read(); c!=-1; c=read())
			ret+=(char)c;
		return ret;
	}
	/**
	 * 
	 * @param file
	 * @return all contents
	 * @throws FileNotFoundException
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * @throws IOException
	 */
	public static String readAll(File f) throws FileNotFoundException, NullPointerException, IndexOutOfBoundsException, IOException{
		MyReader mr=new MyReader(f);
		String all=mr.readAll();
		mr.close();
		return all;
	}

}
