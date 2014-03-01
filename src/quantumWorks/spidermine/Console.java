package quantumWorks.spidermine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import quantumWorks.spidermine.objects.CommandIssuer;

public class Console implements CommandIssuer{
	public final SpiderServer server;
	public BufferedReader in;
	public OutputStreamWriter out;
	public Console(SpiderServer server){
		this.server=server;
		try{
			File out=new File(Utils.getServerDir(server), "console output.log");
			out.createNewFile();
			this.out=new OutputStreamWriter(new java.io.FileOutputStream(out));
			int i=1;
			File in=new File(Utils.getServerDir(server), "console input" + Integer.toString(i) + ".log");
			while(in.isFile()){
				i++;
				in=new File(Utils.getServerDir(server), "console input" + Integer.toString(i) + ".log");
			}
			in.createNewFile();
			OutputStreamWriter writeIn=new OutputStreamWriter(new java.io.FileOutputStream(in));
			writeIn.write(new String("#Console input log started at " + "\n"));
			writeIn.close();
			this.in=new BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(in)), 1024);
		}catch (IOException e){
			// TODO Auto-generated catch block
		}
		
	}
	@Override public void out(String line){
		try {
			this.out.write(line);
			this.out.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	public class InputChecker implements Runnable{
		@Override public void run(){
			String line;
			try {
				while((line=in.readLine())!=null){
					if(line.charAt(0)!='#'){
						String[] pieces=line.split(" ", 2);
						triggerCmd(pieces[0], pieces[1].split(" "));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
	@Override public void triggerCmd(String cmd, String[] params){
		
	}
	@Override protected void finalize() throws Throwable{
		in.close();
		out.close();
		super.finalize();
	}
	@Override
	public int getType() {
		return CommandIssuer.TYPE_CONSOLE;
	}
}
