package pemapmodder.old_spidermine.utils.math;

public class Position extends PCoord{
	protected double y;
	public Position(double x, double y, double z){
		super(x,z);
		this.y=y;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	public double dist(Position other){
		return Math.sqrt(Math.pow(x-other.getX(), 2) +
				Math.pow(y-other.getY(), 2) +
				Math.pow(z-other.getZ(), 2)); // +/- ^ 2 > 0 *absolute*
	}
}
