package pemapmodder.spidermine.events;

import android.os.Bundle;

public class EventResult{
	public final boolean doCont;
	public final Bundle result;
	public EventResult(boolean p0, Bundle p1){
		doCont=p0;
		result=p1;
	}
	public boolean isCont(){
		return doCont;
	}
	public Bundle getResult(){
		return result;
	}
}
