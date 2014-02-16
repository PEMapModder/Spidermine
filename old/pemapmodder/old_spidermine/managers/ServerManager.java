package pemapmodder.old_spidermine.managers;

import pemapmodder.old_spidermine.SpiderServer;

public class ServerManager implements Manager<SpiderServer, Integer>{
	public static ServerManager[] servers={};
	public static SpiderServer getServer(Integer port){
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
	@Override public SpiderServer get(Integer id) {
		return getServer(id);
	}
	public CmdManager cmd;
	public WorldManager world;
	public UserManager user;
	public EntityManager entity;
	public TileManager tile;
}
