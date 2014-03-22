package pemapmodder.spidermine.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import pemapmodder.spidermine.SpiderServer;


public class NetworkHandler extends Thread{
	public DatagramSocket socket=null;
	public DatagramPacket packet=null;
	public final SpiderServer server;
	public NetworkHandler(SpiderServer server) throws SocketException{
		this.server=server;
		socket=new DatagramSocket(server.getIp());
		socket.setSoTimeout(5000);
	}
}
