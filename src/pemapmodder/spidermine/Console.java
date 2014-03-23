package pemapmodder.spidermine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import pemapmodder.spidermine.objects.CommandIssuer;
import android.util.Log;
import android.widget.Toast;

public class Console extends Object implements CommandIssuer{
	public ConsoleChangedListener[] listeners={};
	public final SpiderServer server;
	protected OutputStreamWriter out;
	public Console(SpiderServer server){
		this.server=server;
		File output=new File(server.dir, "console-log-1.log");
		int i=2;
		while(output.exists())
			output=new File(server.dir, "console-log-"+Integer.toString(i++)+".log");
		try{
			out=new OutputStreamWriter(new FileOutputStream(output));
			
		}catch(IOException e){
			Toast.makeText(server.app, "[ERROR] Unable to create console log file output stream.\nError type: "+e.getClass().getSimpleName()+"\nError message: "+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			Log.e(getClass().getName(), e.getMessage(), e);
		}
		addListener(new ConsoleChangedListener(){
			@Override public void writeLine(String line){
				try{
					out.append(line);
					out.write(System.getProperty("line.seperator"));
				}catch(IOException e){
					Toast.makeText(me().server.app, "[ERROR] Unable to write line "+line+" into console log.", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	protected Console me(){
		return this;
	}
	public void addListener(ConsoleChangedListener listener){
		listeners[listeners.length]=listener;
	}
	public void writeLine(String line){
		for(int i=0; i<listeners.length; i++)
			listeners[i].writeLine(line);
	}
	public void runCmd(String cmdLine){
		String[] splited=cmdLine.split(" ");
		String[] args={};
		for(int i=1; i<splited.length; i++){
			args[args.length]=splited[i];
		}
		server.manager.cmd.invokeCmd(server.manager.cmd.get(splited[0]), args, this);
	}
	public static interface ConsoleChangedListener{
		public void writeLine(String line);
	}
	public int getType(){
		return TYPE_CONSOLE;
	}
	public void out(String line){
		writeLine(line);
	}
}
