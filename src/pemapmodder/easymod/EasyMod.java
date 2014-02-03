package pemapmodder.easymod;

import pemapmodder.spidermine.ServerManager;
import android.os.Context;
import pemapmodder.spidermine.utils.io.MyReader;
import java.io.File;
import android.os.Bundle;

public class EasyMod {
	public static EasyMod load(File src, Context app, int server)throws Exception{
		ServerManager.get(server).ccl.info("Loading EasyMod from source at "+src.getAbsolutePath());
		return new EasyMod(src, app, ServerManager.get(server));
	}
	private EasyMod(File f, Context app, SpiderServer server){
		this.app=app;
		this.server=server;
		String src=new MyReader(f).readAll();
		String[] resrc=src.split("<res>", 2)[1].split("\n");
		Bundle res=new Bundle();
		for(int i=0; i<resrc.length; i++)
			res.putString(resrc[i].split("=>")[0], resrc[i].split("=>", 2)[1]);
		this.res=res;
	}
}
