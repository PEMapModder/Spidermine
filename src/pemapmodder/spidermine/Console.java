package pemapmodder.spidermine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.widget.Toast;
import pemapmodder.spidermine.objects.CommandIssuer;


public class Console implements CommandIssuer{
	public final SpiderServer server;
	public BufferedReader in;
	public OutputStreamWriter out;
	public Console(SpiderServer server){
		this.server=server;
		
	}
	public String toString(){
		return "Console";
	}
	public void showLine(String line){
		
	}
	@Override protected void finalize(){
		try{
			in.close();
			out.close();
		}catch(IOException e){
			Toast.makeText(server.app, "[ERROR] Unable to close console I/O stream: \n"+e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	@Override public void out(String line){
		showLine(line);
	}
	@Override public int getType(){
		return TYPE_CONSOLE;
	}
}
