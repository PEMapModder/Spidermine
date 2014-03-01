package quantumWorks.spidermine.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import quantumWorks.spidermine.SpiderServer;
import quantumWorks.spidermine.Utils;

public class ConsoleManager {
	public SpiderServer server;
	public BufferedReader in;
	public OutputStreamWriter out;
	public ConsoleManager(SpiderServer server){
		try{
			File out=new File(Utils.getServerDir(server), "console output.log");
			out.createNewFile();
			this.out=new OutputStreamWriter(new java.io.FileOutputStream(out));
			File in=new File(Utils.getServerDir(server), "console input.log");
			this.in=new BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(in)), 1);
		}catch (IOException e){
		}
		
	}
	@Override protected void finalize() throws Throwable{
		in.close();
		out.close();
		super.finalize();
	}
}
