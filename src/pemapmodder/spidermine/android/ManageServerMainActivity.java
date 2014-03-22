package pemapmodder.spidermine.android;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.managers.ServerManager;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ManageServerMainActivity extends Activity {
	public final static String SERVER_PORT="port",
			SERVER_IP="ip";
	protected SpiderServer server=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout=new LinearLayout(this);
		setContentView(layout);
		;
		setupActionBar();
		try {
			server=ServerManager.get(new InetSocketAddress(InetAddress.getByName(getIntent().getStringExtra(SERVER_IP)), getIntent().getIntExtra(SERVER_PORT, 19132)));
		} catch (UnknownHostException e) {
			finish();
		}
		ScrollView console=new ScrollView(this);
		ImageButton up=new ImageButton(this);
		up.setImageDrawable(getResources().getDrawable(android.R.drawable.arrow_up_float));
		up.setOnClickListener(new ScrollUp(this));
		TextView ctxt=new TextView(this);
		console.addView(ctxt);
	}
	protected class ScrollUp implements OnClickListener{
		private ManageServerMainActivity app;
		public ScrollUp(ManageServerMainActivity app){
			this.app=app;
		}
		@Override public void onClick(View v) {
			
		}
	}
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.manage_server_main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
