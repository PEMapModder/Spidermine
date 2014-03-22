package pemapmodder.spidermine.networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import pemapmodder.spidermine.SpiderServer;


public class NetworkHandler extends Thread{
	public DatagramSocket socket=null;
	public DatagramPacket packet=null;
	public NetworkHandler(SpiderServer server) throws SocketException{
		socket=new DatagramSocket(server.getIp());
		socket.setSoTimeout(5000);
	}
}
