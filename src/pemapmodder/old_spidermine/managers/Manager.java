package pemapmodder.old_spidermine.managers;

public interface Manager<Managee, IdType>{
	public Managee get(IdType id);
}
