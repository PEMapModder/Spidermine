package pemapmodder.old_spidermine;

import android.app.Activity;
import android.os.Bundle;

public class ServerRunner extends Thread{
	public SpiderServer server;
	public ServerRunner(Bundle options, Activity app, ConsoleChangeListener ccl)
			throws Throwable{
		setPriority(options.getInt(PRIORITY));
		new SpiderServer(options, app, ccl);
	}
	@Override public synchronized void start(){
		server.run();
	}
	public void stopServer(){
		server.stop();
	}
	public final static String IP="pemapmodder.spidermine.ServerOptions.IP",
			PORT="pemapmodder.spidermine.ServerOptions.PORT",
			NAME="pemapmodder.spidermine.ServerOptions.NAME",
			PRIORITY="pemapmodder.spidermine.ServerOptions.PRIORITY",
			TOAST_WARN="pemapmodder.spidermine.ServerOptions.TOAST_WARN",
			PATH="pemapmodder.spidermine.ServerOptions.PATH";
}

