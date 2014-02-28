package quantumWorks.spidermine.managers;

public interface Manager<Managee, Identifier> {
	public Managee get(Identifier id);
}
