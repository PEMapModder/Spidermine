package pemapmodder.spidermine.android;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.managers.ServerManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LauncherActivity extends Activity implements OnClickListener{

	public final static int NEW_SERVER_BUTTON=0x6d9753a0;
	public SpiderServer[] savedServers=ServerManager.getAll();
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout());
	}
	public LinearLayout layout(){
		LinearLayout ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		TextView title=new TextView(this);
		title.setText(R.string.app_name);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
		ll.addView(title);
		
		Button addServer=new Button(this);
		addServer.setText(R.string.add_new_server);
		addServer.setId(NEW_SERVER_BUTTON);
		addServer.setOnClickListener(this);
		
		LinearLayout serversList=new LinearLayout(this);
		serversList.setOrientation(LinearLayout.VERTICAL);
		savedServers=ServerManager.getAll();
		for(int i=0; i<savedServers.length; i++){
			TextView s=new TextView(this);
			s.setText(savedServers[i].toString());
			s.setId(0x80001000+i);
			s.setOnClickListener(this);
			serversList.addView(s);
		}
		ll.addView(serversList);
		return ll;
	}
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	@Override public void onClick(View v){
		if(v.getId()>=0x80001000&&v.getId()<0x80001000+savedServers.length){
			int id=v.getId()-0x80001000;
			startActivity(new Intent(this, ServerMainControlPanel.class)
					.putExtra(ServerMainControlPanel.INTENT_SERVER_PORT, savedServers[id].getIp().getPort())
					.putExtra(ServerMainControlPanel.INTENT_SERVER_IP, savedServers[id].getIp().getAddress().getHostAddress()));
		}
		if(v.getId() == NEW_SERVER_BUTTON){
			startActivity(new Intent(this, CreateNewServerActivity.class));
		}
	}

}
