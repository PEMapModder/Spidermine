package pemapmodder.spidermine.objects;

public class Command{
	private final String name;
	private final CommandListener callback;
	public Command(String name, CommandListener callback){
		this.name=name;
		this.callback=callback;
	}
	public String invoke(String[] params){
		return this.callback.invoke(params);
	}
	public String getName() {
		return name;
	}
	@Override public boolean equals(Object other){
		if(!(other instanceof Command))
			return false;
		Command another = (Command) other;
		return another.getName().equalsIgnoreCase(this.getName());
	}
	@Override public int hashCode(){
		return this.name.hashCode();		
	}
	public static interface CommandListener{
		public String invoke(String[] params);
	}
}
