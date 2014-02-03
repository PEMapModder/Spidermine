package pemapmodder.easymod;

import java.io.File;

import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.managers.ServerManager;
import pemapmodder.spidermine.utils.io.MyReader;
import android.content.Context;
import android.os.Bundle;

@SuppressWarnings("unused")
public class EasyMod {
	public static EasyMod load(File src, Context app, int server)throws Throwable{
		ServerManager.get(server).ccl.info("Loading EasyMod from source at "+src.getAbsolutePath());
		return new EasyMod(src, app, ServerManager.get(server));
	}
	private Context app;
	private SpiderServer server;
	private Bundle res;
	private EasyMod(File f, Context app, SpiderServer server) throws Throwable{
		this.app=app;
		this.server=server;
		MyReader r=new MyReader(f);
		String src=r.readAll();
		r.close();
		String[] resrc=src.split("<res>", 2)[1].split("\n");
		Bundle res=new Bundle();
		for(int i=0; i<resrc.length; i++)
			res.putString(resrc[i].split("=>")[0], resrc[i].split("=>", 2)[1]);
		this.res=res;
	}
}
