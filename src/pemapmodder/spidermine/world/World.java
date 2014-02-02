package pemapmodder.spidermine.world;

import java.io.File;

import pemapmodder.spidermine.SpiderServer;

public class World{
	public final File dir;
	public World(SpiderServer server, String name) {
		dir=new File(server.dir+name+"/");
		boolean isNew=dir.mkdir();
		if(isNew){
			//TODO populate world
		}else{
			//TODO import world
		}
	}
	
}
