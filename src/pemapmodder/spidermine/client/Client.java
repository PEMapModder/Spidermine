package pemapmodder.spidermine.client;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.objects.CommandIssuer;

public class Client implements CommandIssuer{
	public SpiderServer server;
	public Client(SpiderServer server){
		this.server=server;
	}
	@Override public void triggerCmd(String cmd, String[] params) {
		server.manager.cmd.invokeCmd(server.manager.cmd.get(cmd), params, this);
	}
	@Override public void out(String line) {
		send(line);
	}
	public void send(String message){
		
	}
	@Override public int getType() {
		return TYPE_CLIENT;
	}
}
