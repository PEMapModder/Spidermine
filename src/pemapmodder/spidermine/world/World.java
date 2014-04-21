package pemapmodder.spidermine.world;

import java.io.File;
import pemapmodder.spidermine.SpiderServer;

public class World{
	public final SpiderServer server;
	public final File dir;
	public World(SpiderServer server, String name, long seed){
		this.server=server;
		this.dir=new File(server.dir)
	}
}
