package pemapmodder.spidermine.managers;

import java.net.InetSocketAddress;

import pemapmodder.spidermine.SpiderServer;


public class ServerManager implements Manager<Manager<?,?>, String>{
	// non-static manage Manager<?,?>
	public final SpiderServer server;
	public final CommandHandler cmd;
	public final ClientManager clnt;
	public final EventManager evt;
	public ServerManager(SpiderServer server){
		this.server=server;
		this.cmd=new CommandHandler(server);
		this.clnt=new ClientManager(server);
		this.evt=new EventManager(server);
	}
	@Override public Manager<?, ?> get(String id) {
		if(id.equalsIgnoreCase("client"))
			return this.clnt;
		return null;
	}
	@Override public boolean add(Manager<?,?> newManager){
		throw new UnsupportedOperationException();
	}
	@Override public boolean remove(Manager<?,?> manager){
		throw new UnsupportedOperationException();
	}
	// static manage SpiderServer
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
	public static SpiderServer[] getAll(){
		return servers;
	}
}
