package pemapmodder.spidermine.utils.math;

import pemapmodder.spidermine.world.World;

public class Location extends Position {

	protected World world;
	public Location(double x, double y, double z, World world) {
		super(x, y, z);
		this.world=world;
	}
	public World getWorld(){
		return world;
	}
	public pemapmodder.spidermine.world.Block getBlock(){
		return world.getBlock(this);
	}

}
