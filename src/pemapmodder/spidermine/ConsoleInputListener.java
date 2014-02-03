package pemapmodder.spidermine;

import pemapmodder.spidermine.managers.ServerManager;

public class ConsoleInputListener{
	public void cmd(String text, int window){
		ServerManager.getServer(window).manager.cmd.cmd(
				text.split(" "),//cmd & params 
				ServerManager.getServer(window).manager.user.get("console"));//issuer
	}
}
