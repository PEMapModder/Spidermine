package pemapmodder.spidermine.events;

import android.os.Bundle;

public class EventResult{
	public EventResult(boolean p0, Bundle p1){
		doCont=p0;
		result=p1;
	}
	public final boolean doCont;
	public final Bundle result;
}
