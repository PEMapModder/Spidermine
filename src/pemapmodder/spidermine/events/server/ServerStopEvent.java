package pemapmodder.spidermine.events.server;

import android.os.Bundle;
import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.events.Event;

public class ServerStopEvent extends Event{
	public ServerStopEvent(SpiderServer server){
		super(server);
	}
	public Bundle getOutput(){
		return null;
	}
	@Override public void evalResult(Bundle data){
		// TODO Auto-generated method stub
		
	}
}
