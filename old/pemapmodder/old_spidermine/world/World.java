package pemapmodder.old_spidermine.world;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import pemapmodder.old_spidermine.SpiderServer;
import pemapmodder.old_spidermine.utils.io.MyReader;
import pemapmodder.old_spidermine.utils.math.Position;
import pemapmodder.old_spidermine.world.populator.WorldPopulator;

public class World{
	public final File dir;
	public final File chunksDir;
	public SpiderServer server;
	private Chunk[][] chunks;
	public short type;
	public World(SpiderServer server, String name, Object seed, short type) {
		this.type=type;
		this.server=server;
		dir=new File(server.dir+"worlds/"+name+"/");
		chunksDir=new File(dir+"chunks/");
		boolean isNew=dir.mkdir();
		try{
			if(isNew){
				for(int cx=0;cx<16;cx++){
					for(int cz=0;cz<16;cz++)
						chunks[cx][cz]=new Chunk(this);
				}
				WorldPopulator.populate(this, seed, type);
				outSave();
			}else{
				inRead();
			}
		}
		catch(Exception e){
			server.err(e);
		}
	}
	private void inRead()throws Exception{
		for(int cx=0;cx<16;cx++){
			for(int cz=0;cz<16;cz++){
				Chunk oc=new Chunk(this);
				File file=new File(chunksDir, Integer.toHexString(cx)+'-'+Integer.toHexString(cz)+".chunk");
				MyReader is=new MyReader(file);
				String magic=is.readLength(17);
				if(!magic.equals("SPIDERMINE_CHUNK\n")){
					//TODO what to do if chunk corrupted
					is.close();
					throw new Exception("CHUNK_FILE_CORRUPTED");
				}
				for(int x=0;x<16;x++){
					String id=is.readLength(8);;
					if(!id.substring(0, 5).equals("PLANE")||!id.substring(6).equals("\n\n")){
						//TODO what to do if chunk corrupted
						is.close();
						throw new Exception("CHUNK_FILE_CORRUPTED");
					}
					if((id.charAt(5)+"")!=Integer.toHexString(x)){
						//TODO when the planes are mixed up;
					}
					for(int z=0;z<16;z++){
						String idz=is.readLength(8);
						if(!id.substring(0, 6).equals("PILLAR")||id.charAt(7)!='\n'){
							//TODO what to do if chunk corrupted
							is.close();
							throw new Exception("CHUNK_FILE_CORRUPTED");
						}
						if((idz.charAt(6)+"")!=Integer.toHexString(z)){
							//TODO when the pillars are mixed up
						}
						String pillar=is.readLength(256);
						for(int y=0;y<128;y++){
							oc.setBlock(x, y, z, new Block(pillar.charAt(y*2), pillar.charAt(y*2+1)), false);
						}
					}
				}
				is.close();
				chunks[cx][cz]=oc;
			}
		}
	}
	private void outSave()throws Exception{
		chunksDir.mkdir();
		for(int cx=0;cx<16;cx++){
			for(int cz=0;cz<16;cz++){
				File chunkFile=new File(chunksDir, Integer.toHexString(cx)+'-'+Integer.toHexString(cz)+".chunk");
				chunkFile.delete();
				OutputStreamWriter os=new OutputStreamWriter(new FileOutputStream(chunkFile));
				Chunk c=chunks[cx][cz];
				//count: 67712+17=>67729 bytes approx. 66 KB
				os.append("SPIDERMINE_CHUNK\n");//magic
				for(int x=0;x<16;x++){//count: 4232*16=>67712 bytes
					os.write("PLANE"+Integer.toHexString(x)+"\n\n");//length: 8
					for(int z=0;z<16;z++){//count: 264*16=>4224 bytes
						os.write("PILLAR"+Integer.toHexString(z)+"\n");//length: 8
						for(int y=0;y<128;y++){//count: 128*2=>256 bytes
							os.write(c.getBlock(x, y, z).getId());//1
							os.write(c.getBlock(x, y, z).getDamage());//1
						}
					}
				}
				os.flush();
				os.close();
			}
		}
	}
	public void setBlock(Position p, Block bl, boolean update){
		chunks[(int) (p.getX()/16)][(int) (p.getZ()/16)].setBlock((int)p.getX(), (int)p.getY(), (int)p.getZ(), bl, update);
	}
	public Block getBlock(Position p){
		return chunks[(int) (p.getX()/16)][(int) (p.getZ()/16)].getBlock((int) p.getX(), (int) p.getY(), (int) p.getZ()); 
	}
	@Override public int hashCode(){//just for fun
		int ret=0xb60a;
		ret*=0xd809;
		ret+=this.dir.hashCode();
		ret*=0xd809;
		ret+=(int)this.type^(this.type>>>32);
		ret*=0xd809;
		return ret;
	}
}
