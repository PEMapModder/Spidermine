package pemapmodder.spidermine.events.server;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.events.Event;

public class ServerStopEvent extends Event<String, Void, Void>{
	public ServerStopEvent(SpiderServer server){
		super(server);
	}
	@Override public void proceedResult(Void v){
		
	}
	@Override public Void getOutputResult(){
		return null;
	}
}
