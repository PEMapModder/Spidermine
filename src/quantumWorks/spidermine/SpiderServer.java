package quantumWorks.spidermine;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import quantumWorks.spidermine.managers.ServerManager;
import android.os.Bundle;

public class SpiderServer extends Thread{
	private final InetSocketAddress ip;
	public SpiderServer(Bundle options)throws Throwable{
		InetAddress ip=InetAddress.getByName(options.getString(OPTIONS_ip, "0.0.0.0"));
		int port=options.getInt(OPTIONS_port, 19132);
		InetSocketAddress address=new InetSocketAddress(ip, port);
		this.ip=address;
		if(ServerManager.add(this)==false)
			throw new Throwable();
	}
	public String getServerName(){
		return "";
	}
	public InetSocketAddress getIp(){
		return ip;
	}
	public final static String OPTIONS_ip="options.string",
			OPTIONS_port="options.port";
}
