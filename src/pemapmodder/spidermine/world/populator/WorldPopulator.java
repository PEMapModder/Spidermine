package pemapmodder.spidermine.world.populator;

import java.util.Random;

import pemapmodder.spidermine.utils.math.Position;
import pemapmodder.spidermine.world.Block;
import pemapmodder.spidermine.world.World;

public class WorldPopulator {

	public static void populate(World world, Object seed, short type) {
		new WorldPopulator(world, seed, type);
	}

	private Random random;
	private WorldPopulator(World world, Object seed, short type){
		for(int x=0;x<256;x++){
			for(int z=0;z<256;z++){
				world.setBlock(new Position(x, 0, z), new Block(0), false);
			}
		}
		this.random=new Random(MathUtils.myHash(seed)/*+world.hashCode()*/);
		Integer a=1;
		
	}

	public final static short TYPE_SUPERFLAT=0x0;
	public final static short TYPE_NORMAL=0x1;
	public final static short TYPE_MEGA=0x2;
	//public final static short TYPE_PRE_INFINITE=0xFF;

}
