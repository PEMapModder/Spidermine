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
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout());
	}
	public LinearLayout layout(){
		LinearLayout ll=new LinearLayout(this);
		TextView title=new TextView(this);
		title.setText(R.string.app_name);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(title);
		
		Button addServer=new Button(this);
		addServer.setText(R.string.add_new_server);
		addServer.setOnClickListener(this);
		addServer.setId(NEW_SERVER_BUTTON);
		
		LinearLayout serversList=new LinearLayout(this);
		serversList.setOrientation(LinearLayout.VERTICAL);
		SpiderServer[] servers=ServerManager.getAll();
		for(int i=0; i<servers.length; i++){
			TextView s=new TextView(this);
			SpiderServer server=servers[i];
			s.setText(server.toString());
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
		if(v.getId() >= 0x80001000){
			SpiderServer server=ServerManager.getAll()[v.getId()-0x80001000];
			startActivity(new Intent(this, ManageServerMainActivity.class)
					.putExtra(ManageServerMainActivity.SERVER_PORT, server.getIp().getPort())
					.putExtra(ManageServerMainActivity.SERVER_IP, server.getIp().getAddress().getHostAddress()));
		}
		else if(v.getId() == NEW_SERVER_BUTTON){
			startActivity(new Intent(this, LauncherActivity.class));
		}
	}

}
