package pemapmodder.spidermine;

public interface Manager<Managee, Identifier> {
	public Managee get(Identifier id);
}
