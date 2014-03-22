package pemapmodder.spidermine.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import pemapmodder.spidermine.SpiderServer;


public class NetworkHandler extends Thread{
	public DatagramSocket socket=null;
	public DatagramPacket packet=null;
	public final SpiderServer server;
	public NetworkHandler(SpiderServer server){
		this.server=server;
		try{
			socket=new DatagramSocket(server.getIp());
		}catch(SocketException e){
			server.console.out("[SEVERE ERROR] SpiderMine is unable to create socket via address "+server.getIp().toString()+". Server starting aborted.");
		}
	}
	@Override public synchronized void start(){
		super.start();
		while(server.status==SpiderServer.STATUS_STARTED){
			byte[] buffer=new byte[2048];
			packet=new DatagramPacket(buffer, buffer.length);
			try{
				socket.receive(packet);
			}catch(IOException e){
				
			}
		}
	}
}
