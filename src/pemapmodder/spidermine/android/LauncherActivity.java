package pemapmodder.spidermine.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LauncherActivity extends Activity implements View.OnClickListener{

	public final static int NEW_SERVER=0x6d9753a0;
	public final static int OPEN_SERVER=0x6d9753a1;
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout());
	}
	public LinearLayout layout(){
		LinearLayout ll=new LinearLayout(this);
		TextView title=new TextView(this);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP);
		//title.setText(R.string.ENTER_TITLE);
		ll.addView(title);
		Button newServer=new Button(this);
		newServer.setOnClickListener(this);
		newServer.setId(NEW_SERVER);
		return ll;
	}
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	@Override public void onClick(View v){
		switch(v.getId()){
		case NEW_SERVER:
			break;
		case OPEN_SERVER:
			break;
		}
	}

}
