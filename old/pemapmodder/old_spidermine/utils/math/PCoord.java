package pemapmodder.old_spidermine.utils.math;

public class PCoord {
	protected double x, z;
	public PCoord(double x, double z){
		this.x=x;
		this.z=z;
	}
	public double getX(){
		return x;
	}
	public double getZ(){
		return z;
	}
	public double pDist(PCoord other){
		return Math.sqrt(Math.pow(x-other.getX(), 2)+Math.pow(z-other.getZ(), 2));
	}
}
