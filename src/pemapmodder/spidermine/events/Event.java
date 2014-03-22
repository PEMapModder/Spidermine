package pemapmodder.spidermine.events;

import pemapmodder.spidermine.SpiderServer;

public abstract class Event<Param, Ret, OutputResult>{
	public final SpiderServer server;
	protected EventHandler[] handlers;
	public Event(SpiderServer server){
		this.server=server;
	}
	public void register(EventHandler handler){
		this.handlers[handlers.length]=handler;
	}
	public OutputResult invoke(Param data){
		for(int i=0; i<handlers.length; i++){
			EventHandlerResult result=handlers[i].handleEvent(data);
			proceedResult(result.result);
			if(!result.contStatus)
				break;
		}
		return getOutputResult();
	}
	public abstract void proceedResult(Ret monoResult);
	public abstract OutputResult getOutputResult();
	public abstract class EventHandler{
		public abstract EventHandlerResult handleEvent(Param data);
	}
	public class EventHandlerResult{
		public final boolean contStatus;
		public final Ret result;
		public EventHandlerResult(boolean contStatus, Ret result){
			this.contStatus=contStatus;
			this.result=result;
		}
	}
}
