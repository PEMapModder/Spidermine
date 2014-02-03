package pemapmodder.spidermine.managers;

import pemapmodder.spidermine.SpiderServer;

public class ServerManager{
	public static ServerManager[] servers={};
	public static SpiderServer get(int port){
		for(int i=0;i<servers.length;i++){
			if(servers[i].server.port==port)
				return servers[i].server;
		}
		return null;
	}
	public SpiderServer server;
	public ServerManager(SpiderServer server){
		servers[servers.length]=this;
	}
}
