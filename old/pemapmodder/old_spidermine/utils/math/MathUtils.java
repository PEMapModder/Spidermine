package pemapmodder.old_spidermine.utils.math;

public class MathUtils {
	public static long myHash(Object seed){
		return seed.hashCode()+seed.toString().hashCode();
	}
}
