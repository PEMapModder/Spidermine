package pemapmodder.spidermine.events;

import android.os.Bundle;

public abstract class EventHandler{
	public abstract EventResult handleEvent(Bundle data);
}
