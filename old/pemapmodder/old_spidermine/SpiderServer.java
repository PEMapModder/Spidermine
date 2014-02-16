package pemapmodder.old_spidermine;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import pemapmodder.old_spidermine.managers.ServerManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class SpiderServer implements Runnable {
	/////////////////////////
	//////////tools//////////
	/////////////////////////
	public Context app;
	public ConsoleChangeListener ccl;
	/////////////////////
	///////options///////
	/////////////////////
	public final InetAddress ip;
	public final int port;
	public final String name;
	public boolean doToastWarn;
	public final File dir;
	////////////////////////
	//////////info//////////
	////////////////////////
	private short status=PRESTART;
	private long ticks=0;
	/////////////////////////
	//////////debug//////////
	/////////////////////////
	public Logger logger;
	protected long startTime;
	private long lastTick;
	///////////////////////////
	//////////network//////////
	///////////////////////////
	public DatagramSocket socket;
	public DatagramPacket packet;
	////////////////////////////
	//////////managers//////////
	////////////////////////////
	public ServerManager manager=new ServerManager(this);
	public SpiderServer(Bundle options, Context app, ConsoleChangeListener ccl)
			throws Throwable{
		this.app=app;
		this.ccl=ccl;
		ip=InetAddress.getByName(options.getString(ServerRunner.IP, "0.0.0.0"));
		port=options.getInt(ServerRunner.PORT, 19132);
		name=options.getString(ServerRunner.NAME, "SpiderMine Server");
		doToastWarn=options.getBoolean(ServerRunner.TOAST_WARN, true);
		dir=new File(options.getString(ServerRunner.PATH,
				Environment.getExternalStorageDirectory().getAbsolutePath()+
				"games/SpiderMine/"+name+"/"));
		if(!dir.mkdirs()){//load server
			
		}else{//create server
			
		}
		logger=Logger.getLogger("SpiderServer");
	}
	public void run() {
		status=RUNTIME;
		startTime=System.currentTimeMillis();
		lastTick=startTime;
		try {
			initNetworking();
		} catch (Throwable e) {
			err(e);
		}
		while(status==RUNTIME){
			try {
				tick();
				this.ticks=getTicks() + 1;
				long time=System.currentTimeMillis()-lastTick;
				if(time>100)
					onOverloaded(time);
				lastTick=System.currentTimeMillis();
				Thread.sleep(50);
			} catch (Throwable e) {
				err(e);
			}
		}
		status=PAUSED;
		status=STOPPED;
	}
	protected void initNetworking()throws Throwable{
		socket=new DatagramSocket(port, ip);
		int length=1536;
		byte[] buffer=new byte[length];
		packet=new DatagramPacket(buffer, length);
		socket.setSoTimeout(5000);
		socket.receive(packet);
		socket.setSoTimeout(0);
		ByteBuffer b=ByteBuffer.wrap(packet.getData());
		byte[] data=new byte[packet.getLength()];
		b.get(data);
		DatagramPacket o=new DatagramPacket(data, packet.getPort(), packet.getAddress(), packet.getLength());
		handlePacket(o);
	}
	protected synchronized void handlePacket(DatagramPacket o)throws Throwable{
		if(o!=null){
			switch(o.getData()[0]&0xFF){
			
			}
		}
	}
	protected void onOverloaded(long ti) {
		logger.log(Level.WARNING, "Server overloaded. Tick interval: "+Long.toString(ti)+" ms.");
		if(doToastWarn){
			Toast t=Toast.makeText(app, "Server overloaded! TPS approx. "+Double.toString((Math.pow(ti, -1))), Toast.LENGTH_SHORT);
			t.show();
		}
		ccl.warning("Server overloaded!!!");
	}
	public short getStatus(){
		return status;
	}
	protected void tick()throws Throwable{
		
	}
	public void err(Throwable e){
		if(e instanceof InterruptedException)
			logger.log(Level.SEVERE, "InterruptedException", e);
		ccl.err("An error occurred: "+e.toString());
	}
	public final void stop(){
		status=STOPPED;
	}
	public long getTicks() {
		return ticks;
	}
	public final static short PRESTART=0,
			RUNTIME=1,
			PAUSED=2,
			STOPPED=2;
}
