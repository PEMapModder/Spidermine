package pemapmodder.spidermine.world;

import java.io.File;
import java.util.Random;
import pemapmodder.spidermine.SpiderServer;

public class World{
	public final SpiderServer server;
	public final File dir, chunksDir;
	public final long seed;
	public World(SpiderServer server, String name){
		this(server, name, new Random(System.currentTimeMillis()).nextLong());
	}
	public World(SpiderServer server, String name, long seed){
		this.server=server;
		dir=new File(server.worldDir, name);
		dir.mkdirs();
		this.seed=seed;
		this.chunksDir=new File(dir, "chunks/");
	}
}
