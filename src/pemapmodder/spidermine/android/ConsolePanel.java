package pemapmodder.spidermine.android;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.managers.ServerManager;
import pemapmodder.utils.php.functions.P;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class ConsolePanel extends Activity{
	private SpiderServer server;
	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_console_panel);
		try{
			server=ServerManager.get(new InetSocketAddress(
					InetAddress.getByName(getIntent().getStringExtra(ServerMainControlPanel.INTENT_SERVER_IP)),
					getIntent().getIntExtra(ServerMainControlPanel.INTENT_SERVER_PORT, 19132)));
			
		}catch(UnknownHostException e){
			if(e instanceof UnknownHostException){
				String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"SpiderMine/error-dump-at-"
						+Long.toString(System.currentTimeMillis())+".txt";
				Toast.makeText(this, "An error occurred: Tried to use the following:\n"
						+getIntent().getStringExtra(ServerMainControlPanel.INTENT_SERVER_IP)
						+"\n as an IP address.\nFull error dump at "+path+"\nClosing activity.", Toast.LENGTH_LONG).show();
				Log.e(getClass().getName(), "Internal coding error: Tried to use invalid string as IP: "
						+getIntent().getStringExtra(ServerMainControlPanel.INTENT_SERVER_IP), e);
				try{
					P.file_put_contents(path, "Error dump as at "
							+DateFormat.format("dd-mm-yy hh:mm:ss", System.currentTimeMillis())
							+"\nException class: "+e.getClass().getName()+"\nException message: "+e.getLocalizedMessage());
				}catch(IOException e1){
					Toast.makeText(this, "Unable to write error dump to file "+path, Toast.LENGTH_LONG).show();
				}
				finish();
			}
		}
	}
	@Override public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.console_panel, menu);
		return true;
	}
}
