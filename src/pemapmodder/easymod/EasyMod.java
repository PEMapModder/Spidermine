package pemapmodder.easymod;

import java.io.File;

import pemapmodder.easymod.xml.Xml;
import pemapmodder.spidermine.SpiderServer;
import pemapmodder.spidermine.managers.ServerManager;
import android.content.Context;
import android.os.Bundle;

@SuppressWarnings("unused")
public class EasyMod {
	public static EasyMod load(File src, Context app, int server)throws Throwable{
		ServerManager.getServer(server).ccl.info("Loading EasyMod from source at "+src.getAbsolutePath());
		return new EasyMod(src, app, ServerManager.getServer(server));
	}
	private Context app;
	private SpiderServer server;
	private Bundle res;
	private EasyMod(File f, Context app, SpiderServer server) throws Throwable{
		if(!f.isFile()){
			throw new Throwable("Passed illegal param0 : not a file");
		}
		String ext="";
		for(int i=f.getName().length()-1; i>=0; i--)
			ext=ext+f.getName().charAt(i);
		ext=ext.split(".", 2)[0];
		boolean bool=ext.equalsIgnoreCase("helm")||
				ext.equalsIgnoreCase("xml")||
				ext.equalsIgnoreCase("easymod")||
				ext.equalsIgnoreCase("mod");
		if(!bool)
			server.ccl.warning("You are recommended to use the file extension xml, helm, easymod or mod for EasyMod files.");
		this.app=app;
		this.server=server;
		Xml content=Xml.parse(f);
	}
}
