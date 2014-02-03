package pemapmodder.spidermine;

public interface ConsoleChangeListener {
	public void onInfoAdded(String info);
	public void onWarningAdded(String warning);
	public void onErrorAdded(String err);
	public void onDebugAdded(String debug);
}
