package pemapmodder.spidermine;

import java.net.SocketAddress;

public class ServerManager implements Manager<SpiderServer, SocketAddress>{
	public static ServerManager instance=new ServerManager();
	private SpiderServer[] serverList={};
	private ServerManager(){}
	@Override public SpiderServer get(SocketAddress id) {
		for(int i=0; i<serverList.length; i++){
			if(this.serverList[i].getIp()==id)
				return serverList[i];
		}
		return null;
	}
	public boolean registerServer(SpiderServer server){
		for(int i=0; i<serverList.length; i++){
			if(this.serverList[i].getIp()==server.getIp())
				return false;
		}
		serverList[serverList.length]=server;
		return true;
	}
}
