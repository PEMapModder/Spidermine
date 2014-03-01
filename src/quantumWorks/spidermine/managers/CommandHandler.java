package quantumWorks.spidermine.managers;

import quantumWorks.spidermine.*;
import quantumWorks.spidermine.objects.*;

public class CommandHandler implements Manager<Command, String>{
	public final SpiderServer server;
	private Command[] commands;
	public CommandHandler(SpiderServer server){
		this.server=server;
	}
	public String invokeCmd(Command cmd, String[] params, CommandIssuer issuer){
		return null;
	}
	@Override
	public Command get(String name) {
		for(int i=0; i<commands.length; i++){
			if(commands[i].getName().equalsIgnoreCase(name))
				return commands[i];
		}
		return null;
	}
	@Override
	public boolean add(Command command) {
		if(get(command.getName())==null){
			commands[commands.length]=command;
			return true;
		}
		return false;
	}
	@Override
	public boolean remove(Command command) {
		if(get(command.getName())!=null){
			Command[] newCmds={};
			for(int i=0; i<commands.length; i++){
				if(!commands[i].equals(command))
					newCmds[newCmds.length]=commands[i];
			}
			commands=newCmds;
			return true;
		}
		return false;
	}
}
