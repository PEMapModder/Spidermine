package pemapmodder.spidermine;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import pemapmodder.spidermine.android.R;
import pemapmodder.spidermine.events.server.ServerStopEvent;
import pemapmodder.spidermine.exceptions.SocketAddressUsedException;
import pemapmodder.spidermine.managers.ServerManager;

import android.app.Activity;
import android.os.Bundle;

public class SpiderServer extends Thread{
	private final static long NANO_RATIO=1000000000L;
	public final static short STATUS_PRESTART=(short)0,
			STATUS_STARTED=(short)1,
			STATUS_PAUSED=(short)2,
			STATUS_STOPPED=(short)3;
	public static final int INFINITE_SCHEDULE = Integer.MIN_VALUE;
	private final InetSocketAddress ip;
	private final String name;
	public final ServerManager manager;
	public long ticks=0;
	public long startTime;
	public long lastMilli;
	public long[] thisSecondNanos={};
	private double tps=0;
	public short status=STATUS_PRESTART;
	public final Console console;
	public ScheduledCallback[] scheduledCallbacks={};
	public int[] scheduleIntervals={};
	public int[] scheduleTimeLeft={};
	public int[] scheduleRuns={};
	public Bundle[] scheduleData={};
	private Activity app;
	protected void tickEvent(){
		for(int i=0; i<scheduleRuns.length; i++){//check schedules
			scheduleTimeLeft[i]--;
			if(scheduleTimeLeft[i]==0){
				scheduleTimeLeft[i]=scheduleIntervals[i];
				if(scheduleRuns[i]==INFINITE_SCHEDULE || scheduleRuns[i]>0){
					if(scheduleRuns[i]!=INFINITE_SCHEDULE)
						scheduleRuns[i]--;
					scheduledCallbacks[i].run(scheduleData[i]);
				}
			}
		}
	}
	public void schedule(int interval, ScheduledCallback callback, int runs, Bundle data){
		int offset=scheduleRuns.length;
		scheduledCallbacks[offset]=callback;
		scheduleIntervals[offset]=interval;
		scheduleTimeLeft[offset]=interval;
		scheduleRuns[offset]=runs;
		scheduleData[offset]=data;
	}
	public static interface ScheduledCallback{
		public void run(Bundle data);
	}
	public SpiderServer(Bundle options, Activity uiThread)throws SocketAddressUsedException, UnknownHostException{
		app=uiThread;
		console=new Console(this);
		int port=options.getInt(OPTIONS_port, 19132);
		InetSocketAddress address=new InetSocketAddress(InetAddress.getByName(options.getString(OPTIONS_ip, "0.0.0.0")), port);
		ip=address;
		if(ServerManager.register(this)==false)
			throw new SocketAddressUsedException();
		name=options.getString(OPTIONS_name, "SpiderMine MCPE Server");
		manager=new ServerManager(this);
	}
	@Override public synchronized void start(){
		super.start();
		try {
			operate();
		} catch (InterruptedException e) {
			if(console!=null){
				console.out("[SEVERE ERROR] The SpiderMine server operation has been interrupted. The server operation has been forced closed.");
			}
		}
	}
	protected void operate() throws InterruptedException{
		// prepare server status
		status=STATUS_STARTED;
		startTime=System.nanoTime();
		lastMilli=startTime;
		while(status==STATUS_STARTED){
			// tick operation
			ticks++;
			tickEvent();
			Thread.sleep(50);
			lastMilli=System.nanoTime();
			thisSecondNanos[thisSecondNanos.length]=System.nanoTime();
			long diff=System.nanoTime()-thisSecondNanos[0];
			if(diff > NANO_RATIO){
				tps=thisSecondNanos.length*NANO_RATIO/diff;
				thisSecondNanos=new long[]{};
			}
		}
		// server stopped
		manager.evt.get(ServerStopEvent.class).invoke(null);
	}
	public double getTps() {
		return tps;
	}
	public String getServerName(){
		return name;
	}
	public InetSocketAddress getIp(){
		return ip;
	}
	public Activity getApp() {
		return app;
	}
	public final static String OPTIONS_ip="options.address.string",
			OPTIONS_port="options.address.port",
			OPTIONS_name="options.name";
	public String toString(){
		return app.getString(R.string.server_list_message)
				.replace("@name", name)
				.replace("@ip", ip.getAddress().getHostAddress())
				.replace("@port", Integer.toString(ip.getPort()));
	}
}
