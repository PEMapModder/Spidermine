package pemapmodder.spidermine.world;

public class Chunk {
	private Block[][][] blocks;
	private World world;
	public Chunk(World world){//generate new empty chunk
		this.world=world;
		for(int x=0;x<16;x++){
			for(int z=0;z<16;z++){
				for(int y=0;y<128;y++){
					blocks[x][z][y]=new Block(0,0);
				}
			}
		}
	}
	public Chunk setBlock(int x, int y, int z, Block block, boolean update){
		x%=16;
		z%=16;
		this.blocks[x][z][y]=block;
		if(update){
			//TODO update blocks
		}
		return this;
	}
	public Block getBlock(int x, int y, int z){
		return blocks[x%16][z%16][y];
	}
	public World getWorld() {
		return world;
	}
}
