package quantumWorks.spidermine.managers;

import java.net.InetSocketAddress;

import quantumWorks.spidermine.SpiderServer;


public class ServerManager implements Manager<Manager<?,?>, String>{
	public final SpiderServer server;
	public final ConsoleManager csl;
	public ServerManager(SpiderServer server){
		this.server=server;
		this.csl = new ConsoleManager(server);
	}
	@Override public Manager<?, ?> get(String id) {
		
		return null;
	}
	public static SpiderServer[] servers={};
	public static boolean add(SpiderServer server){
		for(int i=0; i<servers.length; i++){
			if(servers[i].getIp().equals(server.getIp()))
				return false;
		}
		servers[servers.length] = server;
		return true;
	}
	public static SpiderServer get(InetSocketAddress ip){
		for(int i=0; i<servers.length; i++){
			if(servers[i].getIp().equals(ip))
				return servers[i];
		}
		return null;
	}
}
