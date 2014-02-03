package pemapmodder.spidermine;

import pemapmodder.spidermine.managers.ServerManager;

public class ConsoleInputListener{
	public void cmd(String text, int window){
		ServerManager.get(window).manager.cmd.cmd(text.split(" "), ServerManager.get(window).manager.user.get("console"));
	}
}
