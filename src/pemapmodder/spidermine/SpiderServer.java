package pemapmodder.spidermine;

import java.net.SocketAddress;

public class SpiderServer extends Thread{
	private final SocketAddress ip;
	public SpiderServer(SocketAddress ip)throws Throwable{
		this.ip=ip;
		if(ServerManager.instance.registerServer(this)==false)
			throw new Throwable();
	}
	public SocketAddress getIp(){
		return ip;
	}
	@Override public synchronized void start(){
		
	}
}
