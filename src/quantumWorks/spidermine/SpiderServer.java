package quantumWorks.spidermine;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import quantumWorks.spidermine.managers.ServerManager;
import android.os.Bundle;

public class SpiderServer extends Thread{
	public final static short STATUS_PRESTART=(short)0,
			STATUS_STARTED=(short)1,
			STATUS_PAUSED=(short)2,
			STATUS_STOPPED=(short)3;
	private final InetSocketAddress ip;
	private final String name;
	public long ticks=0;
	public long startTime;
	public long lastMilli;
	public short status=STATUS_PRESTART;
	protected void tickEvent(){
		
	}
	public SpiderServer(Bundle options)throws Throwable{
		super(new Runnable(){
			@Override public void run(){}
		});
		InetAddress ip=InetAddress.getByName(options.getString(OPTIONS_ip, "0.0.0.0"));
		int port=options.getInt(OPTIONS_port, 19132);
		InetSocketAddress address=new InetSocketAddress(ip, port);
		this.ip=address;
		if(ServerManager.add(this)==false)
			throw new Throwable();
		this.name=options.getString(OPTIONS_name, "SpiderMine MCPE Server");
	}
	@Override public synchronized void start(){
		super.start();
		try {
			init();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void init() throws InterruptedException{
		status=STATUS_STARTED;
		startTime=System.nanoTime();
		lastMilli=startTime;
		while(status==STATUS_STARTED){
			ticks++;
			tickEvent();
			Thread.sleep(50);
			lastMilli=System.nanoTime();
		}
	}
	public String getServerName(){
		return name;
	}
	public InetSocketAddress getIp(){
		return ip;
	}
	public final static String OPTIONS_ip="options.address.string",
			OPTIONS_port="options.address.port",
			OPTIONS_name="options.name";
}
