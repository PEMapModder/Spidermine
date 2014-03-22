package pemapmodder.spidermine.managers;

public interface Manager<Managee, Identifier> {
	public Managee get(Identifier id);
	public boolean add(Managee managee);
	public boolean remove(Managee managee);
}
