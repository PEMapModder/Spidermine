package quantumWorks.spidermine.managers;

import java.net.InetSocketAddress;

import quantumWorks.spidermine.SpiderServer;

public class ServerManager implements Manager<Manager<?,?>, String>{
	public final SpiderServer server;
	public final CommandHandler cmd;
	public final ClientManager clnt;
	public ServerManager(SpiderServer server){
		this.server=server;
		this.cmd = new CommandHandler(server);
		this.clnt = new ClientManager(server);
	}
	@Override public Manager<?, ?> get(String id) {
		if(id.equalsIgnoreCase("client"))
			return this.clnt;
		return null;
	}
	@Override public boolean add(Manager<?,?> newManager){
		throw new UnsupportedOperationException();//TYPO?
	}
	@Override public boolean remove(Manager<?,?> manager){
		throw new UnsupportedOperationException();
	}
	public static SpiderServer[] servers={};
	public static boolean register(SpiderServer server){
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
