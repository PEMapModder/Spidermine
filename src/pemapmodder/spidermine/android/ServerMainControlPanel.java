package pemapmodder.spidermine.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ServerMainControlPanel extends Activity{
	public final static String
			INTENT_SERVER_NAME="pemapmodder.spidermine.android.ServerMainControlPanel.intentData.server.name",
			INTENT_SERVER_PORT="pemapmodder.spidermine.android.ServerMainControlPanel.intentData.server.port",
			INTENT_SERVER_IP="pemapmodder.spidermine.android.ServerMainControlPanel.intentData.server.ip";
	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_main_control_panel);
	}
	@Override public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.server_main_control_panel, menu);
		return true;
	}
}
