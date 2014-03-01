package quantumWorks.spidermine.objects;


public interface CommandIssuer{
	public final static int TYPE_CONSOLE=0x00,
			TYPE_CLIENT=0x01;
	public void triggerCmd(String cmd, String[] params);
	public void out(String line);
	public int getType();
}
