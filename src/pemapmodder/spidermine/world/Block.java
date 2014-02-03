package pemapmodder.spidermine.world;

public class Block {
	private int id;
	private int damage;
	public Block(int id, int damage){
		this.id=id;
		this.damage=damage;
	}
	public Block(int i){
		this(i,0);
	}
	public int getId(){
		return this.id;
	}
	public int getMeta(){
		return this.damage;
	}
	public int getDamage(){
		return this.damage;
	}
}
