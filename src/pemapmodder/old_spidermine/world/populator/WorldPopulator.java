package pemapmodder.old_spidermine.world.populator;

import java.util.Random;

import pemapmodder.old_spidermine.utils.math.MathUtils;
import pemapmodder.old_spidermine.utils.math.Position;
import pemapmodder.old_spidermine.world.Block;
import pemapmodder.old_spidermine.world.World;

public class WorldPopulator {

	public static void populate(World world, Object seed, short type) {
		new WorldPopulator(world, seed, type);
	}

	private Random random;
	private WorldPopulator(World world, Object seed, short type){
		
		this.random=new Random(MathUtils.myHash(seed)/*+world.hashCode()*/);
		for(int x=0;x<256;x++){//populate bedrock
			for(int z=0;z<256;z++){
				world.setBlock(new Position(x, 0, z), new Block(7), false);
				int height=random.nextInt()%4;
				if(height==0)height=4;
				for(int i=1;i<=height;i++)
					world.setBlock(new Position(x, i, z), new Block(7), false);
				
			}
		}
	}

	public final static short TYPE_SUPERFLAT=0x0;
	public final static short TYPE_NORMAL=0x1;
	public final static short TYPE_MEGA=0x2;
	//public final static short TYPE_PRE_INFINITE=0xFF;

}
