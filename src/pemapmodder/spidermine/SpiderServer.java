package pemapmodder.spidermine;

import java.io.File;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class SpiderServer implements Runnable {
	Context app;
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
	long ticks=0;
	/////////////////////////
	//////////debug//////////
	/////////////////////////
	Logger logger;
	long startTime;
	long lastTick;
	public SpiderServer(Bundle options, Context app)throws Throwable{
		this.app=app;
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
		while(status==RUNTIME){
			try {
				tick();
				ticks++;
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
	protected void onOverloaded(long ti) {
		logger.log(Level.WARNING, "Server overloaded. Tick interval: "+Long.toString(ti)+" ms.");
		if(doToastWarn){
			Toast t=Toast.makeText(app, "Server overloaded! TPS approx. "+Double.toString((Math.pow(ti, -1))), Toast.LENGTH_SHORT);
			t.show();
		}
	}
	public short getStatus(){
		return status;
	}
	protected void tick()throws Throwable{
		
	}
	protected void err(Throwable e){
		if(e instanceof InterruptedException){
			logger.log(Level.SEVERE, "InterruptedException", e);
			Log.e("SpiderServer", "InterruptedException", e);
		}
	}
	public final void stop(){
		status=STOPPED;
	}
	public final static short PRESTART=0,
			RUNTIME=1,
			PAUSED=2,
			STOPPED=2;
}
