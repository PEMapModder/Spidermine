package pemapmodder.spidermine.managers;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.events.Event;

public class EventManager implements Manager<Event, String>{
	protected Event[] events;
	public SpiderServer server;
	@Override public Event get(String id){
		for(int i=0; i<events.length; i++){
			if(events[i].getClass().getName().equals(id))
				return events[i];
		}
		return null;
	}
	public Event get(Class<?> classObj){
		for(int i=0; i<events.length; i++){
			if(events[i].getClass().equals(classObj))
				return events[i];
		}
		return null;
	}
	@Override public boolean add(Event managee){
		if(get(managee.getClass().getName())==null){
			events[events.length]=managee;
			return true;
		}
		return false;
	}
	@Override public boolean remove(Event managee){
		throw new UnsupportedOperationException();
	}
	public EventManager(SpiderServer server){
		this.server=server;
		init();
	}
	private void init(){
		// add a list of events here...
	}
}
