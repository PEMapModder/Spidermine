package pemapmodder.spidermine.events;

import pemapmodder.spidermine.SpiderServer;
import android.os.Bundle;

public abstract class Event<DataType>{
	protected EventHandler[] handlers;
	public final SpiderServer server;
	public Event(SpiderServer server){
		this.server=server;
	}
	public void registerHandler(EventHandler handler){
		handlers[handlers.length]=handler;
	}
	public Bundle[] invoke(DataType data){
		for(int i=0; i<handlers.length; i++){
			EventResult result=handlers[i].handleEvent(data);
			if(!result.doCont)
				break;
		}
		return getOutput();
	}
}
