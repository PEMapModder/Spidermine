package pemapmodder.spidermine.objects;

public class Interfaces {
	public static interface ErrorHandler{
		public void err(String error);
	}
	public static interface ConsoleListener{
		public void console(String msg);
	}
}
