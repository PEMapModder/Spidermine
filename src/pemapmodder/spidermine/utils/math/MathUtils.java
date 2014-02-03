package pemapmodder.spidermine.utils.math;

public class MathUtils {
	public long myHash(Object seed){
		return seed.hashCode()+seed.toString().hashCode();
	}
}
